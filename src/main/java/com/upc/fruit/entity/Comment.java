package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer orderId;
    private float grade;
    private String commentDescribe;
    public Comment()
    {

    }


}
