package com.lyc.yl.service;

import com.lyc.yl.dao.CategoryDao;
import com.lyc.yl.entity.Category;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class CategoryService {
    @Resource
    private CategoryDao categoryDao;


    public List<Category> query(Category category) {
        return categoryDao.query(category);
    }

    public void insert(Category category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryDao.insert(category);
    }

    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryDao.update(category);
    }

    public void deletedById(Integer id) {
        categoryDao.deletedById(id);
    }

    public List<Category> list() {
        return categoryDao.query(new Category());
    }

    public Integer pageListCount() {
        return categoryDao.pageListCount();
    }
}
