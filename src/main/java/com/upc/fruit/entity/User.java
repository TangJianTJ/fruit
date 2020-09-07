package com.upc.fruit.entity;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public  class User {
    private Integer id;
    private String nickName;
    private String phoneNumber;
    private String password;
    private String email;
    private int sex;
    private String headImage;
    private Date birthday;


}
