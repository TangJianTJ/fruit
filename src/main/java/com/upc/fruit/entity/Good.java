package com.upc.fruit.entity;

import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class Good {
    private Integer id;
    private String goodsName;
    private double goodsPrice;
    private String createTime;
    private String updateTime;
    private String goodsClassify;
    private double goodsNumber;
    private double goodsSell;
    private String goodsImage;
    private String goodsIntroduction;
    private String effect;
    private String nutritionInfo;
    private int goodsClassifyId;
    public Good(GoodsDetail goodsDetail) {
        this.id = goodsDetail.getId();
        this.goodsName=goodsDetail.getGoodsName();
        this.goodsPrice=Double.parseDouble(goodsDetail.getGoodsPrice());
        this.createTime=goodsDetail.getCreateTime();
        this.updateTime=goodsDetail.getUpdateTime();
        this.goodsImage=goodsDetail.getGoodsImage();
        this.goodsClassifyId=goodsDetail.getGoodsClassify();
    }
    public Good()
    {

    }
}
