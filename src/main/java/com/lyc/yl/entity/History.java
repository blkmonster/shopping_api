package com.lyc.yl.entity;

import java.time.LocalDateTime;

/**
 * @author zhaoxin
 */
public class History {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户表的用户id
     */
    private Integer userId;

    /**
     * 搜索词
     */
    private String keyword;

    /**
     * 搜索的次数
     */
    private Integer count;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
