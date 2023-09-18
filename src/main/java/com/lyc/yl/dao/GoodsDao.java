package com.lyc.yl.dao;


import com.lyc.yl.entity.Goods;

import java.util.List;

public interface GoodsDao {


    List<Goods> query(Goods goods);

    void insert(Goods goods);

    void update(Goods goods);

    void deletedById(Integer id);

    Goods detail(Integer id);

    List<Goods> queryByKeyword(String keyword);

    Goods detailByGoodsSn(String goodsSn);

    Integer pageListCount();
}

