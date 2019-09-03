package com.dtxytech.boot.repository.event;

import com.dtxytech.boot.entity.event.Equepment;
import com.dtxytech.boot.repository.base.MyBaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.event
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 12:41
 * @DESC :
 */
public interface EquepmentRepository extends MyBaseRepository<Equepment,Integer> {

    //根据名字查询
    Equepment findByName(String name);

    //根据名字查询1
    @Query("from Equepment where name=:name ")
    Equepment findByNameHql(@Param("name") String name);

    //修改
    @Modifying
    @Query("update Equepment set name=:name where id=:id ")
    int updateByHql(@Param("name") String name, @Param("id") Integer id);


    //删除
    @Modifying
    @Query("delete from Equepment  where id=:id ")
    void deleteByHql(@Param("id")int id);
}
