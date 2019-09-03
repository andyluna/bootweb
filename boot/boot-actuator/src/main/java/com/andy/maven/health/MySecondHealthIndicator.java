package com.andy.maven.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @name : springstudy  com.andy.maven.health
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-22 Friday 10:44
 */
@Component("mysecond")
public class MySecondHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		builder.up()
				.withDetail("mysecondHealth","hello this is my second Health")
				.withDetail("app", "Alive and Kicking")
				.withDetail("error", "Nothing! I'm good.");

	}
}
