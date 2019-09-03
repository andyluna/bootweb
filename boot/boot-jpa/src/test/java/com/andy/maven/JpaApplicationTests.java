package com.andy.maven;

//import com.andy.maven.second.service.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

//import org.springframework.beans.factory.annotation.Autowired;

/**
 * @name : springstudy  com.andy.maven.com.andy.maven.JpaApplication
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-17 Sunday 11:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JpaApplication.class})
public class JpaApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(JpaApplicationTests.class);

	//@Autowired
	//private UserService userService;

	@Before
	public void init() {
		logger.debug("test init");
	}


	@Test
	public void test1() {
		//logger.debug("userService:{}",userService);
		assertEquals(2, 1 + 1);
	}


	@After
	public void destory() {
		logger.debug("test destory");
	}
}