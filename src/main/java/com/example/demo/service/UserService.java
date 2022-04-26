package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Balance;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockFullList;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void addUser(String username, String password);

    User loginCheck(String username,String password);

    Balance getBalance(String user);

    List<Stock> getCurrentStockList(String username);

    StockFullList searchCode(String code);

    List<StockFullList> suggestStockList(String Code);

    List<Stock> buyStock(String code,String cost,Integer amount,String name,String area, String user);

    List<Stock> mergeStock(String code,String cost,Integer amount,String name,String area, String user);

    void sellStockWhole(String code, String profit_Loss, String user);

    void sellStockPart(String code,String profit_Loss,String user,String remainAmount);

//    void updateUserById(User user);
//
//    void deleteUserById(Integer id);
//
//    User getUserById(Integer id);

    User getUserByUsername(String username);


}