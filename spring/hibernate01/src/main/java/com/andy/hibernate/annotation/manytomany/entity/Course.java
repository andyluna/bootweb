package com.andy.hibernate.annotation.manytomany.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.annotation.manytomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:52
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_STUDENT_COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @ManyToMany(targetEntity=Student.class,mappedBy="courses") //让student维护外键表
    private Set<Student> students;
}
