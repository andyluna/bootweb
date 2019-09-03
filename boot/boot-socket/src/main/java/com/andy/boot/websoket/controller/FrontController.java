package com.andy.boot.websoket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : andy<xiangdan311@163.com>
 * @description:
 * @create : 2018-12-21 星期五 15:56
 */
@Slf4j
@Controller
public class FrontController {

    @RequestMapping({"/index","/"})
    public String admin(){
        log.info("-------FrontController---------");
        return "front/index";
    }
}
