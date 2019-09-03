package com.andy.cache.controller;

import com.andy.cache.entity.User;
import com.andy.cache.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 11:25
 */
@RestController
@RequestMapping("/user")
@Log
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/add")
    public User add(Integer id,String name){
        return userService.saveUser(id,name);
    }

    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") Integer id){
        User user = userService.getById(id);
        log.info("controller层"+user);
        return user;
    }



    @GetMapping("/update")
    public User update(Integer id,String name){
        return userService.updateUser(id,name);
    }

    @GetMapping("/delete")
    public Integer delete(Integer id){
        return userService.deleteUser(id);
    }







}
