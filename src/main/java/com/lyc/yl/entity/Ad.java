package com.lyc.yl.entity;

import java.time.LocalDateTime;

/**
 * @author zhaoxin
 */
public class Ad {
    /**
     * id
     */
    private Integer id;

    /**
     * 广告标题
     */
    private String name;

    /**
     * 广告内容
     */
    private String content;

    /**
     * 广告图片
     */
    private String url;

    /**
     * 广告位置 (1.首页)
     */
    private Integer position;

    /**
     * 活动链接
     */
    private String link;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * add_time
     */
    private LocalDateTime addTime;

    /**
     * update_time
     */
    private LocalDateTime updateTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
