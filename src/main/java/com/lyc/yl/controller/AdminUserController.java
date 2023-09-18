package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.Order;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.annotation.Sort;
import com.lyc.yl.entity.Collect;
import com.lyc.yl.entity.History;
import com.lyc.yl.entity.User;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CollectService;
import com.lyc.yl.service.HistoryService;
import com.lyc.yl.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户表后台管理
 *
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Resource
    private UserService userService;
    @Resource
    private CollectService collectService;
    @Resource
    private HistoryService historyService;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username,
                       String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<User> users = userService.queryUser(username, mobile, page, limit, sort, order);
        return ResponseUtil.okList(users);
    }


    @RequiresPermissions("admin:user:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseUtil.badArgument();
        }


        if (userService.updateById(user) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:user:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody User user) {
        if (user.getId() == null) {
            return ResponseUtil.badArgument();
        }

        userService.deleteById(user.getId());
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:user:collectList")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户管理"}, button = "会员收藏查询")
    @GetMapping("/collectList")
    public Object collectList(Collect collect ,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "20") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Collect> collects = collectService.query(collect);
        return ResponseUtil.okList(collects);
    }

    @RequiresPermissions("admin:user:historyList")
    @RequiresPermissionsDesc(menu = {"系统管理", "用户管理"}, button = "搜索历史查询")
    @GetMapping("/historyList")
    public Object historyList(History history ,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "20") Integer limit) {
        PageHelper.startPage(page, limit);
        List<History> histories = historyService.query(history);
        return ResponseUtil.okList(histories);
    }

}

