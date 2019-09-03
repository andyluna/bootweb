package com.dtxytech.boot.entity.one2Onefk;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.one2Onefk
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:24
 * @DESC : 公民- 身份证  通过在身份证表中添加一个公民ID实现 外键一对一
 */
@Entity
@Table(name="T_FK_PERSON")
@Data
@ToString(exclude = {"idCard"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER,targetEntity = IdCard.class)
    private IdCard idCard;
}
