package com.lyc.yl.service;

import com.lyc.yl.dao.OrderGoodsDao;
import com.lyc.yl.entity.OrderGoods;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class OrderGoodsService {
    @Resource
    private OrderGoodsDao orderGoodsDao;


    public List<OrderGoods> query(OrderGoods orderGoods) {
        return orderGoodsDao.query(orderGoods);
    }

    public void drawback(OrderGoods orderGoods) {
        orderGoods.setDrawback(1);
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsDao.update(orderGoods);
    }

    public void reject(OrderGoods orderGoods) {
        orderGoods.setDrawback(3);
        orderGoods.setDrawbackPrice(BigDecimal.valueOf(0));
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsDao.update(orderGoods);
    }

    public void questDrawback(OrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsDao.update(orderGoods);
    }

    public void insert(OrderGoods orderGoods) {
        orderGoodsDao.insert(orderGoods);
    }
}
