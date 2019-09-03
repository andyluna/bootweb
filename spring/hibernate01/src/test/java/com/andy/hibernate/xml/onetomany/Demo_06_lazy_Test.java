package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import com.andy.hibernate.xml.onetomany.entity.Order;
import org.junit.Test;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_06_lazy_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_06_lazy_Test");
    }


    @Test
    public void loadCustomer() {
        // 如果通过load方式加载Customer对象
        Customer customer = (Customer) session.load(Customer.class, 1);
        System.out.println(customer.getClass().getName());
        //com.andy.hibernate.xml.onetomany.entity.Customer$HibernateProxy$Qs9B3WX3
        //如果不使用 则不会发送sql语句
    }

    @Test
    public void loadCustomer2() {
        // 获取Session对象
        // 如果通过load方式加载Customer对象
        Customer customer = (Customer) session.load(Customer.class, 1);
        int n = customer.getOrders().size();
        System.out.println(n);
    }

    @Test
    public void loadOrder() {
        // 如果通过load方式加载Order对象
        Order order =  session.get(Order.class, 1);
        // 获取Customer对象，因为此时的配置文件lazy是proxy，所以是代理对象
        Customer customer = order.getCustomer();
        System.out.println(customer.getClass().getName());
        System.out.println(customer);
        System.out.println(customer.getClass().getName());

    }

    @Test
    public void loadOrder2() {
        // 如果通过load方式加载Order对象
        Order order = (Order) session.get(Order.class, 1);
        ////获取Dept对象，因为此时的配置文件lazy是false，所以是实际对象
        Customer customer = order.getCustomer();
    }
}
