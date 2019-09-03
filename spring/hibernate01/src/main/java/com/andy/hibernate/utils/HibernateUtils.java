package com.andy.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @NAME : bootweb com.andy.hibernate.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/28 八月 13:40
 * @DESC :
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory=null;


    public static SessionFactory getSessionFactory(String configue){
        if(sessionFactory==null){
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(configue).build();
            Metadata md = new MetadataSources(registry).buildMetadata();
            SessionFactoryBuilder sfb = md.getSessionFactoryBuilder();
            sessionFactory = sfb.build();
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory1(String configue){
        if(sessionFactory==null){
            sessionFactory = new Configuration().configure(configue).buildSessionFactory();
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory3(String configue){
        if(sessionFactory==null){
            Configuration configure = new Configuration().configure(configue);
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configure.getProperties()).build();
            sessionFactory = configure.buildSessionFactory(sr);
        }
        return sessionFactory;
    }



    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory=null;
        }
    }


}
