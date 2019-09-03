package com.andy.maven.second.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @name : springstudy  com.andy.maven.entity.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-17 Sunday 11:25
 */
@Entity
@Table(name="T_SECOND_AUDI")
@Data
public class Audi {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Date createDate;


}
