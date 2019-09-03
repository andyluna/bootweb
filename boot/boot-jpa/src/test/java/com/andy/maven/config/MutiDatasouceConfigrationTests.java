package com.andy.maven.config;

import com.andy.maven.JpaApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @name : springstudy  com.andy.maven.config.com.andy.maven.config.MutiDatasouceConfigration
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-19 Tuesday 16:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JpaApplication.class})
public class MutiDatasouceConfigrationTests {
	private static final Logger logger = LoggerFactory.getLogger(MutiDatasouceConfigrationTests.class);

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("secondJdbcTemplate")
	private JdbcTemplate secondJdbcTemplate;
	@Autowired
	@Qualifier("thirdJdbcTemplate")
	private JdbcTemplate thirdJdbcTemplate;

	@Resource(name="dataSource")
	private DataSource dataSource;
	@Resource(name="secondDataSource")
	private DataSource secondDataSource;
	@Resource(name="thirdDataSource")
	private DataSource thirdDataSource;

	@Autowired
	private ApplicationContext context;

	@Before
	public void init() {
		logger.debug("test init");
	}


	@Test
	public void test1() throws SQLException {

		logger.info("1.jdbcTemplate       = {}",jdbcTemplate);
		logger.info("2.secondJdbcTemplate = {}",secondJdbcTemplate);
		logger.info("3.thirdJdbcTemplate  = {}",thirdJdbcTemplate);






		logger.info("1.dataSource       = {} , {}",dataSource,dataSource.getConnection());
		logger.info("2.secondDataSource = {} , {}",secondDataSource,secondDataSource.getConnection());
		logger.info("3.thirdDataSource  = {} , {}",thirdDataSource.getConnection());



	}


	@After
	public void destory() {
		logger.debug("test destory");
//		DataSource secondDataSource1 = context.getBean("dataSource", DataSource.class);
//
//		DataSource secondDataSource2= context.getBean("secondDataSource", DataSource.class);
//
//		DataSource secondDataSource3 = context.getBean("thirdDataSource", DataSource.class);
//		logger.info("secondDataSource1 = {}",secondDataSource1);
//		logger.info("secondDataSource2 = {}",secondDataSource2);
//		logger.info("secondDataSource3 = {}",secondDataSource3);
	}
}