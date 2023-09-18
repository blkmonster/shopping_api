package com.lyc.yl.dao;


import com.lyc.yl.entity.Cart;

import java.util.List;

public interface CartDao {

    void insertOrUpdate(Cart cart);

    void add(Cart cart);

    void cut(Cart cart);

    void delete(Integer id);

    void checked(Cart cart);

    List<Cart> index(Cart cart);
}

