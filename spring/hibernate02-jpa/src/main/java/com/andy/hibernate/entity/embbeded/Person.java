package com.andy.hibernate.entity.embbeded;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.andy.hibernate.entity.embbeded
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 17:08
 * @DESC :
 */
@Data
@Entity
@Table(name="T_JPA_EMBEDDED_PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date createDate;

    @Embedded
    private Address address;
}
