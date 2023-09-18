package com.lyc.yl.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 订单商品表
 * @author zhaoxin
 * @date 2023-03-20
 */

public class OrderGoods {

    /**
     * id
     */
    private Integer id;

    /**
     * 订单表的订单id
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品货品的购买数量
     */
    private Integer number;

    /**
     * 商品售价
     */
    private BigDecimal price;

    /**
     * 实际付款
     */
    private BigDecimal payPrice;

    /**
     * 商品货品图片或者商品图片
     */
    private String picUrl;

    /**
     * 单位
     */
    private String unit;

    /**
     * 是否退款：0.未退款 1.已退款 2.申请退款 3.驳回退款
     */
    private Integer drawback;

    /**
     * 退款金额
     */
    private BigDecimal drawbackPrice;

    /**
     * 退款原因
     */
    private String drawbackCause;

    /**
     * 问题描述
     */
    private String drawbackMessage;

    /**
     * 退款原因详情图片列表，采用json数组格式
     */
    private String[] drawbackPhoto;

    /**
     * 表里面的评论id。
     */
    private Integer comment;

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
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getDrawback() {
        return drawback;
    }

    public void setDrawback(Integer drawback) {
        this.drawback = drawback;
    }

    public BigDecimal getDrawbackPrice() {
        return drawbackPrice;
    }

    public void setDrawbackPrice(BigDecimal drawbackPrice) {
        this.drawbackPrice = drawbackPrice;
    }

    public String getDrawbackCause() {
        return drawbackCause;
    }

    public void setDrawbackCause(String drawbackCause) {
        this.drawbackCause = drawbackCause;
    }

    public String getDrawbackMessage() {
        return drawbackMessage;
    }

    public void setDrawbackMessage(String drawbackMessage) {
        this.drawbackMessage = drawbackMessage;
    }

    public String[] getDrawbackPhoto() {
        return drawbackPhoto;
    }

    public void setDrawbackPhoto(String[] drawbackPhoto) {
        this.drawbackPhoto = drawbackPhoto;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
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
