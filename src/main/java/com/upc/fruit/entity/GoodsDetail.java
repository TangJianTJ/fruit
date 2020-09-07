package com.upc.fruit.entity;

import lombok.Data;

@Data
public class GoodsDetail {
    private Integer id;
    private String goodsName;
    private String goodsPrice;
    private String goodsImage;
    private int goodsClassify;
    private String goodsIntroduction;
    private int temperature;
    private String nutritionInfo;
    private String effect;
    private int hot;
    private String createTime;
    private String updateTime;

   /* public GoodsDetail (Good good)
    {
        this.goodsClassify=Integer.parseInt(good.getGoodsClassify());
        this.goodsName=good.getGoodsName();
        this.goodsImage=good.getGoodsImage();
        this.goodsPrice=String.valueOf(good.getGoodsPrice());
        this.goodsIntroduction=good.getGoodsIntroduction();
        this.effect=good.getEffect();
        this.nutritionInfo=good.getNutritionInfo();
        this.createTime=good.getCreateTime();
        this.id=good.getId();
        this.updateTime=good.getUpdateTime();
    }*/
}
