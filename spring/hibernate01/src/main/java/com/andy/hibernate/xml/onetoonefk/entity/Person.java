package com.andy.hibernate.xml.onetoonefk.entity;

import lombok.Data;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetoonefk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:12
 * @DESC :
 */
@Data
public class Person {
    private Integer id;
    private String name;
    //关联身份证
    private Card card;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card=" + card.getId() +card.getName()+
                '}';
    }
}
