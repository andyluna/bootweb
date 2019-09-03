package com.dtxytech.boot.entity.many2Many;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.many2Many
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:30
 * @DESC : 学生类
 */
@Entity
@Table(name="T_STUDENT")
@Data
@ToString(exclude ={"teachers"} )
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;//姓名

    private Integer age;//年龄

    private String sno;//学号

    private Date createDate;//创建时间

    @ManyToMany(targetEntity=Teacher.class,mappedBy="students") //让teacher维护外键表
    @OrderBy("name asc")
    private Set<Teacher> teachers;

}
