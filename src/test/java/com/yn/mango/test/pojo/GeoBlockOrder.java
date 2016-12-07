package com.yn.mango.test.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangnan on 2016/11/23.
 */
public class GeoBlockOrder implements Serializable {

    private static final long serialVersionUID = 5788362568593864260L;

    /* 数据库自增ID */
    private Long id;
    /*城市简写*/
    private String city;
    /*区域 格式x_y*/
    private String blockId;
    /*订单创建时间*/
    private Date orderTime;
    /*订单ID*/
    private Long orderId;
    private Long userId;
    /*记录创建时间*/
    private Date createTime;
    /*创建时间 分钟*/
    private Long minuteIndex;

    public GeoBlockOrder() {

    }

    public Long getMinuteIndex() {
        return minuteIndex;
    }

    public void setMinuteIndex(Long minuteIndex) {
        this.minuteIndex = minuteIndex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
