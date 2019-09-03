package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import com.andy.hibernate.xml.onetomany.entity.Order;
import org.junit.Test;

import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_01_get_load_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("测试Demo_01_get_load_Test方法");
    }

    /**
     * get()方法: 查询一个对象
     */
    @Test
    public void testGet(){
        Customer cust = session.get(Customer.class,1);
        logger.debug("id为1的Customer：{}",cust);
        Set<Order> orders = cust.getOrders();
        for (Order order : orders) {
            logger.debug("id为1的Customer Order {}",order);
        }
    }

    /**
     * load()方法: 查询一个对象
     */
    @Test
    public void testLoad(){
        Customer cust = session.load(Customer.class,1);
        logger.debug("id为1的Customer：{}",cust);
        Set<Order> orders = cust.getOrders();
        for (Order order : orders) {
            logger.debug("id为1的Customer Order {}",order);
        }
    }




}
