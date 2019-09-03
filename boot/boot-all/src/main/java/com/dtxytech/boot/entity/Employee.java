package com.dtxytech.boot.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : andy/xiangdan@dtxxsoft.com.cn
 * @NAME   : bootweb com.dtxytech.boot.entity.mysql
 * @TIME   : 2019/8/28 八月 10:57
 * @DESC   : 员工信息表 实体类  不关联 主要用来测试 基本方法
 */
@Slf4j
@Entity
@Table(name="T_EMPLOYEE")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String postCode;

    private String idCard;

    private Date createDate;






}
