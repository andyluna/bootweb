package com.andy.maven.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @name : springstudy  com.andy.maven.health
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-22 Friday 10:24
 */
@Component("myfirst")
public class MyFirstHealthIndicator implements HealthIndicator {
	private  static final String VERSION = "v1.0.0";
	@Override
	public Health health() {
		int code = 0;
		if(code != 0){
			Health.down().withDetail("code",code).withDetail("version",VERSION).build();
		}
		return Health.up().withDetail("code",code).withDetail("version",VERSION).up().build();
	}
}
