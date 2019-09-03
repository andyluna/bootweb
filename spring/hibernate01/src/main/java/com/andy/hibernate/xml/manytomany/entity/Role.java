package com.andy.hibernate.xml.manytomany.entity;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.xml.manytomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 16:17
 * @DESC :
 */
@Data
public class Role {

    private Integer id;
    private String roleName;
    private Date   createDate;
    private Set<User> users;
    public Role(){}
    public Role(String roleName) {
        this.roleName = roleName;
        this.createDate = new Date();
    }


    public Role addUser(User user){
        if(users==null){
            users = new HashSet<>();
        }
        users.add(user);
        return this;
    }


}
