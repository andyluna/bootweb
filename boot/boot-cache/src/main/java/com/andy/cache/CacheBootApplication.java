package com.andy.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2019-01-10 星期四 09:29
 */
@SpringBootApplication
@EnableCaching
public class CacheBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CacheBootApplication.class,args);
//        int total = context.getBeanDefinitionCount();
//        System.out.println("总数 = "+total);
//        String[] names = context.getBeanDefinitionNames();
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i]+" = "+context.getBean(names[i]));
//        }

    }

}
