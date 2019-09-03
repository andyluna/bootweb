package com.andy.hibernate.xml.onetoonefk;

import com.andy.hibernate.utils.HibernateUtils;
import com.andy.hibernate.xml.onetoonefk.entity.Card;
import com.andy.hibernate.xml.onetoonefk.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetoonefk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 19:19
 * @DESC :
 */
public class One2OneFkTest {
    private static final Logger logger = LoggerFactory.getLogger(One2OneFkTest.class);
    private String configureName="hibernate-xml-one2onefk.cfg.xml";
    private SessionFactory sessionFactory = null;
    @Before
    public void setUp() throws Exception {
        logger.debug("1对1外键开始");
        sessionFactory = HibernateUtils.getSessionFactory(configureName);
    }
    @Test
    public void test1() throws Exception {
        logger.debug("1对1外键表创建成功");
    }

    @Test
    public void testSave() throws Exception {
        Person p = new Person();
        p.setName("外键名字1");

        Card card = new Card();
        card.setName("外键身份证1");
        p.setCard(card);
        card.setPerson(p);

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();


    }

    @After
    public void tearDown() throws Exception {
        if(sessionFactory!=null){
            sessionFactory.close();
        }
        logger.debug("1对1外键结束");
    }
}