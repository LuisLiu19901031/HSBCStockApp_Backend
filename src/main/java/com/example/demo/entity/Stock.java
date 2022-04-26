package com.example.demo.entity;

import lombok.Data;

@Data
public class Stock {
    private Integer id;
    private Integer userId;
    private String stockCode;
    private String area;
    private Float cost;
    private Integer amount;
    private String stockName;
   // private User user;
}
