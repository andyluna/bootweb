package com.andy.hibernate.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.andy.hibernate.jpa
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 11:22
 * @DESC :
 */
@Data
@Entity
@Table(name="T_JPA_CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  custName;

    private String  custSource;

    private String  custIndustry;

    private String custLevel;

    private String  custAddress;

    private String  custPhone;
    

}
