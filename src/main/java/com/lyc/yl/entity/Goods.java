package com.lyc.yl.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhaoxin
 */
public class Goods {
    /**
     * id
     */
    private Integer id;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品所属类目id
     */
    private Integer categoryId;

    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 商品封面图片
     */
    private String picUrl;

    /**
     * 商品宣传图片列表
     */
    private String[] gallery;

    /**
     * 商品单位，例如件、盒
     */
    private String unit;

    /**
     * 零售价格
     */
    private BigDecimal price;

    /**
     * 专柜价格
     */
    private BigDecimal vipPrice;

    /**
     * 是否上架
     */
    private Integer isOnSale;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    private Integer isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    private Integer isHot;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String detail;

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

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
}
