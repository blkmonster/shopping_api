package com.lyc.yl.service;

import com.lyc.yl.dao.OrderDao;
import com.lyc.yl.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class OrderService {
    @Resource
    private OrderDao orderDao;


    public List<Order> query(Integer userId,String orderSn,String userName,Integer orderStatus, LocalDateTime start, LocalDateTime end) {
        return orderDao.query(userId,orderSn,userName,orderStatus,start,end);
    }

    public void deletedById(Integer id) {
        orderDao.deletedById(id);
    }

    public Order detail(Integer id) {
        return orderDao.detail(id);
    }

    public void ship(Order order) {
        order.setStartTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderDao.ship(order);
    }

    public void confirm(Order order) {
        order.setConfirmTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderDao.ship(order);
    }

    public void pay(Order order) {
        order.setOrderStatus(201);
        order.setPayTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderDao.ship(order);
    }

    public void insert(Order order) {
        orderDao.insert(order);
    }

    public int count(Order order) {
        return orderDao.count(order);
    }

    public List<Order> index(Order order) {
        return orderDao.index(order);
    }

    public Order queryOne(Order order){
        return orderDao.queryOne(order);
    }

    public void over(Order order) {
        order.setOrderStatus(502);
        order.setUpdateTime(LocalDateTime.now());
        orderDao.ship(order);
    }

    public Integer pageListCount() {
        return orderDao.pageListCount();
    }
}
