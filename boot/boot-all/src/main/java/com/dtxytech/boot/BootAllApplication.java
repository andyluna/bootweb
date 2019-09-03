package com.dtxytech.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @auth : andy/xiangdan@dtxxsoft.com.cn
 * @NAME : bootweb com.dtxytech.boot
 * @TIME : 2019/8/27 八月 16:48
 * @DESC :
 */
@SpringBootApplication
public class BootAllApplication {
    private static final Logger logger = LoggerFactory.getLogger(BootAllApplication.class);
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(BootAllApplication.class, args);
        String[] names = context.getBeanDefinitionNames();
        logger.info("BootAllApplication启动成功 总共有 {} 个bean",names.length);

    }

}
