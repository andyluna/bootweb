package com.dtxytech.boot.entity.one2Many;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.one2Many
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:23
 * @DESC : 账号  一个账号对应多个收货地址  1对多关系
 */
@Entity
@Table(name="T_ACCOUNT")
@Data
@ToString(exclude ={"addresss"} )
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String qq;
    private Date createDate;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "account" )
    @OrderBy("createDate desc ")
    private Set<Address> addresss;

}
