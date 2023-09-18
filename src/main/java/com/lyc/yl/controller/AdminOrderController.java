package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Order;
import com.lyc.yl.entity.OrderGoods;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.OrderGoodsService;
import com.lyc.yl.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 订单表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderGoodsService orderGoodsService;

    /**
     * 查询订单
     *
     * @param page
     * @param limit
     * @return
     */
    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Integer userId,String orderSn,String userName,Integer orderStatus,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
        PageHelper.startPage(page, limit);
        List<Order> orderList = orderService.query(userId,orderSn,userName,orderStatus,start,end);
        return ResponseUtil.okList(orderList);
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:order:detail")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "订单详情")
    @GetMapping("/detail")
    public Object detail(Integer id) {
        if (id == null || id == 0) {
            return ResponseUtil.badArgument();
        }
        Order order =orderService.detail(id);
        return ResponseUtil.ok(order);
    }

    /**
     * 订单发货
     *
     * @param order
     * @return
     */
    @RequiresPermissions("admin:order:ship")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "订单发货")
    @PostMapping("/ship")
    public Object ship(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.ship(order);
        return ResponseUtil.ok();
    }

    /**
     * 订单收货
     *
     * @param order
     * @return
     */
    @RequiresPermissions("admin:order:confirm")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "订单收货")
    @PostMapping("/confirm")
    public Object confirm(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.confirm(order);
        return ResponseUtil.ok();
    }

    /**
     * 删除订单
     *
     * @param order
     * @return
     */
    @RequiresPermissions("admin:order:delete")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Order order) {
        if (order.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderService.deletedById(order.getId());
        return ResponseUtil.ok();
    }


    /**
     * 查询订单商品
     *
     * @param page
     * @param limit
     * @return
     */
    @RequiresPermissions("admin:order:orderGoodsList")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "订单商品查询")
    @GetMapping("/orderGoodsList")
    public Object orderGoodsList(OrderGoods orderGoods,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        PageHelper.startPage(page, limit);
        List<OrderGoods> orderGoodsList = orderGoodsService.query(orderGoods);
        return ResponseUtil.okList(orderGoodsList);
    }


    /**
     * 订单商品退款
     *
     * @param orderGoods
     * @return
     */
    @RequiresPermissions("admin:order:drawback")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "退款")
    @PostMapping("/drawback")
    public Object drawback(@RequestBody OrderGoods orderGoods) {
        if (orderGoods.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderGoodsService.drawback(orderGoods);
        return ResponseUtil.ok();
    }


    /**
     * 订单商品退款驳回
     *
     * @param orderGoods
     * @return
     */
    @RequiresPermissions("admin:order:reject")
    @RequiresPermissionsDesc(menu = {"订单管理", "订单管理"}, button = "驳回")
    @PostMapping("/reject")
    public Object reject(@RequestBody OrderGoods orderGoods) {
        if (orderGoods.getId() == null) {
            return ResponseUtil.badArgument();
        }
        orderGoodsService.reject(orderGoods);
        return ResponseUtil.ok();
    }
}

