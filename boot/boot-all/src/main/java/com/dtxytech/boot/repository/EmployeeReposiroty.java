package com.dtxytech.boot.repository;

import com.dtxytech.boot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 10:06
 * @DESC :
 */
public interface EmployeeReposiroty extends JpaRepository<Employee,Integer> , JpaSpecificationExecutor<Employee> {

}


