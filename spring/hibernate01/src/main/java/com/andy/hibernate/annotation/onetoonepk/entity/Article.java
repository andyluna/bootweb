package com.andy.hibernate.annotation.onetoonepk.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.andy.hibernate.annotation.onetoonepk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:32
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_ARTICLE")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn//配置共享主键，否则会额外生成外键关联列
    private ArticleContent ac;

}
