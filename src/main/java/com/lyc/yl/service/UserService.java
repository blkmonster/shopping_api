package com.lyc.yl.service;

import com.github.pagehelper.PageHelper;
import com.lyc.yl.dao.UserMapper;
import com.lyc.yl.entity.User;
import com.lyc.yl.entity.UserExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;


    public User findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User queryByOid(String openId) {
        UserExample example = new UserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    public void add(User user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    public int updateById(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int count() {
        UserExample example = new UserExample();
        example.or().andDeletedEqualTo(false);

        return (int) userMapper.countByExample(example);
    }

    public List<User> queryByUsername(String username) {
        UserExample example = new UserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public List<User> queryByMobile(String mobile) {
        UserExample example = new UserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public int deleteById(Integer id) {
        return userMapper.logicalDeleteByPrimaryKey(id);
    }


    //登录状态
    public int loginState(String userName){
        return  userMapper.loginState(userName);
    }

    //后台管理：查询
    public List<User> queryUser(String nickname, String mobile, Integer page, Integer limit, String sort, String order) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(nickname)) {
            criteria.andNicknameLike("%" + nickname + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileLike("%" + mobile + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return userMapper.selectByExample(example);
    }

    public Integer pageListCount() {
        return userMapper.pageListCount();
    }
}
