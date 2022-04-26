package com.example.demo.controller;

import com.example.demo.entity.StockFullList;
import com.example.demo.entity.User;
import com.example.demo.entity.Balance;
import com.example.demo.entity.Stock;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @CrossOrigin
    @GetMapping("/register")
    @ResponseBody
    public String addUser(@RequestParam String username, @RequestParam String password){
        userService.addUser(username, password);
        return "success";
    }

    @CrossOrigin
    @GetMapping("/login")
    public User loginCheck(@RequestParam String username, @RequestParam String password){
        return userService.loginCheck(username, password);
    }

    @CrossOrigin
    @GetMapping("/getBalance")
    public Balance getBalance(@RequestParam String user) {
        return userService.getBalance(user);
    }
    @CrossOrigin
    @GetMapping("/sellStockWhole")
    public String sellStockWhole(@RequestParam String code, @RequestParam String profit_Loss, @RequestParam String user) {
        userService.sellStockWhole(code, profit_Loss, user);
        return "Success";
    }

    @CrossOrigin
    @GetMapping("/sellStockPart")
    public String sellStockPart(@RequestParam String code, @RequestParam String profit_Loss, @RequestParam String user, @RequestParam String remainAmount) {
        userService.sellStockPart(code, profit_Loss, user, remainAmount);
        return "Success";
    }

    @CrossOrigin
    @GetMapping("/getCurrentStockList")
    public List<Stock> getCurrentStockList(@RequestParam String username){
        return userService.getCurrentStockList(username);
    }

    @CrossOrigin
    @GetMapping("/searchCode")
    public StockFullList searchCode(@RequestParam String code){
        return userService.searchCode(code);
    }

    @CrossOrigin
    @GetMapping("/suggestStockList")
    public List<StockFullList> suggestStockList(@RequestParam String code){
        String _code = "%" + code + "%";
        return userService.suggestStockList(_code);
    }

    @CrossOrigin
    @GetMapping("/buyStock")
    public List<Stock> buyStock(@RequestParam String code, @RequestParam String cost, @RequestParam Integer amount,  @RequestParam String name,  @RequestParam String area,  @RequestParam String user ){
        return userService.buyStock(code, cost, amount, name, area, user);
    }

    @CrossOrigin
    @GetMapping("/mergeStock")
    public List<Stock> mergeStock(@RequestParam String code, @RequestParam String cost, @RequestParam Integer amount,  @RequestParam String name,  @RequestParam String area,  @RequestParam String user ){
        return userService.mergeStock(code, cost, amount, name, area, user);
    }

    @CrossOrigin
    @GetMapping("/find")
    public User getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

}
