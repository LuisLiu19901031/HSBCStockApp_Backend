package com.example.demo.entity;

import lombok.Data;

@Data
public class Balance {
    private Integer id;
    private Integer userId;
    private Integer balance;
    private User user;
}