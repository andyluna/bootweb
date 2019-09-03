package com.dtxytech.boot.repository.dsl;

import com.dtxytech.boot.entity.dsl.Mouse;
import com.dtxytech.boot.repository.base.MyBaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.dsl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 17:02
 * @DESC :  测试 QueryDsl
 */
public interface MouseRepository extends MyBaseRepository<Mouse,Integer>, QuerydslPredicateExecutor<Mouse> {


}
