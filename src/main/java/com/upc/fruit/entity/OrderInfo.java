package com.upc.fruit.entity;

import lombok.Data;

/**
 * 管理系统订单列表显示信息类
 */
@Data
public class OrderInfo {
    private Integer id;
    private String userName;
    private String orderNumber;
    private String creatTime;
    private String updateTime;
    private boolean isSend; //是否发货 1是，0否
    private String isReceive;//是否收货
    private String orderPay;
    private String address;//市县地址
    private String street;//具体地址
    private Integer addressId;
    private String goodName;
    private int goodCount;

    public OrderInfo(OrderBean orders) {
        this.id = orders.getId();
        this.userName=orders.getUser().getNickName();
        this.orderNumber=orders.getOrderNumber();
        this.creatTime=orders.getCreateTime();
        this.updateTime=orders.getUpdateTime();
        this.orderPay=orders.getOrderPay();
        this.address=orders.getReceiveAddress().getAddress();
        this.street=orders.getReceiveAddress().getStreet();
        this.addressId=orders.getReceiveAddress().getId();
        this.goodName=orders.getGoods().getGoodsName();
        this.goodCount=orders.getGoodsCount();
        this.isSend=orders.getIsSend();
        if(orders.getOrderState()==1)
            this.isReceive="是";
    }
}
