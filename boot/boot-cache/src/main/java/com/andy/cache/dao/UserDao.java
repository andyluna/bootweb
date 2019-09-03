package com.andy.cache.dao;

import com.andy.cache.entity.User;

import java.util.List;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 10:08
 */
public interface UserDao {

    List<User> getAllUser();

    User saveUser(User user);

    User updateUser(User user);

    int deleteUser(Integer id);


    User getById(Integer id);
}
