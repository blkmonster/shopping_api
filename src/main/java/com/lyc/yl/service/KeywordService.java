package com.lyc.yl.service;

import com.lyc.yl.dao.CategoryDao;
import com.lyc.yl.dao.KeywordDao;
import com.lyc.yl.entity.Category;
import com.lyc.yl.entity.Keyword;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class KeywordService {
    @Resource
    private KeywordDao keywordDao;


    public List<Keyword> query(Keyword keyword) {
        return keywordDao.query(keyword);
    }

    public void insert(Keyword keyword) {
        keyword.setAddTime(LocalDateTime.now());
        keyword.setUpdateTime(LocalDateTime.now());
        keywordDao.insert(keyword);
    }

    public void update(Keyword keyword) {
        keyword.setUpdateTime(LocalDateTime.now());
        keywordDao.update(keyword);
    }

    public void deletedById(Integer id) {
        keywordDao.deletedById(id);
    }

}
