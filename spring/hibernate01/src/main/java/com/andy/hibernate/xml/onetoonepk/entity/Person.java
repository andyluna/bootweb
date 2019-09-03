package com.andy.hibernate.xml.onetoonepk.entity;

import lombok.Data;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetoonepk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:15
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
