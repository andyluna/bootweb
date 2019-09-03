package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_09_ehcache_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many-ehcache.cfg.xml");
        super.setDisplay("Demo_09_ehcache_Test");
    }

    // 演示二级缓存
    @Test
    public void test1() {
        // 第1次操作
        Customer cust = session.get(Customer.class, 1);
        System.out.println(cust);
        tx.commit();
        session.close();


        session = sessionFactory.openSession();
        tx=session.beginTransaction();
        cust = session.get(Customer.class, 1);
        System.out.println(cust);
    }

    // 演示查询缓存
    @Test
    public void test2() {
        Query q = session.createQuery("select id,name from Customer");
        q.setCacheable(true);
        List list = q.list();
        tx.commit();
        session.close();


        session = sessionFactory.openSession();
        q = session.createQuery("select id,name from Customer");
        q.setCacheable(true);
        List list2 = q.list();


        //这两个查询 只会发送一条sql语句
    }

}
