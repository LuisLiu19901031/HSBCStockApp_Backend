package com.example.demo.service;

import com.example.demo.entity.Balance;
import com.example.demo.entity.User;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockFullList;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addUser(String username, String password) {
        userMapper.addUser(username, password);
    }

    @Override
    public User loginCheck(String username, String password) {
        return userMapper.loginCheck(username, password);
    }

    @Override
    public Balance getBalance(String user) {
        return userMapper.getBalance(user);
    }

    @Override
    public List<Stock> getCurrentStockList(String username) {
        return userMapper.getCurrentStockList(username);
    }

    @Override
    public StockFullList searchCode(String code) {
        return userMapper.searchCode(code);
    }

    @Override
    public List<StockFullList> suggestStockList(String code) {
        return userMapper.suggestStockList(code);
    }
    @Override
    public List<Stock> buyStock(String code, String cost,Integer amount,String name,String area,String user) {
        return userMapper.buyStock(code, cost, amount, name, area, user);
    }

    @Override
    public List<Stock> mergeStock(String code, String cost,Integer amount,String name,String area,String user) {
        return userMapper.mergeStock(code, cost, amount, name, area, user);
    }

    @Override
    public void sellStockWhole(String code, String profit_Loss, String user) {
        userMapper.sellStockWhole(code, profit_Loss, user);
    }

    @Override
    public void sellStockPart(String code,String profit_Loss,String user,String remainAmount) {
        userMapper.sellStockPart(code, profit_Loss, user, remainAmount);
    }

//    @Override
//    public void updateUserById(User user) {
//        userMapper.updateUserById(user);
//    }
//
//    @Override
//    public void deleteUserById(Integer id) {
//        userMapper.deleteUserById(id);
//    }
//
//    @Override
//    public User getUserById(Integer id) {
//        return userMapper.getUserById(id);
//    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

}
