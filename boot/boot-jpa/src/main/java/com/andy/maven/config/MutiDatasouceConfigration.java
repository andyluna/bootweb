package com.andy.maven.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @name : springstudy  com.andy.maven.config.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-18 Monday 16:07
 */
@Configuration
public class MutiDatasouceConfigration{


	/**默认数据源配置结束**/
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			entityManagerFactoryRef="entityManagerFactory",
			transactionManagerRef="transactionManager",
			repositoryFactoryBeanClass=JpaRepositoryFactoryBean.class,
			basePackages= { "com.andy.maven.primary" }) //设置Repository所在位置
	public static class PrimaryDataSourceConfig extends AbstractMutiDataSourceConfig {

		public PrimaryDataSourceConfig(JpaProperties jpaProperties,
		                               HibernateProperties hibernateProperties,
		                               ObjectProvider<JtaTransactionManager> jtaTransactionManager,
		                               ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
		}

		@Bean
		@Primary
		@ConfigurationProperties(prefix="spring.datasource.primary")
		public DataSource dataSource(){
			return new HikariDataSource();
		}

		@Bean
		@Primary
		public JdbcTemplate jdbcTemplate(@Autowired @Qualifier("dataSource") DataSource dataSource){
			return new JdbcTemplate(dataSource);
		}

		@Bean
		@Primary
		public HibernateTemplate hibernateTemplate(@Qualifier("sessionFactory") SessionFactory sessionFactory){
			return new HibernateTemplate(sessionFactory);
		}

//		@PersistenceUnit
//		@PersistenceContext
		//@Transactional()


		@Bean
		@Primary
		public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
			return entityManagerFactory.unwrap(SessionFactory.class);
		}

		@Bean
		@Primary
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(
				@Qualifier("dataSource") DataSource dataSource,
				EntityManagerFactoryBuilder factoryBuilder) {
			Map<String, Object> vendorProperties = getVendorProperties();
			return factoryBuilder
					.dataSource(dataSource)
					.packages(getPackagesToScan())
					.packages("com.andy.maven.primary")
					.properties(vendorProperties)
					.mappingResources(getMappingResources())
					.jta(isJta())
					.persistenceUnit("entityManagerFactory")
					.build();
		}



//		@Bean
//		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//
//			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//			vendorAdapter.setDatabase(Database.HSQL);
//			vendorAdapter.setGenerateDdl(true);
//
//			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//			factory.setJpaVendorAdapter(vendorAdapter);
//			factory.setPackagesToScan(getClass().getPackage().getName());
//			factory.setDataSource(dataSource());
//
//			return factory;
//		}

		@Bean
		@Primary
		public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource,
		                                                     EntityManagerFactoryBuilder builder) {
			return new JpaTransactionManager(entityManagerFactory(dataSource,builder).getObject());
		}

	}
	/**默认数据源配置结束**/







	/**第2个数据源配置结束**/
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			entityManagerFactoryRef="secondEntityManagerFactory",
			transactionManagerRef="secondTransactionManager",
			basePackages= { "com.andy.maven.second" }) //设置Repository所在位置
	public static class SecondDataSourceConfig extends AbstractMutiDataSourceConfig {

		public SecondDataSourceConfig(JpaProperties jpaProperties,
		                               HibernateProperties hibernateProperties,
		                               ObjectProvider<JtaTransactionManager> jtaTransactionManager,
		                               ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
		}

		@Bean
		@ConfigurationProperties(prefix="spring.datasource.second")
		public DataSource secondDataSource(){
			return new HikariDataSource();
		}

		@Bean
		public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource secondDataSource){
			return new JdbcTemplate(secondDataSource);
		}
		@Bean
		public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
				@Qualifier("secondDataSource") DataSource secondDataSource,
				EntityManagerFactoryBuilder factoryBuilder) {
			Map<String, Object> vendorProperties = getVendorProperties();
			return factoryBuilder
					.dataSource(secondDataSource)
					.packages(getPackagesToScan())
					.packages("com.andy.maven.second")
					.properties(vendorProperties)
					.mappingResources(getMappingResources())
					.jta(isJta())
					.persistenceUnit("secondEntityManagerFactory")
					.build();
		}
		@Bean
		public PlatformTransactionManager secondTransactionManager(@Qualifier("secondDataSource") DataSource secondDataSource,
		                                                     EntityManagerFactoryBuilder builder) {
			return new JpaTransactionManager(secondEntityManagerFactory(secondDataSource,builder).getObject());
		}

	}
	/**第2个数据源配置结束**/









	/**第3个数据源配置结束**/
	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(
			entityManagerFactoryRef="thirdEntityManagerFactory",
			transactionManagerRef="thirdTransactionManager",
			basePackages= { "com.andy.maven.third" }) //设置Repository所在位置
	public static class ThirdDataSourceConfig extends AbstractMutiDataSourceConfig {

		public ThirdDataSourceConfig(JpaProperties jpaProperties,
		                              HibernateProperties hibernateProperties,
		                              ObjectProvider<JtaTransactionManager> jtaTransactionManager,
		                              ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
		}

		@Bean
		@ConfigurationProperties(prefix="spring.datasource.third")
		public DataSource thirdDataSource(){
			return new DruidDataSource();
		}

		@Bean
		public JdbcTemplate thirdJdbcTemplate(@Qualifier("thirdDataSource") DataSource thirdDataSource){
			return new JdbcTemplate(thirdDataSource);
		}
		@Bean
		public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(
				@Qualifier("thirdDataSource") DataSource thirdDataSource,
				EntityManagerFactoryBuilder factoryBuilder) {
			Map<String, Object> vendorProperties = getVendorProperties();
			return factoryBuilder
					.dataSource(thirdDataSource)
					.packages(getPackagesToScan())
					.packages("com.andy.maven.third")
					.properties(vendorProperties)
					.mappingResources(getMappingResources())
					.jta(isJta())
					.persistenceUnit("thirdEntityManagerFactory")
					.build();
		}
		@Bean
		public PlatformTransactionManager thirdTransactionManager(@Qualifier("thirdDataSource") DataSource thirdDataSource,
		                                                           EntityManagerFactoryBuilder builder) {
			return new JpaTransactionManager(thirdEntityManagerFactory(thirdDataSource,builder).getObject());
		}

	}
	/**第3个数据源配置结束**/





}
