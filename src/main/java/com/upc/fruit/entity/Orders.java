package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Orders {
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
    private Integer userId;
    private Integer goodsId;
    private Integer addressId;
    private Boolean isSend;
    private int isDelete; //用户是否删除
}
