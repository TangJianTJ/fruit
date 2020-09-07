package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Success {
    private Object msg;
    private Integer status;

    public Success(Object msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }
}
