package com.dtxytech.boot.service;

import com.dtxytech.boot.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @NAME : bootweb com.dtxytech.boot.service
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 10:47
 * @DESC : 测试JPA原生的 方法 不添加任何额外的方法
 */
public interface EmployeeService {
    /**********************  1.增加 ************************************************/
    //1.1 保存单个对象 保存之后自动将ID值赋上
    Employee saveEmployee(Employee employee);

    //1.2 保存集合对象  保存之后返回List
    List<Employee> saveAllEmployee(Iterable<Employee> employees);

    //1.3 比save方法多一个 flush() 操作
    Employee saveAndFlushEmployee(Employee employee);


    /**********************  2.删除 ************************************************/
    //2.1 先根据Id查询   再删除对象 如果数据不存在：以前的版本会报错 v2.1.7.RELEASE之后不报错了
    void delete(Employee employee);

    //2.2 底层会执行 delete  from TABLE_NAME where id=? or id=? or id =?
    void deleteInBatch(Iterable<Employee> employees);

    //2.3 删除全部数据  先执行select 查询出所有数据 然后再执行 delete from TABLE_NAME where id=? 效率低！！
    void deleteAll();

    //2.4 删除批量数据  底层根据迭代器 一条一条的查询出所有对象 然后再调用  delete(employee) 方法  效率更低！！！
    void deleteAll(Iterable<Employee> employees);

    //2.5 先根据id去查询数据  如果数据不存在则报异常EmptyResultDataAccessException  如果存在再调用delete(employee) 此方法慎用！
    void deleteById(Integer id);

    /**********************  3.修改 ************************************************/

    //没有save方法一样  如果 实体类的主键ID为空则执行保存 如果不为空则执行修改


    /**********************  4.查询 ************************************************/
    //4.1 根据ID查询  返回的是java.util.Optional 类
    Optional<Employee> findById(Integer id);

    //4.2 根据Example对象查询  返回的是java.util.Optional 类  如何组装查询条件在 Test类中已经示例
    Optional<Employee> findOne(Example<Employee> exp);

    //4.3 根据Specification对象查询  返回的是java.util.Optional 类 如何组装查询条件在 Test类中已经示例
    Optional<Employee> findOne(Specification<Employee> spec);

    //4.4 类似与Hibernate Load方法  延迟加载：如果事务里面没有使用Employee而事务关闭之后再使用则会报懒加载异常
    Employee           getOne(Integer id);

    //4.5 根据多个ID 批量查询  底层采用 id in(?,?,?)方式 所以 ids的长度最好不要超过100 不然有的数据库不支持
    List<Employee> findAllById(Iterable<Integer> ids);



    /**********************  5.分页查询、分组查询、排序、部分字段查询**********/
    //5.1 查询总数
    long count();

    //5.2 查询总数 通过Example
    long count(Example<Employee> exp);

    //5.3 查询总数 通过Specification
    long count(Specification<Employee> spec);

    //5.4查询所有 不带参数
    List<Employee> findAll();

    //5.5查询所有 带上排序
    List<Employee> findAll(Sort sort);

    //5.6查询所有 带Example参数
    List<Employee> findAll(Example<Employee> employeeExample);

    //5.7查询所有 带Example参数和排序
    List<Employee> findAll(Example<Employee> employeeExample,Sort sort);

    //5.8查询所有 分页展示 第一页参数从 0 开始
    Page<Employee> findAll(Pageable pageable);

    //5.9查询所有 带Example参数和分页
    Page<Employee> findAll(Example<Employee> employeeExample,Pageable pageable);

    //5.10查询所有 带Specification参数和分页
    List<Employee> findAll(Specification<Employee> spec );

    //5.11查询所有 带Specification参数和分页 第一页参数从 0 开始
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    //5.12查询所有 带Specification参数和分页
    List<Employee> findAll(Specification<Employee> spec,Sort sort);




}
