package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Order;
import org.hibernate.query.NativeQuery;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_05_nativesql_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_05_nativesql_Test");
    }


    // 以对象数组封装
    @Test
    public void test1() {
        NativeQuery sqlQuery = session.createSQLQuery("select * from t_xml_order");
        List<Object[]> list = sqlQuery.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }
    }

    // 以JavaBean对象封装
    @Test
    public void test2() {
        NativeQuery sqlQuery = session.createSQLQuery("select * from t_xml_order");
        sqlQuery.addEntity(Order.class);
        List<Order> list = sqlQuery.list();
        for (Order order : list) {
            System.out.println(order);
        }
    }


}
