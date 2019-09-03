package com.dtxytech.boot.entity.one2Many;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:22
 * @DESC : 收获地址  与账号 形成多对一关联关系
 */
@Entity
@Table(name="T_ACCOUNT_ADDRESS")
@Data
@ToString(exclude = {"account"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String phone;
    private Date createDate;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)//cascade配置可选
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;


}
