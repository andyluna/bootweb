package com.andy.maven.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

/**
 * @name : springstudy  com.andy.maven.entity.com.andy.maven.second.entity.User
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-17 Sunday 12:06
 */
public class UserTest {
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

	@Before
	public void init() {
		logger.debug("test init");
	}


	@Test
	public void test1() {
		assertEquals(2, 1 + 1);
	}


	@After
	public void destory() {
		logger.debug("test destory");
	}
}