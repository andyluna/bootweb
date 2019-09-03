package com.andy.cache.dao.impl;

import com.andy.cache.dao.UserDao;
import com.andy.cache.entity.User;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 10:10
 */
@Repository
@Log
public class UserDaoImpl implements UserDao {
    private static Map<Integer,User> userMap = new LinkedHashMap<>();
    static{
        userMap.put(1001,new User(1001,"andy"));
        userMap.put(1002,new User(1002,"tingting"));
        userMap.put(1003,new User(1003,"zuozuo"));
    }

    public static void main(String[] args) {
        System.out.println(userMap.values());
    }


    @Override
    public List<User> getAllUser() {
        List<User> list =  new ArrayList<>(userMap.values());
        log.info("dao层getAllUser："+list);
        return list;
    }

    @Override
    public User saveUser(User user) {
        userMap.put(user.getId(),user);
        log.info("dao层saveUser："+user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        userMap.put(user.getId(),user);
        log.info("dao层updateUser："+user);
        return user;
    }

    @Override
    public int deleteUser(Integer id) {
        userMap.remove(id);
        log.info("dao层deleteUser："+id);
        return 1;
    }

    @Override
    public User getById(Integer id) {
        User user = userMap.get(id);
        log.info("dao层deleteUser："+user);
        return user;
    }
}
