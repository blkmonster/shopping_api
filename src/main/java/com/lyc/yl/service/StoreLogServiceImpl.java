package com.lyc.yl.service;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.dao.StoreLogDao;
import com.lyc.yl.entity.LitemallLog;
import com.lyc.yl.entity.LitemallLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作日志表(StoreLog)表服务实现类
 *
 * @author makejava
 * @since 2022-12-20 10:01:30
 */
@Service
public class StoreLogServiceImpl{
    @Resource
    private StoreLogDao logMapper;

    public void deleteById(Integer id) {
        logMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallLog log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(log);
    }

    public List<LitemallLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        LitemallLogExample example = new LitemallLogExample();
        LitemallLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return logMapper.selectByExample(example);
    }

}
