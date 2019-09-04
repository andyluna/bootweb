package com.dtxytech.boot.entity.embbed;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:24
 * @DESC : 基类 定义一些公共的字段
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseData {
    private String createUserId;
    private String createUserName;
    private Date   createDate;

}
