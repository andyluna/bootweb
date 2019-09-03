package com.dtxytech.boot.bean;

import com.dtxytech.boot.entity.WorkTicket;
import org.springframework.stereotype.Component;

/**
 * @NAME : bootweb com.dtxytech.boot.bean
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/2 九月 10:20
 * @DESC :
 */
@Component
public class MyBean {

    public String getMyContent(WorkTicket wt){
        return "MyBean内容-"+wt.getContent();
    }
}
