package com.upc.fruit.entity;

import lombok.Data;

@Data
public class ReceiveAddressBean {
    private int id;
    private String address;
    private String street;
    private String receiveUser;
    private String receivePhoneNumber;
    private String createTime;
    private String updateTime;
    private int userId;
    private User user;

    public ReceiveAddressBean(User user) {
        this.user = user;
    }
    public ReceiveAddressBean(ReceiveAddress receiveAddress)
    {
        this.address=receiveAddress.getAddress();
        this.id=receiveAddress.getId();
        this.createTime=receiveAddress.getCreateTime();
        this.updateTime=receiveAddress.getUpdateTime();
        this.street=receiveAddress.getStreet();
        this.userId=receiveAddress.getUserId();
        this.receivePhoneNumber=receiveAddress.getReceivePhoneNumber();
        this.receiveUser=receiveAddress.getReceiveUser();
    }
}
