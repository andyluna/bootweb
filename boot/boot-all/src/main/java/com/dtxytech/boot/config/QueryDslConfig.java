package com.dtxytech.boot.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @NAME : bootweb com.dtxytech.boot.config
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 17:19
 * @DESC :
 */
@Configuration
public class QueryDslConfig {

    @Bean
    @ConditionalOnMissingBean
    public JPAQueryFactory jpaQueryFactory
            (EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

}
