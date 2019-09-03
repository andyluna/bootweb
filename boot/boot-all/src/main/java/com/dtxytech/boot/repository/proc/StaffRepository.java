package com.dtxytech.boot.repository.proc;

import com.dtxytech.boot.entity.proc.Staff;
import com.dtxytech.boot.repository.base.MyBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @NAME : bootweb com.dtxytech.boot.repository.proc
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/2 九月 10:49
 * @DESC : 主要用来测试存储过程
 */
public interface StaffRepository extends MyBaseRepository<Staff, Integer> ,PorxExecuteExtends{

    /***************************以下4个方式 都是调用 proc_1_inout*************************/
    @Procedure("proc_1_inout")
    Integer exec_proc_1_inout1(Integer arg);

    //通过procedureName别名在数据库中引用名为“plus1inout”的隐式映射过程。
    @Procedure(procedureName = "proc_1_inout")
    Integer exec_proc_1_inout2(Integer arg);

    //EntityManager使用方法名称引用隐式映射的命名存储过程“Staff.proc1” 。
    @Procedure(name = "Staff.proc1")
    Integer exec_proc_1_inout3(@Param("arg") Integer arg);
    //EntityManager使用方法名称引用隐式映射的命名存储过程“Staff.proc1” 。
    @Procedure
    Integer proc1(@Param("arg") Integer arg);



    //执行存储过程   nativeQuery 一定要设置为true
    @Query(value = "{call proc_2_sayhello()}",nativeQuery = true)
    String executeProcSayHello1();







    //测试两个数求和
    @Query(value = "{call proc_3_add(:param1,:param2)}",nativeQuery = true)
    int execute3ProcAdd(@Param("param1") Integer a,@Param("param2")Integer b);



    // 测试职工表中 年龄大于 多少的总数
    @Procedure("proc_4_agetotal")
    int execute4ProcAgeGetherThan(Integer minAge);






    //测试存储过程 返回列表
    @Query(value = "{call proc_5_age_name_staff(:minAge,:nameLike )}",nativeQuery = true)
    List<Staff> proc5(@Param("minAge") Integer minAge, @Param("nameLike") String nameLike);


}
