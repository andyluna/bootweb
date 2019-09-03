package com.andy.maven.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * @name : springstudy  com.andy.maven.config
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-26 Tuesday 10:50
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	private static final Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		if(beanName.equals("carRepository")){
			logger.info("开始执行之前beanName = {}",bean);

		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(beanName.equals("carRepository")){
			logger.info("执行完成之后beanName = {}",bean);
			if (bean instanceof JpaRepositoryFactoryBean){
				JpaRepositoryFactoryBean jfb = (JpaRepositoryFactoryBean)bean;

				Repository repository = jfb.getObject();
				logger.info("repository = {}" ,repository);

			}
		}
		return bean;
	}
}
