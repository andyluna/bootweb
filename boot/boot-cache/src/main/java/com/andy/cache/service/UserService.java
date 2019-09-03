package com.andy.cache.service;

import com.andy.cache.entity.User;

import java.util.List;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 10:08
 */
public interface UserService {

    List<User> getAllUser();

    User saveUser(Integer id, String name);

    User updateUser(Integer id, String name);

    int deleteUser(Integer id);

    User getById(Integer id);
}
