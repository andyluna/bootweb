package com.andy.hibernate.annotation.manytomany.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.annotation.manytomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:51
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String studentName;
    private String gender;

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(name="T_ANNO_STU_COU",
               joinColumns={@JoinColumn(name="STU_ID",referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name="COU_ID",referencedColumnName ="id" )})
    private Set<Course> courses;
}
