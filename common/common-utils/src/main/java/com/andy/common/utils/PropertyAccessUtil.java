package com.andy.common.utils;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * @name : bootweb  com.andy.common.utils
 * @desc : 属性访问工具类  现根据属性的 get，set方法进行取值 去过报错则 直接根据属性名取值
 * @author: xiangdan
 * @time : 2019-03-20 Wednesday 09:02
 */
public class PropertyAccessUtil extends BeanWrapperImpl {
	private DirectFieldAccessor directFieldAccessor;

	public PropertyAccessUtil(Object entity) {
		super(entity);
		directFieldAccessor = new DirectFieldAccessor(entity);
	}

	public PropertyAccessUtil(Class<?> type) {
		super(type);
	}

	@Override
	@Nullable
	public Object getPropertyValue(String propertyName) {

		try {
			return super.getPropertyValue(propertyName);
		} catch (NotReadablePropertyException e) {
			return directFieldAccessor.getPropertyValue(propertyName);
		}
	}

	@Override
	public void setPropertyValue(String propertyName, @Nullable Object value) {

		try {
			super.setPropertyValue(propertyName, value);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValue(new PropertyValue(propertyName,value));
		}
	}


	@Override
	public void setPropertyValue(PropertyValue pv) throws BeansException {
		try {
			super.setPropertyValue(pv);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValue(pv);
		}
	}

	@Override
	protected void setPropertyValue(PropertyTokenHolder tokens, PropertyValue pv) throws BeansException {
		try {
			super.setPropertyValue(tokens, pv);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValue(pv);
		}
	}

	@Override
	public void setPropertyValues(Map<?, ?> map) throws BeansException {
		try {
			super.setPropertyValues(map);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValues(map);
		}
	}

	@Override
	public void setPropertyValues(PropertyValues pvs) throws BeansException {
		try {
			super.setPropertyValues(pvs);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValues(pvs);
		}
	}

	@Override
	public void setPropertyValues(PropertyValues pvs, boolean ignoreUnknown) throws BeansException {
		try {
			super.setPropertyValues(pvs,ignoreUnknown);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValues(pvs,ignoreUnknown);
		}
	}

	@Override
	public void setPropertyValues(PropertyValues pvs, boolean ignoreUnknown, boolean ignoreInvalid) throws BeansException {
		try {
			super.setPropertyValues(pvs, ignoreUnknown, ignoreInvalid);
		} catch (NotWritablePropertyException e) {
			directFieldAccessor.setPropertyValues(pvs, ignoreUnknown, ignoreInvalid);
		}
	}
}
