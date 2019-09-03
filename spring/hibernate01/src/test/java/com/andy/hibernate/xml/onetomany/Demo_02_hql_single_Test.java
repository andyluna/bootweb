package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Order;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_02_hql_single_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_02_hql_single_Test");
    }
    /**
     * 1.全表查询
     */
    @Test
    public void test1(){
        logger.debug("测试全表查询");
        Query query = session.createQuery("from Order");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }

    /**
     * 2.别名查询
     */
    @Test
    public void test2(){
        logger.debug("测试别名查询");
        Query query = session.createQuery("select o from Order o");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }

    /**
     * 3.条件查询
     */
    @Test
    public void test3(){
        logger.debug("测试条件查询");
        Query query = session.createQuery("from Order where orderName = '老王1的订单1'");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }
    /**
     * 4.条件查询2
     */
    @Test
    public void test4(){
        Query query = session.createQuery("from Order where orderName = ?0 ");//这里要加上数字0  否则报错
        query.setParameter(0, "老王2的订单2");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }

    /**
     * 5.具名查询
     */
    @Test
    public void test5(){
        Query query = session.createQuery("from Order where orderName = :orderName");
        query.setParameter("orderName", "老王2的订单2");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }


    /**
     * 6.分页查询
     */
    @Test
    public void test6(){
        Query query = session.createQuery("from Order");
        query.setFirstResult(2);//设置起始行，从0开始
        query.setMaxResults(5);//设置查询行数
        List<Order> list = query.list();
        list.forEach(System.out::println);

    }

    /**
     * 7.查询排序
     */
    @Test
    public void test7(){
        System.out.println("查询排序");
        Query query = session.createQuery("from Order order by id desc");
        List<Order> list = query.list();
        list.forEach(System.out::println);
    }


    /**
     * 8.聚合查询
     */
    @Test
    public void test8(){
        System.out.println("聚合查询");
        Query query = session.createQuery("select count(*) from Order");
		/*
		List<Long> list = query.list();
		Long count = list.get(0);
		System.out.println(count);
		*/
        Long count = (Long)query.uniqueResult();
        System.out.println(count);

    }


    /**
     * 9.投影查询（查询局部字段）
     */
    @Test
    public void test9(){
        System.out.println("9.投影查询（查询局部字段）");
        Query query = session.createQuery("select orderName,productName from Order");
        List<Object[]> list = query.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
            System.out.println();
        }

    }


    /**
     * 10.投影查询2（查询局部字段）
     */
    @Test
    public void test10(){
        System.out.println("10.投影查询2（查询局部字段）");
        Query query = session.createQuery("select new com.andy.hibernate.xml.onetomany.entity.Order(orderName,productName) from Order");
        List<Order> list = query.list();
        for (Order order : list) {
            System.out.println(order);
        }

    }





}
