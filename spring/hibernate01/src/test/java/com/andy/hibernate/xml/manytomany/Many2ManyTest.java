package com.andy.hibernate.xml.manytomany;

import com.andy.hibernate.utils.HibernateUtils;
import com.andy.hibernate.xml.manytomany.entity.Role;
import com.andy.hibernate.xml.manytomany.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.xml.manytomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 16:24
 * @DESC :
 */
public class Many2ManyTest {
    private static final Logger logger = LoggerFactory.getLogger(Many2ManyTest.class);
    private String configureName="hibernate-xml-many2Many.cfg.xml";
    private SessionFactory sessionFactory = null;
    @Before
    public void setUp() throws Exception {
        logger.debug("测试多对多开始");
        sessionFactory = HibernateUtils.getSessionFactory(configureName);
    }
    @Test
    public void testCreate() throws Exception {
        logger.debug("测试一对多创建表");
    }
    @Test
    public void testSave() throws Exception {
        logger.debug("测试一对多创建表");
        User user1 = new User("user1");
//        User user2 = new User("user2");
//        User user3 = new User("user3");
//        User user4 = new User("user4");
//        User user5 = new User("user5");

        Role role1 = new Role("role1");
        Role role2 = new Role("role2");
        Role role3 = new Role("role3");
        Role role4 = new Role("role4");
        Role role5 = new Role("role5");

        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);
        roles1.add(role2);
        roles1.add(role3);
        roles1.add(role4);
        roles1.add(role5);

        user1.setRoles(roles1);

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(user1);
        tx.commit();
        session.close();

    }
    @Test
    public void testDropTable(){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("drop table t_xml_user_role ").executeUpdate();
        session.createSQLQuery("drop table t_xml_user ").executeUpdate();
        session.createSQLQuery("drop table t_xml_role ").executeUpdate();
        tx.commit();
        session.close();
    }
    @After
    public void tearDown() throws Exception {
        logger.debug("测试多对多结束");
    }




}