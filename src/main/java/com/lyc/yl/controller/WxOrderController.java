package com.lyc.yl.controller;

import com.lyc.yl.entity.Cart;
import com.lyc.yl.entity.Order;
import com.lyc.yl.entity.OrderGoods;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CartService;
import com.lyc.yl.service.OrderGoodsService;
import com.lyc.yl.service.OrderService;
import com.lyc.yl.util.RegexUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 订单管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private CartService cartService;
    @Resource
    private OrderGoodsService orderGoodsService;

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @PostMapping("/insert")
    public Object insert(@RequestBody Order order) {
        Object error = validate(order);
        if (error != null) {
            ResponseUtil.badArgument();
        }
        // 新增订单
        order.setOrderSn(productCode());
        order.setOrderTime(LocalDateTime.now());
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        if (order.getOrderStatus() == 201){
            order.setPayTime(LocalDateTime.now());
        }
        orderService.insert(order);

        // 新增订单商品
        Cart cart = new Cart();
        cart.setUserId(order.getUserId());
        cart.setChecked(1);
        List<Cart> carts = cartService.index(cart);
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(orderService.queryOne(order).getId());
        orderGoods.setOrderSn(order.getOrderSn());
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        for (Cart cartItem : carts) {
            orderGoods.setGoodsSn(cartItem.getGoodsSn());
            orderGoods.setGoodsName(cartItem.getGoodsName());
            orderGoods.setNumber(cartItem.getNumber());
            orderGoods.setPrice(cartItem.getPrice());
            // 实际付款暂时定为原价
            orderGoods.setPayPrice(cartItem.getPrice());
            orderGoods.setPicUrl(cartItem.getPicUrl());
            orderGoods.setUnit(cartItem.getUnit());
            orderGoodsService.insert(orderGoods);
        }

        // 删除已购买的购物车商品
        for (Cart cartItem : carts) {
            cartService.delete(cartItem.getId());
        }

        // 根据返回errno判断是否付款成功
        if (order.getOrderStatus() == 201){
            return ResponseUtil.ok();
        } else {
            return ResponseUtil.fail();
        }
    }

    /**
     * 查询订单
     *
     * @param order
     * @return
     */
    @GetMapping("/list")
    public Object index(Order order,Integer curNow) {
        if (curNow == null) {
            return ResponseUtil.badArgument();
        }
        if (order.getUserId() == null) {
            return ResponseUtil.unlogin();
        }
        List<Order> orders;
        // 分为四种查询情况：0全部、1待支付、2待收货、3待评价
        // 其中，待收货包括：201待发货、301已发货
        // 待评价包括：401已收货、402系统收货、501待评价
        if (curNow == 1){
            order.setOrderStatus(101);
            orders = orderService.index(order);
        } else if (curNow == 2){
            order.setOrderStatus(201);
            List<Order> orders1 = orderService.index(order);
            order.setOrderStatus(301);
            List<Order> orders2 = orderService.index(order);
            orders = mergeLists(orders1,orders2);
        } else if (curNow == 3){
            order.setOrderStatus(401);
            List<Order> orders1 = orderService.index(order);
            order.setOrderStatus(402);
            List<Order> orders2 = orderService.index(order);
            orders = mergeLists(orders1,orders2);
            order.setOrderStatus(501);
            List<Order>  orders3 = orderService.index(order);
            orders = mergeLists(orders,orders3);
        } else {
            orders = orderService.index(order);
        }

        return ResponseUtil.okList(orders);
    }

    /**
     * 删除订单
     *
     * @param order
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.deletedById(order.getId());
        return ResponseUtil.ok();
    }

    /**
     * 订单用户收货
     *
     * @param order
     * @return
     */
    @PostMapping("/confirm")
    public Object confirm(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        order.setOrderStatus(401);
        orderService.confirm(order);
        return ResponseUtil.ok();
    }

    /**
     * 支付订单
     *
     * @param order
     * @return
     */
    @PostMapping("/pay")
    public Object pay(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.pay(order);
        return ResponseUtil.ok();
    }

    /**
     * 查询订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object detail(Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        Order orderDetail = orderService.detail(id);
        return ResponseUtil.ok(orderDetail);
    }

    /**
     * 评价完成订单
     *
     * @param order
     * @return
     */
    @PostMapping("/over")
    public Object over(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.over(order);
        return ResponseUtil.ok();
    }

    /**
     * 查询订单数量
     *
     * @param order
     * @return
     */
    @GetMapping("/count")
    public Object count(@RequestBody Order order) {
        int total = orderService.count(order);
        return ResponseUtil.ok(total);
    }

    /**
     * 订单商品退款申请
     *
     * @param orderGoods
     * @return
     */
    @PostMapping("/drawback")
    public Object drawback(@RequestBody OrderGoods orderGoods) {
        if (orderGoods.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderGoodsService.questDrawback(orderGoods);
        return ResponseUtil.ok();
    }


    //验证订单信息
    public Object validate(Order order) {
        if (order.getUserId() == null) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(order.getUserName())) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(order.getMobile())) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileSimple(order.getMobile())) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(order.getAddress())) {
            return ResponseUtil.badArgument();
        }
        if (null == order.getAppointTime()) {
            return ResponseUtil.badArgument();
        }
        if (order.getNum() <= 0) {
            return ResponseUtil.badArgument();
        }

        return null;
    }
    // 生成订单号
    public String productCode(){
        // 获取当前日期
        Date currentDate = new Date();
        // 定义商品编号的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 生成商品编号
        return "O" + sdf.format(currentDate);
    }
    // 拼接两个数组
    public List<Order> mergeLists(List<Order> list1, List<Order> list2) {
        Order[] arr1 = list1.toArray(new Order[0]);
        Order[] arr2 = list2.toArray(new Order[0]);
        int length1 = arr1.length;
        int length2 = arr2.length;
        Order[] result = new Order[length1 + length2];
        System.arraycopy(arr1, 0, result, 0, length1);
        System.arraycopy(arr2, 0, result, length1, length2);
        return Arrays.asList(result);
    }

}

