package com.andy.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @NAME : bootweb com.andy.hibernate.jpa
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 11:24
 * @DESC :
 */
public class EntityManagerUtil {
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory= Persistence.createEntityManagerFactory("myJpa");
    }

    public static void main(String[] args){
        System.out.println("entityManagerFactory = "+entityManagerFactory);

        entityManagerFactory.close();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }



}
