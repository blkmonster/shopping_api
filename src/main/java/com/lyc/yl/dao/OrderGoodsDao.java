package com.lyc.yl.dao;

import com.lyc.yl.entity.OrderGoods;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderGoodsDao {


    List<OrderGoods> query(OrderGoods orderGoods);

    void update(OrderGoods orderGoods);

    void insert(OrderGoods orderGoods);
}

