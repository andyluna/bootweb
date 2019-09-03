package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.xml.onetomany.entity.Customer;
import com.andy.hibernate.xml.onetomany.entity.Order;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:51
 * @DESC :
 */
public class Demo_04_criteria_Test extends One2ManyTest{
    public void init(){
        super.setConfigureName("hibernate-xml-one2Many.cfg.xml");
        super.setDisplay("Demo_04_criteria_Test");
    }
    /**
     * 全表查询
     */
    @Test
    public void test1(){
        Criteria ce = session.createCriteria(Customer.class);
        List<Customer> list = ce.list();
        list.forEach( System.out::println);
    }

    /**
     * 条件查询
     */
    @Test
    public void test2(){
        //201709070003
        Criteria ce = session.createCriteria(Order.class);
        //添加查询条件   orderno = '201709070003'
        ce.add( Restrictions.like("orderName", "老王%")  );
        List<Order> list = ce.list();
        list.forEach( System.out::println);
    }


    /**
     * 条件查询2(多条件)
     */
    @Test
    public void test3(){
        //201709070003
        Criteria ce = session.createCriteria(Order.class);
        //添加查询条件   orderno like '%2017%' and productName like '%JavaWeb%'
        ce.add( Restrictions.and(  Restrictions.like("orderName", "%老王%") ,  Restrictions.like("productName", "%鼠标%")  )  );
        List<Order> list = ce.list();
        list.forEach( System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void test4(){
        Criteria ce = session.createCriteria(Order.class);
        //分页查询
        ce.setFirstResult(2);//起始行
        ce.setMaxResults(2);//查询行数
        List<Order> list = ce.list();
        list.forEach( System.out::println);
    }


    /**
     * 查询排序
     */
    @Test
    public void test5(){
        Criteria ce = session.createCriteria(Order.class);
        //排序  order by id desc
        ce.addOrder(org.hibernate.criterion.Order.desc("id"));
        List<Order> list = ce.list();
        list.forEach( System.out::println);
    }


    /**
     * 聚合查询
     */
    @Test
    public void test6(){
        Criteria ce = session.createCriteria(Order.class);
        //查询总记录数  select count(id)
        //ce.setProjection(Projections.count("id"));
        //查询id的最大值
        ce.setProjection(Projections.max("id"));
        Integer max = (Integer)ce.uniqueResult();
        System.out.println(max);
    }


    /**
     * 投影查询
     */
    @Test
    public void test7(){
        Criteria ce = session.createCriteria(Order.class);
        //投影操作
        ProjectionList pList = Projections.projectionList();
        pList.add(Property.forName("orderName"));
        pList.add(Property.forName("productName"));
        ce.setProjection(pList);
        List<Object[]> list = ce.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
            System.out.println();
        }
    }


}
