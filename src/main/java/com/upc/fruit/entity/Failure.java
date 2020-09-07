package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Failure {
    private String msg;
    private Integer status;

    public Failure(String msg) {
        this.msg = msg;
        status=404;
    }

    public Failure(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }
}
