package com.andy.hibernate.xml.onetomany;

import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_03_hql_multi_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_03_hql_multi_Test");
    }
    /**
     * 内连接查询
     * 效果：只会显示满足条件的数据
     */
    @Test
    public void test1(){
        //需求：显示客户名称和订单产品名称
        Query query = session.createQuery("select c.id,c.name,o.productName from Customer c inner join c.orders o");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 左连接查询
     * 效果：左边的数据全部显示
     */
    @Test
    public void test2(){
        //需求：显示客户名称和订单产品名称
        Query query = session.createQuery("select c.name,o.productName from Customer c left join c.orders o");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
            System.out.println();
        }
    }


    /**
     * 右连接查询
     * 效果：右边的数据全部显示
     */
    @Test
    public void test3(){
        //需求：显示客户名称和订单产品名称
        Query query = session.createQuery("select c.name,o.productName from Order o right join o.customer c");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
            System.out.println();
        }
    }



}
