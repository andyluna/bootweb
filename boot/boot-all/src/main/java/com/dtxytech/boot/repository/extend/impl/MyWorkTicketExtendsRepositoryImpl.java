package com.dtxytech.boot.repository.extend.impl;

import com.dtxytech.boot.entity.WorkTicket;
import com.dtxytech.boot.repository.extend.MyWorkTicketExtendsRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:13
 * @DESC : 自定义扩展实现类   名字必须以Impl结尾
 */
@Slf4j
public class MyWorkTicketExtendsRepositoryImpl implements MyWorkTicketExtendsRepository {
    //自动注入 EntityManager
    //EntityManager权利很大 标准JPA的所有操作都是通过这个类来执行的
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public WorkTicket findByAnyThing(String anyThing) {
        log.debug("扩展方法 默认Impl结尾,查询参数={}",anyThing);
        WorkTicket wt =  new WorkTicket();
        wt.setId("123默认Impl结尾");
        wt.setContent("测试一下");
        return wt;
    }

    @Override
    public WorkTicket findByContentZdy(String content) {
        log.debug("自定义查询 EntityManager ={}",entityManager);
        Query query = entityManager.createQuery("from WorkTicket where content=:content ");
        query.setParameter("content", content);
        return (WorkTicket) query.getSingleResult();
    }

    @Override
    public WorkTicket findByIdByHibernateSession(String id) {
        //entityManager如果操作不方便 可以调用 转换成 Hibernate.Session进行数据操作
        //以下操作的前提是  当前执行线程必须有事务 不然会报异常
        Session session = entityManager.unwrap(Session.class);
        return session.get(WorkTicket.class,id );
    }
}
