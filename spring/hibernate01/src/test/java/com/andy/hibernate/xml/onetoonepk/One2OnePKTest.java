package com.andy.hibernate.xml.onetoonepk;

import com.andy.hibernate.utils.HibernateUtils;
import com.andy.hibernate.xml.onetoonepk.entity.Card;
import com.andy.hibernate.xml.onetoonepk.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetoonepk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:18
 * @DESC :
 */
public class One2OnePKTest {
    private static final Logger logger = LoggerFactory.getLogger(One2OnePKTest.class);
    private String configureName="hibernate-xml-one2onepk.cfg.xml";
    private SessionFactory sessionFactory = null;
    @Before
    public void setUp() throws Exception {
        logger.debug("1对1主键开始");
        sessionFactory = HibernateUtils.getSessionFactory(configureName);
    }
    @Test
    public void test1() throws Exception {
        logger.debug("1对1主键表创建成功");
    }

    @Test
    public void testSave() throws Exception {

        List<Person> persons = new ArrayList<>();
        for (int i=0;i<20;i++){
            Person p = new Person();
            p.setName("主键名字"+i);
            Card card = new Card();
            card.setName("主键身份证"+i);
            p.setCard(card);
            card.setPerson(p);
            persons.add(p);
        }
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        persons.forEach(p->session.save(p));
        tx.commit();
        session.close();
    }


    @Test
    public void testGet() throws Exception {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, 2);
        System.out.println(person);
        session.close();
    }
    @Test
    public void testQuery() throws Exception {
        Session session = sessionFactory.openSession();
        Query<Person> query = session.createQuery("from Person order by id asc ", Person.class);
        List<Person> list = query.list();
        list.forEach(p->logger.debug("{}",p));
        session.close();
    }

    @After
    public void tearDown() throws Exception {
        if(sessionFactory!=null){
            sessionFactory.close();
        }
        logger.debug("1对1主键结束");
    }

}