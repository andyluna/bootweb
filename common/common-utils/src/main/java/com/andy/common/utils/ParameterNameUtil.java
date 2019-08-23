package com.andy.common.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @name : bootweb  com.andy.common.utils
 * @desc : 获取方法或构造函数参数参数名称
 * @author: xiangdan
 * @time : 2019-03-20 Wednesday 08:50
 */
public class ParameterNameUtil extends DefaultParameterNameDiscoverer{

	public ParameterNameUtil() {
		super();
	}

	@Override
	public void addDiscoverer(ParameterNameDiscoverer pnd) {
		super.addDiscoverer(pnd);
	}

	@Override
	public String[] getParameterNames(Method method) {
		return super.getParameterNames(method);
	}

	@Override
	public String[] getParameterNames(Constructor<?> ctor) {
		return super.getParameterNames(ctor);
	}


	public static String[] getStaticParameterNames(Method method) {
		return new ParameterNameUtil().getParameterNames(method);
	}

	public static String[] getStaticParameterNames(Constructor<?> ctor) {
		return new ParameterNameUtil().getParameterNames(ctor);
	}

}
