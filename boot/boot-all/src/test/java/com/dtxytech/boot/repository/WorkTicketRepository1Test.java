package com.dtxytech.boot.repository;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.WorkTicket;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 21:38
 * @DESC :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
@Transactional
@Rollback(false)
public class WorkTicketRepository1Test {
    @Autowired
    private WorkTicketRepository1 wtRepository1;
    //1.根据内容查询
    @Test
    public void findAllByContent(){
        List<WorkTicket> list = wtRepository1.findAllByContent("测试%");
        log.debug("查询成功 size = {}",list.size());
        list.forEach(System.out::println);
    }

    //2 测试spel
    @Test
    public void findAllByContentWithSpel(){
        List<WorkTicket> list = wtRepository1.findAllByContentWithSpel("测试");
        log.debug("查询成功 size = {}",list.size());
        list.forEach(System.out::println);
    }
    //3. 测试spel2
    @Test
    public void findAllByContentWithSpel1(){
        List<WorkTicket> list = wtRepository1.findAllByContentWithSpel1("测试");
        log.debug("查询成功 size = {}",list.size());
        list.forEach(System.out::println);
    }

    //4.修改
    @Test
    public void updateWtMainIdByContent(){
        List<WorkTicket> all = wtRepository1.findAllByContent("测试38");
        log.debug("修改前 数据如下");
        all.forEach(System.out::println);

        int i = wtRepository1.updateWtMainIdByContent("修改修改后mainId", "测试38");
        log.debug("修改成功:{}",i);

        all = wtRepository1.findAllByContent("测试38");
        log.debug("修改后 数据如下");
        all.forEach(System.out::println);
    }




    //5.测试删除删除操作
    @Test
    public void deleteByContent(){
        int i = wtRepository1.deleteByContent("测试42");
        log.debug("删除数据成功 共删除{}条",i);
    }

    //6.测试删除删除操作
    @Test
    public void deleteByContent2(){
        int i = wtRepository1.deleteByContent2("测试43");
        log.debug("删除数据成功 共删除{}条",i);
    }

    //7. 测试应用查询提示
    @Test
    public void testfindByContentStartingWith(){
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<WorkTicket> page = wtRepository1.findByContentStartingWith("测试",pageRequest);
        log.debug("page:{}",page);
        page.forEach(System.out::println);
    }

    //测试自定义分页查询
    @Test
    public void testfindByContentPage(){
        PageRequest pageRequest = PageRequest.of(1, 5);
        Page<WorkTicket> page =  wtRepository1.findByContentPage("测试%",pageRequest);
        log.debug("page:{}",page);
        page.forEach(System.out::println);
    }
}