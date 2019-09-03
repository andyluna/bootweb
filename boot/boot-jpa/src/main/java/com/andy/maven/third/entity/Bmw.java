package com.andy.maven.third.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @name : springstudy  com.andy.maven.third.entity.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-19 Tuesday 18:52
 */
@Entity
@Table(name="T_THIRD_BMW")
@Data
public class Bmw {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Date createDate;

}
