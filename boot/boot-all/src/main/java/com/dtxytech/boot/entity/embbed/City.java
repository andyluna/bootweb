package com.dtxytech.boot.entity.embbed;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:25
 * @DESC : 城市
 */
@Data
@Entity
@Table(name="T_EMBBED_CITY")
public class City extends BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;//城市名字

    private Long totalPeople;//总人口

    private String zcZipCode;//这个字段没有别的意思 特意用来演示当与 CityZipCode.ZipCode冲突重复时 查询该如何定义

    //因为这个类 已经加了@Embeddable注解 所以 这里不需要再加@Embedded注解 但是亲测加了也不会有任何问题
    private CitySymbol cs;//城市象征性建筑或美食

    //因为类没有加注解 如果要嵌入就必须加上@Embedded注解
    @Embedded
    private CityZipCode zc;//城市邮政编码



}
