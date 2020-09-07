package com.upc.fruit.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoAll {
    private String nickName;
    private String headImage;
    private List<OrderBean> orders;

    public UserInfoAll(String nickName, String headImage, List<OrderBean> orderBeanList) {
        this.nickName = nickName;
        this.headImage = headImage;
        this.orders = orderBeanList;
    }
}
