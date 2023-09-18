package com.lyc.yl.service;

import com.lyc.yl.dao.CollectDao;
import com.lyc.yl.entity.Collect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class CollectService {
    @Resource
    private CollectDao collectDao;


    public List<Collect> query(Collect collect) {
        return collectDao.query(collect);
    }

    public void delete(Collect collect) {
        collectDao.delete(collect);
    }

    public void insert(Collect collect) {
        collect.setAddTime(LocalDateTime.now());
        collect.setUpdateTime(LocalDateTime.now());
        collectDao.insert(collect);
    }
}
