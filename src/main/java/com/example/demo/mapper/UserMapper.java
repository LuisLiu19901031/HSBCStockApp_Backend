package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.entity.Balance;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockFullList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();

    @Update("INSERT INTO `user`(`username`, `password`) VALUES (#{username}, #{password});" +
            "set @id:= (select id from user where username = #{username});" +
            "INSERT INTO `balance`(`userId`, `balance`) VALUES (@id, 100000) ")
    @Transactional
    void addUser(String username, String password);


    // @Select("select * from user where username=#{username} and password=#{password}")
    @Select("use hsbcstockapp;" +
            "select user.id,user.username,user.password,balance.balance from user join balance on user.id=balance.userId " +
            "where user.username = #{username} and user.password=#{password}")
    User loginCheck(String username, String password);

    @Select("set @userId:= (select id from user where username=#{user});"+
            "select balance from balance where userId=@userId;")
    Balance getBalance(String user);


    @Select("set @uid:= (select id from user where username=#{username});"+
      "select * from stock where userId = @uid;"
    )
    List<Stock> getCurrentStockList(String username);

    @Select("Select ts_code,name,areaEn from stockfulllist where ts_code = #{code}")
    StockFullList searchCode(String code);

    @Select("Select ts_code,name,areaEn from stockfulllist where ts_code like #{code}")
    List<StockFullList> suggestStockList(String code);

    @Select("set @userId:= (select id from user where username=#{user});"+
            "Insert into stock (userId, stockCode, area, cost, amount, stockName) values (@userId, #{code}, #{area}, #{cost}, #{amount}, #{name});" +
            "select * from stock where userId = @userId;" )
    List<Stock> buyStock(String code,String cost ,Integer amount,String name,String area, String user);

    @Select("set @userId:= (select id from user where username=#{user});"+
            "update stock set cost = #{cost}, amount= #{amount} where userId=@userId and stockCode=#{code};" +
            "select * from stock where userId = @userId;" )
    List<Stock> mergeStock(String code,String cost ,Integer amount,String name,String area, String user);


    @Delete("set @userId:= (select id from user where username=#{user});"+
            "delete from stock where stockCode = #{code} and userId=@userId;"+
            "update balance set balance = (balance + CAST(#{profit_Loss} AS DECIMAL)) where userId = @userId;")
    void sellStockWhole(String code, String profit_Loss, String user);

    @Update("set @userId:= (select id from user where username=#{user});"+
            "update stock set amount = #{remainAmount} where userId = @userId and stockCode=#{code};"+
            "update balance set balance = (balance + CAST(#{profit_Loss} AS DECIMAL)) where userId = @userId;")
    void sellStockPart(String code, String profit_Loss, String user, String remainAmount);

//    @Update("update user set username=#{username},password=#{password} where id=#{id}")
//    @Transactional
//    void updateUserById(User user);
//
//    @Delete("delete from user where id=#{id}")
//    @Transactional
//    void deleteUserById(Integer id);
//
//    @Select("select * from user where id=#{id}")
//    User getUserById(Integer id);

    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);

}