package com.dtxytech.boot.entity.many2Many;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.many2Many
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:30
 * @DESC : 老师类    老师与学生多对多关系    通过中间表 T_TEACHER_STUDENT
 */
@Entity
@Table(name="T_TEACHER")
@Data
@ToString(exclude = {"students"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//老师主键

    private String name;//老师名称

    private String level;//级别

    private Date createDate;//创建时间


    /**
     * CascadeType.REMOVE  级联删除操作,删除当前实体时，与它有映射关系的实体也会跟着被删除。
     * CascadeType.MERGE   级联更新（合并）操作 当Student中的数据改变，会相应地更新Course中的数据。
     * CascadeType.DETACH  级联脱管/游离操作  如果你要删除一个实体，但是它有外键无法删除，你就需要这个级联权限了。它会撤销所有相关的外键关联。
     * CascadeType.REFRESH 级联刷新操作 简而言之 在保存之前 先查询数据库
     *                      假设场景 有一个订单,订单里面关联了许多商品,这个订单可以被很多人操作,那么这个时候A对此订单和关联的商品进行了修改,
     *                      与此同时,B也进行了相同的操作,但是B先一步比A保存了数据,那么当A保存数据的时候,就需要先刷新订单信息及关联的商品信息后,再将订单及商品保存。
     * CascadeType.ALL     拥有以上所有级联操作权限。
     */
    @ManyToMany(targetEntity = Student.class,cascade = CascadeType.ALL)//cascade参数可选
    @JoinTable(name="T_TEACHER_STUDENT",
            joinColumns={@JoinColumn(name="TEACHER_ID",referencedColumnName = "id")},//当前老师表的外键ID
            inverseJoinColumns = {@JoinColumn(name="STUDENT_ID",referencedColumnName ="id" )})//学生表的外键ID
    //如果 上面配置了 invereJoinColumns 则Student不做任何配置也没关系
    @OrderBy("sno asc") // 如果想要 通过 teacher.getStudents() 则会用到这个排序
    private Set<Student> students;


}
