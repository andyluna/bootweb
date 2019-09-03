package com.andy.hibernate.xml.onetomany.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 14:05
 * @DESC : 客户
 */
@Data
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String level;
    private Date createDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(gender, customer.gender) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(level, customer.level) &&
                Objects.equals(createDate, customer.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, age, level, createDate);
    }

    //关联订单
    private Set<Order> orders = new HashSet<Order>();





    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", level='" + level + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
