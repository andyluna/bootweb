package com.andy.maven.primary.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @name : bootweb  com.andy.maven.primary.entity
 * @desc :
 * @author: xiangdan
 * @time : 2019-04-09 星期二 21:28
 */
@Entity
@Table(name="T_PERSON_TEST")
@Data
public class Person {

	@Id
	@GeneratedValue(generator = "hello")
	@GenericGenerator(name="hello",strategy = "com.andy.maven.util.UUIdGenerator")
	private String id;

	private String name;

}
