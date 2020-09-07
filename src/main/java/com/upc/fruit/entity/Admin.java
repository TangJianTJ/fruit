package com.upc.fruit.entity;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String userName;
    private String password;
    private String createdTime;
    private String phoneNumber;
    private Boolean state;
    private String email;
}
