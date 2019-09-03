package com.andy.hibernate.annotation.onetomany.entity;

import lombok.Data;

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
 * @NAME : bootweb com.andy.hibernate.annotation.onetomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:43
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date createDate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product" )
    @OrderBy("createDate desc ")
    private Set<ProductComment> pcs;




}
