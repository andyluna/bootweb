//package com.dtxytech.boot.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.BeanFactoryAware;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
//import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
//import org.springframework.transaction.jta.JtaTransactionManager;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @name : springstudy  com.andy.maven.config
// * @desc : 多个数据源公共的属性注入
// * @author: xiangdan
// * @time : 2019-02-20 Wednesday 10:23
// */
//public abstract class AbstractMutiDataSourceConfig implements BeanFactoryAware {
//
//	private JpaProperties jpaProperties;
//
//	private HibernateProperties hibernateProperties;
//
//	private ConfigurableListableBeanFactory beanFactory;
//
//	private final JtaTransactionManager jtaTransactionManager;
//
//	private final TransactionManagerCustomizers transactionManagerCustomizers;
//
//
//
//	public AbstractMutiDataSourceConfig(JpaProperties jpaProperties,
//                                        HibernateProperties hibernateProperties,
//                                        ObjectProvider<JtaTransactionManager> jtaTransactionManager,
//                                        ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
//		this.jpaProperties = jpaProperties;
//		this.hibernateProperties = hibernateProperties;
//		this.jtaTransactionManager = jtaTransactionManager.getIfAvailable();
//		this.transactionManagerCustomizers = transactionManagerCustomizers.getIfAvailable();
//	}
//	@Override
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
//	}
//
//	protected final boolean isJta() {
//		return (this.jtaTransactionManager != null);
//	}
//
//	protected Map<String, Object> getVendorProperties() {
//		HibernateSettings hibernateSettings = new HibernateSettings();
//		return new LinkedHashMap<>(
//				this.hibernateProperties.determineHibernateProperties(this.jpaProperties.getProperties(),hibernateSettings)
//		);
//	}
//
//
//	protected String[] getPackagesToScan() {
//		List<String> packages = EntityScanPackages.get(this.beanFactory)
//				.getPackageNames();
//		if (packages.isEmpty() && AutoConfigurationPackages.has(this.beanFactory)) {
//			packages = AutoConfigurationPackages.get(this.beanFactory);
//		}
//		return StringUtils.toStringArray(packages);
//	}
//
//	protected String[] getMappingResources() {
//		List<String> mappingResources = this.jpaProperties.getMappingResources();
//		return (!ObjectUtils.isEmpty(mappingResources)
//				? StringUtils.toStringArray(mappingResources) : null);
//	}
//
//}
