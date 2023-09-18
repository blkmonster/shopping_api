package com.lyc.yl.service;

import com.lyc.yl.dao.StatDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StatService {
    @Resource
    private StatDao statDao;


    @SuppressWarnings("rawtypes")
	public List<Map> statUser() {
        return statDao.statUser();
    }
    @SuppressWarnings("rawtypes")
    public List<Map> statOrder() {
        return statDao.statOrder();
    }
    @SuppressWarnings("rawtypes")
    public List<Map> statGoods() {
        return statDao.statGoods();
    }
}
