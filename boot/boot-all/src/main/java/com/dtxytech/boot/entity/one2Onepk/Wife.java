package com.dtxytech.boot.entity.one2Onepk;

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
 * @NAME : bootweb com.dtxytech.boot.entity.one2Onepk
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:27
 * @DESC : Wife
 */
@Entity
@Table(name="T_PK_WIFE")
public class Wife {
    @Id
    @GeneratedValue(generator = "foreignKey")//这里的foreignKey 必须与下面的name 保持一致
    @GenericGenerator(name = "foreignKey" ,
                      strategy = "foreign",//使用hibernate的外键策略
                      parameters = @Parameter(value = "husband",name = "property"))
    //指定成员属性中的article所在类的主键为本类的主键,这里的参数属性name必须为"property"
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn//如果不加此注解，hibernate会在数据库默认生成一条article_id属性
    private Husband husband;
}
