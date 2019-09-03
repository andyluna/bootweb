package com.andy.hibernate.xml.manytomany.entity;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.xml.manytomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 16:16
 * @DESC :
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Date createDate;
    private Set<Role> roles;

    public User addRole(Role role){
        if(roles==null){
            roles = new HashSet<>();
        }
        roles.add(role);
        return this;
    }
    public User(){

    }

    public User(String name) {
        this.name = name;
        this.createDate = new Date();
    }
}
