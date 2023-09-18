package com.lyc.yl.dao;


import com.lyc.yl.entity.Keyword;

import java.util.List;

public interface KeywordDao {


    List<Keyword> query(Keyword keyword);

    void insert(Keyword keyword);

    void update(Keyword keyword);

    void deletedById(Integer id);
}

