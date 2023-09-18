package com.lyc.yl.dao;

import com.lyc.yl.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDao {


    List<Order> query(Integer userId,String orderSn,String userName,Integer orderStatus, LocalDateTime start, LocalDateTime end);

    void deletedById(Integer id);

    Order detail(Integer id);

    void ship(Order order);

    int count(Order order);

    List<Order> index(Order order);

    void insert(Order order);

    Order queryOne(Order order);

    Integer pageListCount();
}

