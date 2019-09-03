package com.andy.maven.primary.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @name : springstudy  com.andy.maven.primary.entity.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-19 Tuesday 18:45
 */
@Entity
@Table(name="T_PRIMARY_CAR")
@Data
public class Car {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Date createDate;




}
