package com.andy.maven.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @name : bootweb  com.andy.boot.groovy.util
 * @desc :
 * @author: xiangdan
 * @time : 2019-04-09 星期二 20:09
 */
public class UUIdGenerator implements IdentifierGenerator {



	public UUIdGenerator() {
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor s, Object obj) {
		return UUID.randomUUID().toString().replace("-","");
	}
}
