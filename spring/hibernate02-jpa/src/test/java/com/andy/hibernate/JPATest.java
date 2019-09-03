package com.andy.hibernate;

import com.andy.hibernate.entity.Customer;
import com.andy.hibernate.utils.EntityManagerUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 15:09
 * @DESC :
 */
public class JPATest {
    private static final Logger logger = LoggerFactory.getLogger(JPATest.class);

    //增
    @Test
    public void test1(){
        EntityManager em = EntityManagerUtil.createEntityManager();
        Customer cus = new Customer();
        cus.setCustName("test");
        cus.setCustLevel("gender1");
        cus.setCustAddress("长沙");
        cus.setCustPhone("13875909772");
        cus.setCustSource("helo");
        cus.setCustIndustry("asdsad");

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(cus);
        tx.commit();
        em.close();
    }


    //查
    @Test
    public void findTest() {
        EntityManager em = EntityManagerUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //查询find方法
        Customer c = em.find(Customer.class, 1);
        System.out.println(c);
        tx.commit();
        em.close();
    }

    //改
    @Test
    public void updateTest() {
        EntityManager em = EntityManagerUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //先查询
        Customer c = em.find(Customer.class, 1);
        //在用merge修改
        c.setCustName("改名字了");
        em.merge(c);
        System.out.println(c);
        tx.commit();
        em.close();
    }

    //删
    @Test
    public void delTest() {
        EntityManager em = EntityManagerUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //先查询
        Customer c = em.find(Customer.class, 1);
        if(c!=null){
            em.remove(c); //在用remove删除
        }
        tx.commit();
        em.close();
    }

    //查询全部
    @Test
    public void findAllTest() {
        EntityManager em = EntityManagerUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //查询find方法
        Query query = em.createQuery("from Customer");// JPQL java persistence query language
        List<Customer> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        tx.commit();
        em.close();
    }

    //聚合函数
    @Test
    public void test() {
        EntityManager em = EntityManagerUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //查询find方法
        Query query = em.createQuery("select count(id) from Customer");// JPQL java persistence query language
        Long n = (Long)query.getSingleResult();
        System.out.println(n);
        tx.commit();
        em.close();
    }


    

}