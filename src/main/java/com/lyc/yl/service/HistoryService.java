package com.lyc.yl.service;

import com.lyc.yl.dao.HistoryDao;
import com.lyc.yl.entity.Collect;
import com.lyc.yl.entity.History;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class HistoryService {
    @Resource
    private HistoryDao historyDao;


    public List<History> query(History history) {
        return historyDao.query(history);
    }

    public void insertOrUpdate(History history) {
        //通过userId 和 keyword 查找，有则修改，无则增加
        history.setAddTime(LocalDateTime.now());
        historyDao.insertOrUpdate(history);
    }
}
