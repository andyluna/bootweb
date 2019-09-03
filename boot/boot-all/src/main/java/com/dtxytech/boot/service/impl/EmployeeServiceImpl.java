package com.dtxytech.boot.service.impl;

import com.dtxytech.boot.entity.Employee;
import com.dtxytech.boot.repository.EmployeeReposiroty;
import com.dtxytech.boot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @NAME : bootweb com.dtxytech.boot.service.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 10:47
 * @DESC : 全部基于SpringData-JPA底层方法 不添加任何额外的方法
 */
@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeReposiroty empRepository;

    /**********************  1.增加 ************************************************/
    @Override
    public Employee saveEmployee(Employee employee) {
        return empRepository.save(employee);
    }
    @Override
    public List<Employee> saveAllEmployee(Iterable<Employee> employees) {
        return empRepository.saveAll(employees);
    }
    @Override
    public Employee saveAndFlushEmployee(Employee employee) {
        return empRepository.saveAndFlush(employee);
    }



    /**********************  2.删除 ************************************************/
    @Override
    public void delete(Employee employee) {
        empRepository.delete(employee);
    }
    @Override
    public void deleteInBatch(Iterable<Employee> employees) {
        empRepository.deleteInBatch(employees);
    }
    @Override
    public void deleteAll() {
        empRepository.deleteAll();
    }
    @Override
    public void deleteAll(Iterable<Employee> employees) {
        empRepository.deleteAll(employees);
    }
    @Override
    public void deleteById(Integer id) {
        empRepository.deleteById(id);
    }


    /**********************  3.修改 ************************************************/







    /**********************  4.查询 ************************************************/

    @Override
    public Optional<Employee> findById(Integer id) {
        return empRepository.findById(id);
    }

    @Override
    public Optional<Employee> findOne(Example<Employee> exp){
        return empRepository.findOne(exp);
    }

    @Override
    public Optional<Employee> findOne(Specification<Employee> spec){
        return empRepository.findOne(spec);
    }

    @Override
    public Employee getOne(Integer id) {
        return empRepository.getOne(id);
    }

    @Override
    public List<Employee> findAllById(Iterable<Integer> ids) {
        return empRepository.findAllById(ids);
    }

    /**********************  5.分页查询、分组查询、排序、部分字段查询**********/

    @Override
    public long count(){
        return empRepository.count();
    }
    @Override
    public long count(Example<Employee> exp){
        return empRepository.count(exp);
    }
    @Override
    public long count(Specification<Employee> spec){
        return empRepository.count(spec);
    }

    @Override
    public List<Employee> findAll() {
        return empRepository.findAll();
    }

    @Override
    public List<Employee> findAll(Sort sort) {
        return empRepository.findAll(sort);
    }

    @Override
    public List<Employee> findAll(Example<Employee> employeeExample) {
        return empRepository.findAll(employeeExample);
    }

    @Override
    public List<Employee> findAll(Example<Employee> employeeExample, Sort sort) {
        return empRepository.findAll(employeeExample,sort);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return empRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAll(Example<Employee> employeeExample, Pageable pageable) {
        return empRepository.findAll(employeeExample,pageable);
    }

    @Override
    public List<Employee> findAll(Specification<Employee> spec) {
        return empRepository.findAll(spec);
    }

    @Override
    public Page<Employee> findAll(Specification<Employee> spec, Pageable pageable) {
        return empRepository.findAll(spec,pageable);
    }

    @Override
    public List<Employee> findAll(Specification<Employee> spec, Sort sort) {
        return empRepository.findAll(spec,sort);
    }


}
