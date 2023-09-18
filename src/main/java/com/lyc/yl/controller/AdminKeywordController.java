package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Category;
import com.lyc.yl.entity.Keyword;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CategoryService;
import com.lyc.yl.service.KeywordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 关键词表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/keyword")
public class AdminKeywordController {

    @Resource
    private KeywordService keywordService;

    @RequiresPermissions("admin:keyword:list")
    @RequiresPermissionsDesc(menu = {"订单管理", "关键词"}, button = "查询")
    @GetMapping("/list")
    public Object list(Keyword keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "20") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Keyword> keywords = keywordService.query(keyword);
        return ResponseUtil.okList(keywords);
    }

    @RequiresPermissions("admin:keyword:create")
    @RequiresPermissionsDesc(menu = {"订单管理", "关键词"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody Keyword keyword) {
        if (keyword == null) {
            return ResponseUtil.badArgument();
        }
        keywordService.insert(keyword);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:keyword:update")
    @RequiresPermissionsDesc(menu = {"订单管理", "关键词"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Keyword keyword) {
        if (keyword.getId() == null) {
            return ResponseUtil.badArgument();
        }
        keywordService.update(keyword);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:keyword:delete")
    @RequiresPermissionsDesc(menu = {"订单管理", "关键词"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Keyword keyword) {
        if (keyword.getId() == null) {
            return ResponseUtil.badArgument();
        }
        keywordService.deletedById(keyword.getId());
        return ResponseUtil.ok();
    }

}

