package com.upc.fruit.entity;

import lombok.Data;

@Data
public class CommentBean {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer orderId;
    private float grade;
    private String headImage;
    private String nickName;
    private String commentDescribe;

    public CommentBean(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.goodsId = comment.getGoodsId();
        this.grade = comment.getGrade();
        this.commentDescribe = comment.getCommentDescribe();
        this.orderId=comment.getOrderId();
    }
}
