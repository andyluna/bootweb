package com.andy.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 14:12
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private ConcurrentMapCacheManager cacheManager;

    @GetMapping("")
    public CacheManager getCacheManager(){

        return cacheManager;
    }




    @GetMapping("/get")
    public Cache get(){
        Cache cache = cacheManager.getCache("andy");
        cache.put(1,"andy1");
        cache.put(2,"andy2");
        cache.put(3,"andy3");
        return cache;
    }
}
