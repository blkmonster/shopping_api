package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.annotation.RequiresPermissionsDesc;
import com.lyc.yl.entity.Goods;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 商品表后台管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 查询商品
     *
     * @param page
     * @param limit
     * @return
     */
    @RequiresPermissions("admin:goods:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(Goods goods,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (goods == null) {
            return ResponseUtil.badArgument();
        }
        PageHelper.startPage(page, limit);
        List<Goods> goods1 = goodsService.query(goods);
        return ResponseUtil.okList(goods1);
    }


    /**
     * 编辑商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody Goods goods) {
        if (goods.getId() == null) {
            return ResponseUtil.badArgument();
        }
        goodsService.update(goods);
        return ResponseUtil.ok();
    }


    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Goods goods) {
        if (goods.getId() == null) {
            return ResponseUtil.badArgument();
        }
        goodsService.deletedById(goods.getId());
        return ResponseUtil.ok();
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "上架")
    @PostMapping("/create")
    public Object create(@RequestBody Goods goods) {
        goodsService.insert(goods);
        return ResponseUtil.ok();
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:goods:detail")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return ResponseUtil.ok(goodsService.detail(id));
    }

}

