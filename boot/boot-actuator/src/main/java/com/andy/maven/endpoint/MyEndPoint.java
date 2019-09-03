package com.andy.maven.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @name : springstudy  com.andy.maven.endpoint
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-22 Friday 09:57
 */
@Endpoint(id = "andyendpoint")
public class MyEndPoint {

	@Value("${myendpoint.id}")
	private String id;
	@Value("${myendpoint.name}")
	private String name;
	@Value("${myendpoint.context}")
	private String context;

	@ReadOperation
	public Map<String,String> test(){
		Map<String,String> result = new HashMap<>();
		result.put("id",id);
		result.put("name",name);
		result.put("context",context);
		return result;
	}





}
