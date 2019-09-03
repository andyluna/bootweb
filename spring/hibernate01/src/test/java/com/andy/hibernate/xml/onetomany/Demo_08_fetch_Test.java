package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_08_fetch_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_08_fetch_Test");
    }

    @Test
    public void test1() {
        Customer customer = (Customer) session.load(Customer.class, 1);
        int n = customer.getOrders().size();
        System.out.println(n);
    }

    @Test
    public void test2() {
        List<Customer> results = session.createQuery(
                "From Customer c where c.id in (1,2,3,4)").list();
        // 这里的四个 id 是我数据库中已经准备好的数据
        Customer c0 = (Customer) results.get(0);
        int n= c0.getOrders().size();

        System.out.println(n);
    }
}
