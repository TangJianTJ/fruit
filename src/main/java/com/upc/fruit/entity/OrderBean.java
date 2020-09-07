package com.upc.fruit.entity;

import lombok.Data;

@Data
public class OrderBean {
    private int id;
    private String orderNumber;
    private String orderPay;
    private int goodsCount;
    private int orderState;
    private int reviewState;//是否评价
    private String receiveTime;
    private Object receivePlace;
    private String createTime;
    private String updateTime;
    private ReceiveAddressBean receiveAddress;
    private User user;
    private GoodsDetail goods;
    private Boolean isSend;
    public OrderBean(Orders orders)
    {
        this.id=orders.getId();
        this.orderNumber=orders.getOrderNumber();
        this.orderPay=orders.getOrderPay();
        this.goodsCount=orders.getGoodsCount();
        this.orderState=orders.getOrderState();
        this.reviewState=orders.getReviewState();
        this.receiveTime=orders.getReceiveTime();
        this.receivePlace=orders.getReceivePlace();
        this.createTime=orders.getCreateTime();
        this.updateTime=orders.getUpdateTime();
        this.isSend=orders.getIsSend();
    }
}
