package com.example.demo.entity;

import com.example.demo.entity.Balance;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer balance;
}

