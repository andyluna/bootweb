package com.dtxytech.boot.repository.extend;

import com.dtxytech.boot.entity.WorkTicket;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:13
 * @DESC :
 */
public interface MyWorkTicketExtendsRepository {
    //1.自定义查询  不真正查询数据库  只是自定义数据而已
    WorkTicket findByAnyThing(String anyThing);


    //2.根据 content 自定义查询 通过 EntityManager
    WorkTicket findByContentZdy(String content);


    //3.根据 Id 自定义查询 通过Session
    WorkTicket findByIdByHibernateSession(String id);



}
