package com.andy.maven.endpoint;

import cn.hutool.core.lang.Singleton;
import cn.hutool.system.HostInfo;
import cn.hutool.system.JavaInfo;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JavaSpecInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.JvmSpecInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.UserInfo;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @name : springstudy  com.andy.maven.endpoint
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-22 Friday 10:10
 */
@Endpoint(id="hutool")
public class HutoolEndPoint {

	@ReadOperation
	public Map<String,Object> test(){
		Map<String,Object> result = new HashMap<>();

		result.put("JvmSpecInfo", Singleton.get(JvmSpecInfo.class).toString());
		result.put("JvmInfo", Singleton.get(JvmInfo.class).toString());
		result.put("JavaSpecInfo", Singleton.get(JavaSpecInfo.class).toString());
		result.put("JavaInfo", Singleton.get(JavaInfo.class).toString());
		result.put("JavaRuntimeInfo", Singleton.get(JavaRuntimeInfo.class).toString());
		result.put("OsInfo", Singleton.get(OsInfo.class).toString());
		result.put("UserInfo", Singleton.get(UserInfo.class).toString());
		result.put("HostInfo", Singleton.get(HostInfo.class).toString());
		result.put("RuntimeInfo", Singleton.get(RuntimeInfo.class).toString());

		return result;
	}

}
