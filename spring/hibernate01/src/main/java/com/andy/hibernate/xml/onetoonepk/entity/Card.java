package com.andy.hibernate.xml.onetoonepk.entity;

import lombok.Data;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetoonefk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:12
 * @DESC :
 */
@Data
public class Card {
    private Integer id;
    private String name;
    //关联公民
    private Person person;
}
