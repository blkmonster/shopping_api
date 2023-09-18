package com.lyc.yl.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyc.yl.entity.Collect;
import com.lyc.yl.entity.Goods;
import com.lyc.yl.entity.Order;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.CollectService;
import com.lyc.yl.service.GoodsService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * 商品表前端管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private CollectService collectService;

    /**
     * 查询商品
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/pidList")
    public Object pidList(@NotNull Integer pid,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (pid == null) {
            return ResponseUtil.badArgument();
        }
        Goods goods = new Goods();
        goods.setCategoryId(pid);
        goods.setIsOnSale(1);
        PageHelper.startPage(page, limit);
        List<Goods> goods1 = goodsService.query(goods);
        return ResponseUtil.okList(goods1);
    }

    /**
     * 查询商品
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/byKeyword")
    public Object list(String keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        if (keyword == null || "".equals(keyword)) {
            return ResponseUtil.badArgument();
        }
        PageHelper.startPage(page, limit);
        List<Goods> goods1 = goodsService.queryByKeyword(keyword);
        return ResponseUtil.okList(goods1);
    }


    /**
     * 商品详情
     *
     * @param goodsSn
     * @return
     */
    @GetMapping("/detail")
    public Object detail(String goodsSn) {
        if (goodsSn == null || "".equals(goodsSn)){
            return ResponseUtil.badArgument();
        }
        return ResponseUtil.ok(goodsService.detailByGoodsSn(goodsSn));
    }

    /**
     * 展示商品，根据商品热度、首发、用户收藏、用户搜索记录来推荐
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    public Object list( Integer userId,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer limit) {
        List<Goods> goodsList =goodsService.queryList(userId);
        // 数据来自多个方向，手动进行分页
        int total = goodsList.size();
        int pageCount = getPageCount(total, limit);
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, total);
        Page page1 = new Page(page,limit);
        page1.setPages(pageCount);
        if (startIndex > endIndex){
            page1.setTotal(0);
            return ResponseUtil.okList(null,page1);
        }
        List<Goods> pagedGoodsList = goodsList.subList(startIndex, endIndex);
        page1.setTotal(pagedGoodsList.size());
        return ResponseUtil.okList(pagedGoodsList,page1);
    }

    public int getPageCount(int total, int pageSize) {
        if (total % pageSize == 0) {
            return total / pageSize;
        } else {
            return total / pageSize + 1;
        }
    }


}


