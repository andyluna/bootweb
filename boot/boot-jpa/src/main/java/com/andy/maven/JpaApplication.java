package com.andy.maven;

import com.andy.maven.primary.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @projectName: com.andy.maven.JpaApplication
 * @desc:
 * @author: xiangdan
 * @time : 2019-02-17 10:39
 */
@SpringBootApplication
public class JpaApplication {
	private static final Logger logger = LoggerFactory.getLogger(JpaApplication.class);
	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args);
	    logger.info("执行完成！！！");
//
		DataSource secondDataSource1 = context.getBean("dataSource", DataSource.class);
//
//		DataSource secondDataSource2= context.getBean("secondDataSource", DataSource.class);
//
//		DataSource secondDataSource3 = context.getBean("thirdDataSource", DataSource.class);
//		logger.info("secondDataSource1 = {},{}",secondDataSource1,secondDataSource1.getConnection());
//		logger.info("secondDataSource2 = {},{}",secondDataSource2);
//		logger.info("secondDataSource3 = {},{}",secondDataSource3.getConnection());
//
//
		String[] names = context.getBeanNamesForType(LocalContainerEntityManagerFactoryBean.class);
		logger.info(Arrays.toString(names));


		names = context.getBeanNamesForType(EntityManagerFactory.class);
		logger.info(Arrays.toString(names));

		CarRepository carRepository = context.getBean("carRepository", CarRepository.class);
		logger.info("carRepository = {}",carRepository);


		carRepository = context.getBean("carRepository", CarRepository.class);
		logger.info("carRepository = {}",carRepository);
//
//
//
//		EntityManagerFactory entityManagerFactory = context.getBean("entityManagerFactory",EntityManagerFactory.class);
//
//		logger.info(" bean {},{}",entityManagerFactory.getClass(),entityManagerFactory);
//
//
//
//		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
//
//		logger.info(" sessionFactory {},{}",sessionFactory);
	}
	
}
