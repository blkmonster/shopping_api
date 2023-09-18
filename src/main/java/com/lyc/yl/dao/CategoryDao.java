package com.lyc.yl.dao;


import com.lyc.yl.entity.Category;

import java.util.List;

public interface CategoryDao {


    List<Category> query(Category category);

    void insert(Category category);

    void update(Category category);

    void deletedById(Integer id);

    Integer pageListCount();
}

