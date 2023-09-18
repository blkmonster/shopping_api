package com.lyc.yl.dao;


import com.lyc.yl.entity.History;

import java.util.List;

public interface HistoryDao {

    List<History> query(History history);

    void insertOrUpdate(History history);
}

