package com.lyc.yl.service;

import com.lyc.yl.dao.CartDao;
import com.lyc.yl.entity.Cart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class CartService {
    @Resource
    private CartDao cartDao;


    public void insertOrUpdate(Cart cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cartDao.insertOrUpdate(cart);
    }

    public int count(Cart cart) {
        List<Cart> carts= cartDao.index(cart);
        int total = 0;
        for (Cart cart1 : carts) {
            total += cart1.getNumber();
        }
        return total;
    }

    public List<Cart> index(Cart cart) {
        return cartDao.index(cart);
    }

    public void addOrCut(Cart cart) {
        if (cart.getNumber() == 1){
            cartDao.add(cart);
        } else {
            cartDao.cut(cart);
        }
    }

    public void delete(Integer id) {
        cartDao.delete(id);
    }

    public void checked(Cart cart) {
        cartDao.checked(cart);
    }
}
