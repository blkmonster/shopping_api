package com.lyc.yl.service;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.dao.StoreAdminDao;
import com.lyc.yl.entity.LitemallAdminExample;
import com.lyc.yl.entity.StoreAdmin;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 管理员表(StoreAdmin)表服务实现类
 *
 * @author makejava
 * @since 2022-12-20 09:58:08
 */
@Service
public class StoreAdminServiceImpl {
    private final StoreAdmin.Column[] result = new StoreAdmin.Column[]{StoreAdmin.Column.id, StoreAdmin.Column.username, StoreAdmin.Column.avatar, StoreAdmin.Column.roleIds};
    @Resource
    private StoreAdminDao adminMapper;


    public List<StoreAdmin> findAdmin(String username) {
        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public StoreAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<StoreAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        LitemallAdminExample example = new LitemallAdminExample();
        LitemallAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(StoreAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(StoreAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public StoreAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<StoreAdmin> all() {
        LitemallAdminExample example = new LitemallAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
