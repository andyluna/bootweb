package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_07_session_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_07_session_Test");
    }


    // 测试一级缓存
    @Test
    public void test1() {
        // 1.得到session
        Customer customer1 = session.get(Customer.class, 1); // 查询id=1的Customer对象，如果查询到，会将Customer对象存储到一级缓存中
        Customer customer2 = session.get(Customer.class, 1); // 会从一级缓存中查询，而不会向数据库再发送sql语句查询
        System.out.println("customer1 = "+customer1);
        System.out.println("customer2 = "+customer2);
    }

    // 持久化对象具有自动更新数据库的能力
    @Test
    public void test2() {
        // 1.得到session
        Customer customer = session.get(Customer.class, 1); // 查询id=1的Customer对象，如果查询到，会将Customer对象存到一级缓存中
        customer.setName("Tom"); // 操作持久化对象来修改属性
        customer.setCreateDate(new Date());
    }

    // 测试一级缓存操作常用的API
    @Test
    public void test3() {
        List<Customer> list = session.createQuery("from Customer").list(); // 这个操作会存储数据到一级缓存
        session.clear(); // 清空一级缓存
        Customer c = session.get(Customer.class, 1); // 会先从session的一级缓存中获取，如果不存在，才会从数据库里面获取
        session.evict(c); // 从一级缓存中删除一个指定的对象
        Customer cc = session.get(Customer.class, 1);

        cc.setName("kkkk");
        session.refresh(cc); // refresh方法的作用是：它会用数据库里面的数据来同步我们的一级缓存以及快照区，
        // 这样的话，再操作cc时，就不会发送update语句。
        // refresh方法：重新查询数据库，用数据库中的信息来更新一级缓存与快照区
    }

}
