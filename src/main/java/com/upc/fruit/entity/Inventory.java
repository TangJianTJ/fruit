package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Inventory {
    private Integer id;
    private double goodsNumber;
    private double goodsSell;

    public Inventory(Integer id,double goodsNumber,double goodsSell)
    {
        this.goodsNumber=goodsNumber;
        this.goodsSell=goodsSell;
        this.id=id;
    }
    public Inventory()
    {

    }
}
