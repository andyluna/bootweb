package com.dtxytech.boot.entity.dsl;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.dsl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 16:56
 * @DESC : 鼠标实体类  测试DSL查询  需要额外添加jar包querydsl-jpa
 *          需要配置 QueryDslConfig
 */
@Entity
@Table(name="T_DSL_MOUSE")
@Data
public class Mouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键ID
    private String name;//鼠标名称
    private String brand;//品牌
    private Float  price;//价格
    private Date createDate;//创建时间

    public Mouse(){}

    public Mouse(String name, String brand, Float price, Date createDate) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.createDate = createDate;
    }

}
