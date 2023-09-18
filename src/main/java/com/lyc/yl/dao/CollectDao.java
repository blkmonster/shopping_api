package com.lyc.yl.dao;


import com.lyc.yl.entity.Collect;

import java.util.List;

public interface CollectDao {

    List<Collect> query(Collect collect);

    void delete(Collect collect);

    void insert(Collect collect);
}

