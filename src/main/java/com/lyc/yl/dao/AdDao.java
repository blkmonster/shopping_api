package com.lyc.yl.dao;


import com.lyc.yl.entity.Ad;

import java.util.List;

public interface AdDao {


    List<Ad> query(Ad ad);

    void insert(Ad ad);

    void update(Ad ad);

    void deletedById(Integer id);
}

