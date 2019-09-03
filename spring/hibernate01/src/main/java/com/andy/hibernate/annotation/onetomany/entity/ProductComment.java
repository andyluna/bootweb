package com.andy.hibernate.annotation.onetomany.entity;

import lombok.Data;

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
 * @NAME : bootweb com.andy.hibernate.annotation.onetomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:44
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_PRODUCT_COMMENT")
public class ProductComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Date createDate;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
