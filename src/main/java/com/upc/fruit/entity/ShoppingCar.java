package com.upc.fruit.entity;

import lombok.Data;

@Data
public class ShoppingCar {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private int goodsCount;

}
