package com.lyc.yl.service;

import com.lyc.yl.dao.AdDao;
import com.lyc.yl.entity.Ad;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author zhaoxin
 */
@Service
public class AdService {
    @Resource
    private AdDao adDao;


    public List<Ad> query(Ad ad) {
        return adDao.query(ad);
    }

    public void insert(Ad ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adDao.insert(ad);
    }

    public void update(Ad ad) {
        ad.setUpdateTime(LocalDateTime.now());
        adDao.update(ad);
    }

    public void deletedById(Integer id) {
        adDao.deletedById(id);
    }

}
