package com.andy.hibernate.annotation;

import com.andy.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.annotation
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 11:37
 * @DESC :
 */
public class HibernateAnnotationTest {
    private static final Logger logger = LoggerFactory.getLogger(HibernateAnnotationTest.class);
    private String configueName = "hibernate-annotation.cfg.xml";
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;
    @Before
    public void setUp() throws Exception {
        sessionFactory = HibernateUtils.getSessionFactory(configueName);
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }
    @Test
    public void testCreate(){
        logger.debug("建表成功 {}",sessionFactory);
    }
    @Test
    public void testDrop(){
        List<String> list = Arrays.asList(
                "truncate table T_ANNO_ARTICLE_CONTENT",
                "truncate table T_ANNO_ARTICLE ",
                "truncate table T_ANNO_PRODUCT_COMMENT ",
                "truncate table T_ANNO_PRODUCT ",
                "truncate table T_ANNO_STUDENT_COURSE ",
                "truncate table T_ANNO_STU_COU ",
                "truncate table T_ANNO_STUDENT ");

        list.forEach(h->session.createSQLQuery(h).executeUpdate());
        logger.debug("删除表成功");
    }


    @After
    public void tearDown() throws Exception {
    }
}