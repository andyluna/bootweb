package com.andy.cache.service.impl;

import com.andy.cache.dao.UserDao;
import com.andy.cache.entity.User;
import com.andy.cache.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 10:10
 */
@Service
@Log
@Caching
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {

        List<User> list =  userDao.getAllUser();
        log.info("service层getAllUser："+list);
        return list;
    }

    @Override
    public User saveUser(Integer id, String name) {
        User u = userDao.saveUser(new User(id,name));
        log.info("service层saveUser："+u);
        return u;
    }

    @CachePut(cacheNames = "test", key = "#id")
    @Override
    public User updateUser(Integer id, String name) {
        User u = userDao.updateUser(new User(id,name));
        log.info("service层updateUser："+u);
        return u;
    }
    @CacheEvict(cacheNames = "test")
    @Override
    public int deleteUser(Integer id) {
        int u = userDao.deleteUser(id);
        log.info("service层deleteUser："+id);
        return u;
    }
    @Cacheable(cacheNames = "test")
    @Override
    public User getById(Integer id) {
        User u = userDao.getById(id);
        log.info("service层getById："+u);
        return u;
    }
}
