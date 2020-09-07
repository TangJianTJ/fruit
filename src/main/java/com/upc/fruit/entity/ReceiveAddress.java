package com.upc.fruit.entity;

import lombok.Data;

@Data
public class ReceiveAddress {
    private int id;
    private String address;
    private String street;
    private String receiveUser;
    private String receivePhoneNumber;
    private String createTime;
    private String updateTime;
    private int userId;
}
