package com.dtxytech.boot.service;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.Employee;
import com.dtxytech.boot.utils.SpecificationUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @NAME : bootweb com.dtxytech.boot.service
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 10:50
 * @DESC : 测试 Springdata-jpa 默认方法
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;
    /**********************  1.增加 ************************************************/
    @Test
    public void saveEmployee() {
        log.debug("测试 saveEmployee   ");
        Employee emp = new Employee();
        emp.setFirstName("xiang");
        emp.setLastName("丹");
        emp.setIdCard("432524198811148372");
        emp.setPostCode("410000");
        emp.setCreateDate(new Date());
        employeeService.saveEmployee(emp);
        log.debug("保存成功 ，此时emp主键自动赋值上了:{}"+emp);
    }

    @Test
    public void saveAllEmployee() {
        List<Employee> emps = new ArrayList<>();
        Employee emp = null;
        for(int i=0;i<100;i++){
            emp = new Employee();
            emp.setFirstName("xiang批量-firstName"+i);
            emp.setLastName("lastName"+i);
            emp.setIdCard("43252419881114"+i);
            emp.setPostCode("410000");
            emp.setCreateDate(new Date());
            emps.add(emp);
        }
        employeeService.saveAllEmployee(emps);
        emps.forEach(e->log.debug("{}",e));
    }

    @Test
    public void saveAndFlushEmployee() {
        log.debug("测试 saveAndFlushEmployee   ");
        Employee emp = new Employee();
        emp.setFirstName("hello");
        emp.setLastName("world");
        emp.setIdCard("asdasdasdsadasdasdas");
        emp.setPostCode("410000");
        emp.setCreateDate(new Date());
        employeeService.saveAndFlushEmployee(emp);
        log.debug("保存成功 ，此时emp主键自动赋值上了:{}"+emp);
    }


    /**********************  2.删除 ************************************************/

    @Test
    public void delete() {
        Employee emp = new Employee();
        emp.setId(3);
        employeeService.delete(emp);
    }

    @Test
    public void deleteInBatch() {
        List<Employee> emps = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setId(5);
        emps.add(emp1);
        Employee emp2 = new Employee();
        emp2.setId(5);
        emps.add(emp2);
        employeeService.deleteInBatch(emps);
    }

    @Test
    public void deleteAll() {
        employeeService.deleteAll();
    }

    @Test
    public void deleteAll1() {
        List<Employee> emps = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setId(151);
        emps.add(emp1);
        Employee emp2 = new Employee();
        emp2.setId(152);
        emps.add(emp2);
        employeeService.deleteAll(emps);
    }

    @Test
    public void deleteById() {
        employeeService.deleteById(2);
    }

    /**********************  3.修改 ************************************************/

    @Test
    public void testUpdate() {
        Optional<Employee> byId = employeeService.findById(2);
        if(byId.isPresent()){
            Employee employee = byId.get();
            employee.setLastName("修改一下");
            employeeService.saveEmployee(employee);
        }
    }


    /**********************  4.查询 ************************************************/

    @Test
    public void findById() {
        Optional<Employee> optEmp = employeeService.findById(135);
        Employee emp = optEmp.orElse(null);
        log.debug("findById返回结果：{}",emp);
    }

    @Test
    public void findOneByExample() {
        Employee employee = new Employee();
        employee.setId(123);
        employee.setFirstName("xiang");
        ExampleMatcher matcher = ExampleMatcher.matchingAll();//定义匹配器 这里还可以自定义
        Example<Employee> example = Example.of(employee, matcher);//组装查询条件
        Optional<Employee> optEmp = employeeService.findOne(example);
        Employee emp = optEmp.orElse(null);
        log.debug("findById返回结果：{}",emp);
    }

    @Test
    public void findOneBySpec() {
        Specification<Employee> spec = (Specification<Employee>) (root, query, cb) ->
            cb.and(
                    cb.equal(root.get("id"),12),
                    cb.like(root.get("firstName"),"xiang%")
            );
        Optional<Employee> optEmp = employeeService.findOne(spec);
        Employee emp = optEmp.orElse(null);
        log.debug("findById返回结果：{}",emp);
    }


    @Test
    public void getOne() {
        Employee emp = employeeService.getOne(12);
        log.debug("getOne返回结果：{}",emp);
    }

    @Test
    public void findAllById(){
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i < 15; i++) {
            ids.add(i);
        }
        List<Employee> emps = employeeService.findAllById(ids);
        log.debug("findAllById 查询总数:{} ",emps.size());
        emps.forEach(e->log.debug("{}",e));
    }


    /**********************  5.分页查询、分组查询、排序、部分字段查询**********/
    @Test
    public void count() {
        long count = employeeService.count();
        log.debug("count 不带参数查询全表 =  {}",count);
    }

    @Test
    public void testCountByExample() {
        Employee employee = new Employee();
        //employee.setId(123);
        employee.setFirstName("xiang");
        employee.setIdCard("43252419");
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Employee> example = Example.of(employee, matcher);//组装查询条件

        long count = employeeService.count(example);
        log.debug("count 带上example参数参数查询全表 =  {}",count);
    }

    @Test
    public void testCountBySpec() {
        Specification<Employee> spec = (Specification<Employee>) (root, query, cb) ->
                cb.and(
                        cb.like(root.get("idCard"),"432524%"),
                        cb.like(root.get("firstName"),"xiang%")
                );
        long count = employeeService.count(spec);
        log.debug("testCountBySpec 全表 =  {}",count);
    }

    @Test
    public void findAll() {
        List<Employee> all = employeeService.findAll();
        log.debug("不带参数查询全表成功 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllBySort() {
        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));
        List<Employee> all = employeeService.findAll(sort);
        log.debug("不带参数 排序查询全表成功 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllByExmaple() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Example<Employee> example = SpecificationUtils.findAllExample(employee);
        List<Employee> all = employeeService.findAll(example);
        log.debug("带参数参数 example 查询 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllExampleAndSort() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Example<Employee> example = SpecificationUtils.findAllExample(employee);

        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));

        List<Employee> all = employeeService.findAll(example,sort);
        log.debug("带参数参数 example and Sort 查询 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllByPageable() {
        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));
        Pageable pageable = PageRequest.of(0,5,sort);
        Page<Employee> pagecontent = employeeService.findAll(pageable);
        log.debug("带参数参数 pageable 查询 数据总数:{} ",pagecontent);
        pagecontent.forEach(s->log.debug("{}",s));

    }

    @Test
    public void testFindAllByExampleAndPageable() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Example<Employee> example = SpecificationUtils.findAllExample(employee);

        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));
        Pageable pageable = PageRequest.of(0,5,sort);
        Page<Employee> all = employeeService.findAll(example,pageable);
        log.debug("带参数参数 testFindAllByExampleAndPageable 查询 数据总数:{} ",all);
        all.forEach(s->log.debug("{}",s));

    }

    @Test
    public void testFindAllBySpec() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Specification<Employee> spec = SpecificationUtils.findAllSpecification(employee);

        List<Employee> all = employeeService.findAll(spec);
        log.debug("带参数参数 testFindAllBySpec 查询 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllBySpecAndPageable() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Specification<Employee> spec = SpecificationUtils.findAllSpecification(employee);

        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));
        Pageable pageable = PageRequest.of(0,5,sort);


        Page<Employee> all = employeeService.findAll(spec,pageable);
        log.debug("带参数参数 testFindAllBySpecAndPageable 查询 数据总数:{} ",all);
        all.forEach(s->log.debug("{}",s));
    }

    @Test
    public void testFindAllBySpecAndSort() {
        Employee employee = new Employee();
        employee.setFirstName("xiang");
        employee.setIdCard("4325241");
        Specification<Employee> spec = SpecificationUtils.findAllSpecification(employee);

        Sort sort = Sort.by(Sort.Order.desc("firstName"),Sort.Order.asc("idCard"));
        Pageable pageable = PageRequest.of(0,5,sort);


        List<Employee> all = employeeService.findAll(spec,sort);
        log.debug("带参数参数 testFindAllBySpecAndSort 查询 数据总数:{} ",all.size());
        all.forEach(s->log.debug("{}",s));
    }



}