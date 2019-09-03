package com.andy.hibernate.xml.onetomany;

import com.andy.hibernate.utils.HibernateUtils;
import com.andy.hibernate.xml.onetomany.entity.Customer;
import com.andy.hibernate.xml.onetomany.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @NAME : bootweb com.andy.hibernate.xml.onetomany.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 15:00
 * @DESC :
 */
public class One2ManyTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected String configureName="hibernate-xml-one2Many.cfg.xml";
    protected SessionFactory sessionFactory = null;
    protected  Session session;
    protected Transaction tx;
    public String display="测试1对多";

    @Before
    public void setUp() throws Exception {
        init();
        logger.debug(display+" 开始");
        sessionFactory = HibernateUtils.getSessionFactory(configureName);
        if(sessionFactory==null){
            sessionFactory= HibernateUtils.getSessionFactory(configureName);
        }
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }



    @Test
    public void test1() throws Exception {
        logger.debug(display+" 建表");
    }
    //@Test
    public void testSave(){
        logger.debug("测试一对多 级联保存");
        List<Customer> customers = createCustomer(50);
        customers.forEach(c->session.save(c));
    }


    //@Test
    public void testDropTable(){
        session.createSQLQuery("drop table t_xml_order ").executeUpdate();
        session.createSQLQuery("drop table t_xml_customer ").executeUpdate();
    }

    @After
    public void tearDown() throws Exception {
        if(tx!=null && tx.isActive()){
            tx.commit();
        }
        if(session!=null){
            session.close();
        }
//        if(sessionFactory!=null){
//            sessionFactory.close();
//        }
        logger.debug(display+" 结束");
    }


    /**
     * 创建数据
     * @param n 条数
     * @return
     */
    public List<Customer> createCustomer(int n){
        List<Customer> customers = new ArrayList<>();
        for (int i=0;i<n;i++){
            Customer customer = new Customer();
            customer.setName("老王"+(i+1));
            customer.setAge(40);
            customer.setGender("gender"+(i+1));
            customer.setLevel("至尊IP"+(i+1));
            customer.setCreateDate(new Date());
            Order order1 = new Order(customer.getName()+"的订单"+(i+1),"鼠标",new Date(),customer);
            Order order2 = new Order(customer.getName()+"的订单"+(i+2),"鼠标",new Date(),customer);
            Order order3 = new Order(customer.getName()+"的订单"+(i+3),"鼠标",new Date(),customer);
            Set<Order> orders = new HashSet<>();
            orders.add(order1);
            orders.add(order2);
            orders.add(order3);

            customer.setOrders(orders);

            customers.add(customer);
        }
        return customers;
    }
    public void init() {

    }

    public void setConfigureName(String configureName) {
        this.configureName = configureName;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}