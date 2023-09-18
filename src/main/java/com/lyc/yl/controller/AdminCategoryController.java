package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Category;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 类目表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Resource
    private CategoryService categoryService;

    @RequiresPermissions("admin:category:query")
    @RequiresPermissionsDesc(menu = {"系统管理", "商品类目管理"}, button = "查询")
    @GetMapping("/query")
    public Object list(Category category,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "20") Integer limit) {
        if (category == null) {
            return ResponseUtil.badArgument();
        }
        PageHelper.startPage(page, limit);
        List<Category> categories = categoryService.query(category);
        return ResponseUtil.okList(categories);
    }

    @GetMapping("/queryL1")
    public Object categoryL1() {
        Category category = new Category();
        category.setLevel("一级类目");
        List<Category> categories = categoryService.query(category);
        return ResponseUtil.okList(categories);
    }
    @GetMapping("/queryL2")
    public Object categoryL2() {
        Category category = new Category();
        category.setLevel("二级类目");
        List<Category> categories = categoryService.query(category);
        return ResponseUtil.okList(categories);
    }

    @RequiresPermissions("admin:category:insert")
    @RequiresPermissionsDesc(menu = {"系统管理", "商品类目管理"}, button = "添加")
    @PostMapping("/insert")
    public Object create(@RequestBody Category category) {
        if (category == null) {
            return ResponseUtil.badArgument();
        }
        categoryService.insert(category);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "商品类目管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Category category) {
        if (category.getId() == null) {
            return ResponseUtil.badArgument();
        }
        if ("一级类目".equals(category.getLevel())){
            category.setPid(null);
        }
        categoryService.update(category);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "商品类目管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Category category) {
        if (category.getId() == null) {
            return ResponseUtil.badArgument();
        }
        categoryService.deletedById(category.getId());
        return ResponseUtil.ok();
    }

}

