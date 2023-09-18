package com.lyc.yl.controller;


import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.StatVo;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计图表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/stat")
public class AdminStatController {

    @Resource
    private StatService statService;
    @Resource
    private UserService userService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private OrderService orderService;

    @SuppressWarnings("rawtypes")
    @RequiresPermissions("admin:stat:user")
    @RequiresPermissionsDesc(menu = {"统计管理", "用户统计"}, button = "查询")
    @GetMapping("/user")
    public Object statUser() {
        List<Map> rows = statService.statUser();
        String[] columns = new String[]{"day", "users"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }

    @SuppressWarnings("rawtypes")
    @RequiresPermissions("admin:stat:order")
    @RequiresPermissionsDesc(menu = {"统计管理", "订单统计"}, button = "查询")
    @GetMapping("/order")
    public Object statOrder() {
        List<Map> rows = statService.statOrder();
        String[] columns = new String[]{"day", "orders", "customers", "amount", "pcr"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);

        return ResponseUtil.ok(statVo);
    }

    @SuppressWarnings("rawtypes")
    @RequiresPermissions("admin:stat:goods")
    @RequiresPermissionsDesc(menu = {"统计管理", "商品统计"}, button = "查询")
    @GetMapping("/goods")
    public Object statGoods() {
        List<Map> rows = statService.statGoods();
        String[] columns = new String[]{"day", "orders", "products", "amount"};
        StatVo statVo = new StatVo();
        statVo.setColumns(columns);
        statVo.setRows(rows);
        return ResponseUtil.ok(statVo);
    }

    @GetMapping("/dashboard")
    public Object dashboard() {
        Integer userTotal = userService.pageListCount();
        Integer goodsTotal = goodsService.pageListCount();
        Integer productTotal = categoryService.pageListCount();
        Integer orderTotal = orderService.pageListCount();
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("userTotal", userTotal);
        data.put("goodsTotal", goodsTotal);
        data.put("productTotal", productTotal);
        data.put("orderTotal", orderTotal);
        return ResponseUtil.ok(data);
    }

}

