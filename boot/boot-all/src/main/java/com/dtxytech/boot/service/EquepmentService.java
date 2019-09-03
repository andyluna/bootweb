package com.dtxytech.boot.service;

import com.dtxytech.boot.entity.event.Equepment;

import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.service
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 12:54
 * @DESC :
 */
public interface EquepmentService {
    //1.1保存
    Equepment save(Equepment e);
    //1.2批量保存
    List<Equepment> saveAll(Iterable<Equepment> its);

    //2.1根据id查询
    Equepment getById(Integer id);
    //2.2根据名字查询
    Equepment getByName(String name);
    //2.3根据HQL语句查询
    Equepment getByHql(String name);

    //3.1修改
    void update(Equepment e);
    //3.1修改
    int updateByHql(String name,Integer id);



    //4.1根据id删除
    void deleteById(int id);
    //4.2根据id删除 直接使用语句
    void deleteByHql( int id);



}
