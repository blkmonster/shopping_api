package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.entity.Collect;
import com.lyc.yl.entity.Goods;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CollectService;
import com.lyc.yl.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 商品收藏表小程序管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/collect")
public class WxCollectController {

    @Resource
    private CollectService collectService;
    @Resource
    private GoodsService goodsService;

    /**
     * 查询收藏商品
     *
     * @param page
     * @param limit
     * @param collect
     * @return
     */
    @GetMapping("/list")
    public Object list(Collect collect,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (collect.getUserId() == null) {
            return ResponseUtil.unlogin();
        }
        List<Collect> collects = collectService.query(collect);
        if (collects == null){
            return ResponseUtil.okList(null);
        }
        List<Goods> goodsList = new ArrayList<>(collects.size());
        for (Collect collect1 : collects){
            Goods goods = goodsService.detailByGoodsSn(collect1.getGoodsSn());
            if (goods != null) {
                goodsList.add(goods);
            }
        }
        PageHelper.startPage(page, limit);
        return ResponseUtil.okList(goodsList);
    }


    /**
     * 删除收藏商品
     *
     * @param collect
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody Collect collect) {
        if (collect.getId() == null && collect.getGoodsSn() == null) {
            return ResponseUtil.badArgument();
        }
        collectService.delete(collect);
        return ResponseUtil.ok();
    }

    /**
     * 添加收藏商品
     *
     * @param collect
     * @return
     */
    @PostMapping("/create")
    public Object create(@RequestBody Collect collect) {
        if (collect.getGoodsSn() == null || "".equals(collect.getGoodsSn())) {
            return ResponseUtil.badArgument();
        }
        if (collect.getUserId() == null) {
            return ResponseUtil.unlogin();
        }
        collectService.insert(collect);
        return ResponseUtil.ok();
    }

    /**
     * 查询商品是否在用户收藏中
     *
     * @param collect
     * @return
     */
    @GetMapping("/isCollect")
    public Object isCollect(Collect collect) {
        if (collect.getUserId() == null || collect.getGoodsSn() == null || "".equals(collect.getGoodsSn())) {
            return ResponseUtil.badArgument();
        }
        List<Collect> collects = collectService.query(collect);
        if (collects.isEmpty()){
            return ResponseUtil.ok(false);
        } else {
            return ResponseUtil.ok(true);
        }
    }


}

