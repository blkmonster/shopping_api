package com.lyc.yl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyc.yl.dao.CollectDao;
import com.lyc.yl.dao.GoodsDao;
import com.lyc.yl.entity.Collect;
import com.lyc.yl.entity.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private CollectDao collectDao;


    public List<Goods> query(Goods goods) {
        return goodsDao.query(goods);
    }

    public void insert(Goods goods) {
        goods.setGoodsSn(productCode());
        goods.setAddTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goodsDao.insert(goods);
    }

    public void update(Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goodsDao.update(goods);
    }

    public void deletedById(Integer id) {
        goodsDao.deletedById(id);
    }

    public List<Goods> list() {
        return goodsDao.query(new Goods());
    }

    public Object detail(Integer id) {
        return goodsDao.detail(id);
    }

    public Goods detailByGoodsSn(String goodsSn) {
        return goodsDao.detailByGoodsSn(goodsSn);
    }

    public String productCode(){
        // 获取当前日期
        Date currentDate = new Date();
        // 定义商品编号的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 生成商品编号
        return "S" + sdf.format(currentDate);
    }

    public List<Goods> queryByKeyword(String keyword) {
        return goodsDao.queryByKeyword(keyword);
    }

    public Integer pageListCount() {
        return goodsDao.pageListCount();
    }

    public List<Goods> queryList(Integer userId) {
        Goods goods = new Goods();
        // 热门商品
        goods.setIsHot(1);
        goods.setIsOnSale(1);
        List<Goods> goodsList = goodsDao.query(goods);
        // 新品
        goods.setIsHot(0);
        goods.setIsNew(1);
        List<Goods> goods1 = goodsDao.query(goods);
        // 拼接得到的集合
        goodsList = mergeLists(goodsList,goods1);
        // 用户收藏
        if (userId != null){
            Collect collect = new Collect();
            collect.setUserId(userId);
            List<Collect> collects = collectDao.query(collect);
            if (!collects.isEmpty()){
                List<Goods> goods2 = new ArrayList<>(collects.size());
                for (Collect collect1 : collects){
                    Goods goodsOne = goodsDao.detailByGoodsSn(collect1.getGoodsSn());
                    if (goodsOne != null) {
                        goods2.add(goodsOne);
                    }
                }
                goodsList = mergeLists(goods2,goodsList);
            }
        }
        return goodsList;

    }

    // 拼接两个数组
    private List<Goods> mergeLists(List<Goods> list1, List<Goods> list2) {
        Goods[] arr1 = list1.toArray(new Goods[0]);
        Goods[] arr2 = list2.toArray(new Goods[0]);
        int length1 = arr1.length;
        int length2 = arr2.length;
        Goods[] result = new Goods[length1 + length2];
        System.arraycopy(arr1, 0, result, 0, length1);
        System.arraycopy(arr2, 0, result, length1, length2);
        return Arrays.asList(result);
    }
}
