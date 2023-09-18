package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Ad;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.AdService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 广告表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/ad")
public class AdminAdController {

    @Resource
    private AdService adService;

    @RequiresPermissions("admin:ad:list")
    @RequiresPermissionsDesc(menu = {"推广管理", "广告管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Ad ad,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "20") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Ad> adList = adService.query(ad);
        return ResponseUtil.okList(adList);
    }

    @RequiresPermissions("admin:ad:create")
    @RequiresPermissionsDesc(menu = {"推广管理", "广告管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody Ad ad) {
        if (ad == null) {
            return ResponseUtil.badArgument();
        }
        adService.insert(ad);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ad:update")
    @RequiresPermissionsDesc(menu = {"推广管理", "广告管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Ad ad) {
        if (ad.getId() == null) {
            return ResponseUtil.badArgument();
        }
        adService.update(ad);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:ad:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "广告管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Ad ad) {
        if (ad.getId() == null) {
            return ResponseUtil.badArgument();
        }
        adService.deletedById(ad.getId());
        return ResponseUtil.ok();
    }

}

