package com.andy.hibernate.annotation.onetoonepk.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name="T_ANNO_ARTICLE_CONTENT")
public class ArticleContent {

    @Id
    @GeneratedValue(generator = "foreignKey")
    @GenericGenerator(name = "foreignKey" , strategy = "foreign",//使用hibernate的外键策略
                    parameters = @Parameter(value = "article",name = "property"))//指定成员属性中的article所在类的主键为本类的主键,这里的参数属性name必须为"property"
    private Integer id;
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn//如果不加此注解，hibernate会在数据库默认生成一条article_id属性
    private Article article;
}
