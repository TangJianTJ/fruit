package com.upc.fruit.entity;

import lombok.Data;

@Data
public class ShoppingCarBean {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private int goodsCount;
    private GoodsDetail goods;

    public ShoppingCarBean(ShoppingCar shoppingCar,GoodsDetail goodsDetail) {
        this.userId = shoppingCar.getUserId();
        this.goodsId = shoppingCar.getGoodsId();
        this.goodsCount = shoppingCar.getGoodsCount();
        this.id=shoppingCar.getId();
        this.goods=goodsDetail;
    }
}
