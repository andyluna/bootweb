package com.andy.maven;

import com.andy.maven.endpoint.HutoolEndPoint;
import com.andy.maven.endpoint.MyEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Arrays;

/**
 * @name : springstudy  com.andy.maven
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-22 Friday 09:08
 */
@SpringBootApplication
public class BootActuatorApplication {

	private static final Logger logger = LoggerFactory.getLogger(BootActuatorApplication.class);

	public static void main(String[] args){

		StandardServletEnvironment env = (StandardServletEnvironment) SpringApplication.run(BootActuatorApplication.class,args).getEnvironment();
		String[] activeProfiles = env.getActiveProfiles();



		logger.info("activeProfiles={}",Arrays.toString(activeProfiles));

		String[] defaultProfiles = env.getDefaultProfiles();

		logger.info("defaultProfiles={}", Arrays.toString(defaultProfiles));





	}

	@Configuration
	static class MyEndpointConfiguration{
		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnEnabledEndpoint
		public MyEndPoint myEndPoint(){
			return new MyEndPoint();
		}

		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnEnabledEndpoint
		public HutoolEndPoint hutoolEndPoint(){
			return new HutoolEndPoint();
		}

	}

}
