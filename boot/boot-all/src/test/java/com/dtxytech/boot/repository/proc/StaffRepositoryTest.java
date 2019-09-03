package com.dtxytech.boot.repository.proc;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.proc.Staff;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * @NAME : bootweb com.dtxytech.boot.repository.proc
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/2 九月 10:50
 * @DESC :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
@Transactional
@Rollback(false)
public class StaffRepositoryTest {
    private static Random r = new Random();
    @Autowired
    private StaffRepository staffRepository;
    //首先创建100条测试数据
    @Test
    public void testAllAll(){
        List<Staff> staffs = new ArrayList<>();
        Staff s = null;
        for (int i = 0; i < 100; i++) {
             s = new Staff();
             s.setName("张三丰"+i);
             s.setPosition("资深码农"+i);
             s.setAge(r.nextInt(100));
             s.setCreateDate(new Date());
             staffs.add(s);
        }
        staffRepository.saveAll(staffs);
    }

    //1. 以下4个方式都是 测试存储过程 执行存储过程proc_1_inout
    @Test
    public void testexec_proc_1_inout_1(){
        int in = 1;
        Integer res = staffRepository.exec_proc_1_inout1(in);
        log.debug("执行存储过程proc_1_inout 入参:{} 结果:{} ",in,res);
    }
    @Test
    public void testexec_proc_1_inout_2(){
        int in = 2;
        Integer res = staffRepository.exec_proc_1_inout2(in);
        log.debug("执行存储过程testexec_proc_1_inout_2 入参:{} 结果:{} ",in,res);
    }
    @Test
    public void testexec_proc_1_inou_3(){
        int in = 3;
        Integer res = staffRepository.exec_proc_1_inout3(in);
        log.debug("执行存储过程testexec_proc_1_inou_3 入参:{} 结果:{} ",in,res);
    }
    @Test
    public void testexec_proc1(){
        int in = 4;
        Integer res = staffRepository.proc1(in);
        log.debug("执行存储过程testexec_proc_1_inout_4 入参:{} 结果:{} ",in,res);
    }



    //2.执行存储过程  无入参 有返回 结果
    @Test
    public void testSayHello1(){
        String res = staffRepository.executeProcSayHello1();
        log.debug("testSayHello1返回结果： {} " ,res);
    }
    @Test
    public void testSayHello2(){
        String res = staffRepository.executeProcSayHello2();
        log.debug("testSayHello2返回结果： {} " ,res);
    }

    @Test
    public void testSayHello3(){
        String res = staffRepository.executeProcSayHello3();
        log.debug("testSayHello3返回结果：{} " ,res);
    }

    @Test
    public void testSayHello4(){
        String res = staffRepository.executeProcSayHello4();
        log.debug("testSayHello4返回结果： {} " ,res);
    }




    //3. 测试两个数求和 有入参有返回 结果
    @Test
    public void execute3ProcAdd(){
        int a = 3;
        int b = 2;
        int s = staffRepository.execute3ProcAdd(a, b);
        log.debug("测试结果 {} + {} = {}",a,b,s);
    }


    //4.测试年龄大于多少的 职工综合
    @Test
    public void execute4ProcAgeGetherThan(){
        int minAge = 56;
        int s = staffRepository.execute4ProcAgeGetherThan(minAge);
        log.debug("年龄大于 {}职工总数是 {} = {}",minAge ,s);
    }


    //5.测试返回列表
    @Test
    public void testProc5(){
        int minAge=61;
        String nameLike="张三丰";
        List<Staff> proc5 = staffRepository.proc5(minAge, nameLike);
        log.debug("testProc5测试存储过程返回列表成功:{} ",proc5.size());
        proc5.forEach(s->log.debug("{}",s));
    }











}