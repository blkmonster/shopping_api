package com.lyc.yl.entity;

import java.time.LocalDateTime;

/**
 * @author zhaoxin
 */
public class Category {
    /**
     * id
     */
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 标签
     */
    private String keywords;

    /**
     * 类目介绍
     */
    private String desc;

    /**
     * 父类目id
     */
    private Integer pid;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目等级
     */
    private String level;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    private int deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", desc='" + desc + '\'' +
                ", pid=" + pid +
                ", iconUrl='" + iconUrl + '\'' +
                ", level=" + level +
                ", sortOrder=" + sortOrder +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
