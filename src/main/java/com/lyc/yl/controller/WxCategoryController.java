package com.lyc.yl.controller;

import com.lyc.yl.entity.Category;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CategoryService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类目表前端管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/category")
public class WxCategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有商品类目
     *
     * @return 所有商品类目
     */
    @GetMapping("/list")
    public Object list() {

        List<Category> categories = categoryService.list();
        return ResponseUtil.okList(categories);
    }

    /**
     * 获取一级商品类目
     *
     * @return 一级商品类目
     */
    @GetMapping("/L1")
    public Object categoryL1() {
        Category category = new Category();
        category.setLevel("一级类目");
        List<Category> categories = categoryService.query(category);
        return ResponseUtil.okList(categories);
    }

    /**
     * 根据一级类目获取二级商品类目
     *
     * @return 对应二级商品类目
     */
    @GetMapping("/L2")
    public Object categoryL2(Integer pid) {
        Category category = new Category();
        category.setLevel("二级类目");
        category.setPid(pid);
        List<Category> categories = categoryService.query(category);
        return ResponseUtil.okList(categories);
    }


}

