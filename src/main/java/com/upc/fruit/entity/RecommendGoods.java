package com.upc.fruit.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RecommendGoods implements Serializable {
    private int id;
    private String place;
    private int goodsType;
    private String createTime;
    private String updateTime;
    private GoodsDetail goods;

    public RecommendGoods(GoodsDetail goods) {
        this.goods = goods;
    }
}
