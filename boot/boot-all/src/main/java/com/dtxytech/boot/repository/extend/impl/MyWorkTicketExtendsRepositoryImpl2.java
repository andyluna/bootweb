package com.dtxytech.boot.repository.extend.impl;

import com.dtxytech.boot.entity.WorkTicket;
import com.dtxytech.boot.repository.extend.MyWorkTicketExtendsRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:13
 * @DESC : 自定义扩展实现类   名字必须以Impl结尾
 */
@Slf4j
public class MyWorkTicketExtendsRepositoryImpl2 implements MyWorkTicketExtendsRepository {

    @Override
    public WorkTicket findByAnyThing(String anyThing) {
        log.debug("扩展方法 Impl2结尾");
        WorkTicket wt =  new WorkTicket();
        wt.setId("123Impl2结尾");
        wt.setContent("测试一下");
        return wt;
    }

    @Override
    public WorkTicket findByContentZdy(String content) {
        return null;
    }

    @Override
    public WorkTicket findByIdByHibernateSession(String content) {
        return null;
    }
}
