package com.dtxytech.boot.config;

/**
 * @name : springstudy  com.andy.maven.config.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-18 Monday 16:07
 */

/**
 @EnableJpaRepositories(
 // basePackages 支持多包扫描，用文本数组的形式就可以
 // 比如这样 {"com.simply.zuozuo.repo","com.simply.zuozuo.mapper"}
 // basePackages = {"com.dtxytech.boot.repository"},
 value = {},// 指定里面的存储库类
 basePackageClasses = {BootAllApplication.class},
 // 通过什么后缀来命名实现类，比如接口A的实现，名字叫AImpl
 repositoryImplementationPostfix = "Impl",
 // named SQL存放的位置，默认为META-INF/jpa-named-queries.properties
 namedQueriesLocation = "",
 // 枚举中有三个值，
 // CREATE_IF_NOT_FOUND，先搜索用户声明的，不存在则自动构建
 // USE_DECLARED_QUERY，用户声明查询
 // CREATE，按照接口名称自动构建查询
 queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND,
 // 指定Repository的工厂类
 repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class,
 // 指定Repository的Base类
 repositoryBaseClass = DefaultRepositoryBaseClass.class,
 // 实体管理工厂引用名称，对应到@Bean注解对应的方法
 entityManagerFactoryRef = "entityManagerFactory",
 // 事务管理工厂引用名称，对应到@Bean注解对应的方法
 transactionManagerRef = "transactionManager",
 // 是否考虑嵌套存储库
 considerNestedRepositories = false,
 // 开启默认事务
 enableDefaultTransactions = true,
 //引导方式  延迟启动
 bootstrapMode = BootstrapMode.LAZY
 )
 */
//@Configuration
public class MutiDatasouceConfigration {


	/**默认数据源配置结束**/
//	@Configuration
//	@EnableTransactionManagement
//	@EnableJpaRepositories(
//			entityManagerFactoryRef="entityManagerFactory",
//			transactionManagerRef="transactionManager",
//			repositoryFactoryBeanClass=JpaRepositoryFactoryBean.class,
//			basePackages= { "com.dtxytech.boot" }) //设置Repository所在位置
//	public static class PrimaryDataSourceConfig extends AbstractMutiDataSourceConfig {
//
//		public PrimaryDataSourceConfig(JpaProperties jpaProperties,
//		                               HibernateProperties hibernateProperties,
//		                               ObjectProvider<JtaTransactionManager> jtaTransactionManager,
//		                               ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
//		}
//
//		@Bean
//		@Primary
//		@ConfigurationProperties(prefix="spring.datasource")
//		public DataSource dataSource(){
//			return new DruidDataSource();
//		}
//
//
//		@Bean
//		@Primary
//		public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//				@Qualifier("dataSource") DataSource dataSource,
//				EntityManagerFactoryBuilder factoryBuilder) {
//			Map<String, Object> vendorProperties = getVendorProperties();
//			return factoryBuilder
//					.dataSource(dataSource)
//					.packages(getPackagesToScan())
//					.packages("com.dtxytech.boot.entity")
//					.properties(vendorProperties)
//					.mappingResources(getMappingResources())
//					.jta(isJta())
//					.persistenceUnit("entityManagerFactory")
//					.build();
//		}
//
//		@Bean
//		@Primary
//		public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource,
//		                                                     EntityManagerFactoryBuilder builder) {
//			return new JpaTransactionManager(entityManagerFactory(dataSource,builder).getObject());
//		}


		//		@Bean
//		@Primary
//		public JdbcTemplate jdbcTemplate(@Autowired @Qualifier("dataSource") DataSource dataSource){
//			return new JdbcTemplate(dataSource);
//		}

//		@Bean
//		@Primary
//		public HibernateTemplate hibernateTemplate(@Qualifier("sessionFactory") SessionFactory sessionFactory){
//			return new HibernateTemplate(sessionFactory);
//		}

//		@PersistenceUnit
//		@PersistenceContext
		//@Transactional()


//		@Bean
//		@Primary
//		public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
//			return entityManagerFactory.unwrap(SessionFactory.class);
//		}
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

//	}
	/**默认数据源配置结束**/







	/**第2个数据源配置结束**/
//	@Configuration
//	@EnableTransactionManagement
//	@EnableJpaRepositories(
//			entityManagerFactoryRef="secondEntityManagerFactory",
//			transactionManagerRef="secondTransactionManager",
//			basePackages= { "com.andy.maven.second" }) //设置Repository所在位置
//	public static class SecondDataSourceConfig extends AbstractMutiDataSourceConfig {
//
//		public SecondDataSourceConfig(JpaProperties jpaProperties,
//		                               HibernateProperties hibernateProperties,
//		                               ObjectProvider<JtaTransactionManager> jtaTransactionManager,
//		                               ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
//		}
//
//		@Bean
//		@ConfigurationProperties(prefix="spring.datasource.second")
//		public DataSource secondDataSource(){
//			return new HikariDataSource();
//		}
//
//		@Bean
//		public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource secondDataSource){
//			return new JdbcTemplate(secondDataSource);
//		}
//		@Bean
//		public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
//				@Qualifier("secondDataSource") DataSource secondDataSource,
//				EntityManagerFactoryBuilder factoryBuilder) {
//			Map<String, Object> vendorProperties = getVendorProperties();
//			return factoryBuilder
//					.dataSource(secondDataSource)
//					.packages(getPackagesToScan())
//					.packages("com.andy.maven.second")
//					.properties(vendorProperties)
//					.mappingResources(getMappingResources())
//					.jta(isJta())
//					.persistenceUnit("secondEntityManagerFactory")
//					.build();
//		}
//		@Bean
//		public PlatformTransactionManager secondTransactionManager(@Qualifier("secondDataSource") DataSource secondDataSource,
//		                                                     EntityManagerFactoryBuilder builder) {
//			return new JpaTransactionManager(secondEntityManagerFactory(secondDataSource,builder).getObject());
//		}
//
//	}
	/**第2个数据源配置结束**/









	/**第3个数据源配置结束**/
//	@Configuration
//	@EnableTransactionManagement
//	@EnableJpaRepositories(
//			entityManagerFactoryRef="thirdEntityManagerFactory",
//			transactionManagerRef="thirdTransactionManager",
//			basePackages= { "com.andy.maven.third" }) //设置Repository所在位置
//	public static class ThirdDataSourceConfig extends AbstractMutiDataSourceConfig {
//
//		public ThirdDataSourceConfig(JpaProperties jpaProperties,
//		                              HibernateProperties hibernateProperties,
//		                              ObjectProvider<JtaTransactionManager> jtaTransactionManager,
//		                              ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//			super(jpaProperties, hibernateProperties, jtaTransactionManager, transactionManagerCustomizers);
//		}
//
//		@Bean
//		@ConfigurationProperties(prefix="spring.datasource.third")
//		public DataSource thirdDataSource(){
//			return new DruidDataSource();
//		}
//
//		@Bean
//		public JdbcTemplate thirdJdbcTemplate(@Qualifier("thirdDataSource") DataSource thirdDataSource){
//			return new JdbcTemplate(thirdDataSource);
//		}
//		@Bean
//		public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(
//				@Qualifier("thirdDataSource") DataSource thirdDataSource,
//				EntityManagerFactoryBuilder factoryBuilder) {
//			Map<String, Object> vendorProperties = getVendorProperties();
//			return factoryBuilder
//					.dataSource(thirdDataSource)
//					.packages(getPackagesToScan())
//					.packages("com.andy.maven.third")
//					.properties(vendorProperties)
//					.mappingResources(getMappingResources())
//					.jta(isJta())
//					.persistenceUnit("thirdEntityManagerFactory")
//					.build();
//		}
//		@Bean
//		public PlatformTransactionManager thirdTransactionManager(@Qualifier("thirdDataSource") DataSource thirdDataSource,
//		                                                           EntityManagerFactoryBuilder builder) {
//			return new JpaTransactionManager(thirdEntityManagerFactory(thirdDataSource,builder).getObject());
//		}
//
//	}
	/**第3个数据源配置结束**/





}
