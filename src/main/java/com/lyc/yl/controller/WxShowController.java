package com.lyc.yl.controller;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.entity.Ad;
import com.lyc.yl.entity.Keyword;
import com.lyc.yl.result.ResponseUtil;
import com.lyc.yl.service.AdService;
import com.lyc.yl.service.KeywordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 小程序所需要展示信息管理层
 * @author zhaoxin
 */
@RestController
@RequestMapping("/wx/show")
public class WxShowController {

    @Resource
    private KeywordService keywordService;
    @Resource
    private AdService adService;

    /**
     * 查询关键字
     *
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @GetMapping("/keywordList")
    public Object keywordList(Keyword keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Keyword> keywords = keywordService.query(keyword);
        return ResponseUtil.okList(keywords);
    }

    /**
     * 查询首页广告
     *
     * @param page
     * @param limit
     * @param ad
     * @return
     */
    @GetMapping("/adList")
    public Object adList(Ad ad,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer limit) {
        ad.setEnabled(1);
        PageHelper.startPage(page, limit);
        List<Ad> adList = adService.query(ad);
        return ResponseUtil.okList(adList);
    }



}

