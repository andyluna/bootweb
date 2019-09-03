package com.dtxytech.boot.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:28
 * @DESC : @NoRepositoryBean注解的接口 不会被spring管理
 *          用途： 这种方式 一般用来做扩展定义成一个集成很多方法的父接口
 *               定义一些公共的方法 给子类继承使用
 *          JReapRepository 就是这么做的！
 */
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable>  extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {


}
