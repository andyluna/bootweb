package com.dtxytech.boot.service;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.WorkTicket;
import com.dtxytech.boot.repository.WorkTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @NAME : bootweb com.dtxytech.boot.service
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:34
 * @DESC : 测试spring-data-jpa规范  支持的 方法
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
public class WorkTicketServiceTest {

    @Autowired
    private WorkTicketService wtService;
    //1.测试保存
    @Test
    public void testSave(){
        WorkTicket wt = new WorkTicket();
        wt.setContent("测试");
        wt.setModelCode("工作票Mode");
        wt.setStatus("有效");
        wt.setMainId("10086");
        wt.setEquipKks("12345678");
        wt.setCreateDate(new Date());
        wtService.save(wt);
        log.debug("testSave = {}",wt);
    }
    //2.测试  插入100条数据
    @Test
    public void testSaveAll(){
        List<WorkTicket> list = new ArrayList<>();
        WorkTicket wt = null;
        for (int i = 0; i < 100; i++) {
            wt = new WorkTicket();
            wt.setContent("测试"+i);
            wt.setModelCode("工作票Mode"+i);
            wt.setStatus("有效"+i);
            wt.setEquipKks("12345678"+i);
            wt.setMainId("mainId"+i);
            wt.setCreateDate(new Date());
            list.add(wt);
        }

        wtService.saveAll(list);
        log.debug("批量保存成功 = {}",wt);
        list.forEach(w->log.debug("{}",w));
    }

    //3.测试规范查询
    @Test
    public void findById(){
        String id="ff8080816ce2c1ca016ce2c1ce9f0000";
        WorkTicket wt = wtService.findById(id);
        log.debug("findById = {}",wt);

    }

    //4. 统计查询
    @Test
    public void countByContentStartingWith(){
        long count = wtService.countByContentStartingWith("测试");
        log.debug("测试查询:{} ",count);
    }


    //5. findTop3ByContentStartingWith
    @Test
    public void testfindTop3ByContentStartingWith(){
        List<WorkTicket> list = wtService.findTop3ByContentStartingWith("测试");
        log.debug("testfindTop3ByContentStartingWith size={}",list.size());
        list.forEach(System.out::println);
    }


    //6. testfindFirst4ByContentStartingWith
    @Test
    public void testfindFirst4ByContentStartingWith(){
        List<WorkTicket> stream = wtService.findFirst4ByContentStartingWith("测试");
        log.debug("testfindFirst4ByContentStartingWith size={}",stream.size());
        stream.forEach(System.out::println);
    }


    //7.加了这个注解的情况下，参数为空报IllegalArgumentException异常 结果为空报 EmptyResultDataAccessException异常
    @Test
    public void findByContentAndMainId(){
        WorkTicket wt = wtService.findByContentAndMainId("测试23", "mainId23");
        log.debug("findByContentAndMainId content={}",wt );
    }

    //8.异步查询：
    @Test
    public void findByMainId() throws Exception  {
        Future<WorkTicket> wt = wtService.findByMainId("mainId24");
        log.debug("wt = {}",wt.isDone());
        log.debug("wt.get = {}",wt.get());
    }

    //9.异步查询
    @Test
    public void findOneByContent() throws Exception {
        CompletableFuture<WorkTicket> content = wtService.findOneByContent("测试25");
        log.debug("wt = {}",content.isDone());
        log.debug("wt.get = {}",content.get());
    }

    //10.异步查询
    @Test
    public void findOneByEquipKks() throws Exception {
        ListenableFuture<WorkTicket> content = wtService.findOneByEquipKks("1234567828");
        log.debug("wt = {}",content.isDone());
        log.debug("wt.get = {}",content.get());
    }

    //11. 测试标准查询
    @Test
    @Transactional
    public void findByHql1(){
        List<WorkTicket> list = wtService.findByHql1("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(System.out::println);
    }


    //12. 测试标准查询
    @Test
    @Transactional
    public void findByHql2(){
        List<WorkTicket> list = wtService.findByHql2("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(System.out::println);
    }

    //13. 测试只返回部分字段
    @Test
    @Transactional
    public void findByHql3(){
        List<Object> list = wtService.findByHql3("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("o是否是数组类型：{} ,字段1={},字段2={},字段3={}",o.getClass().isArray(),
                    ((Object[])o)[0],
                    ((Object[])o)[1],
                    ((Object[])o)[2]
                    );
        });
    }


    //14. 测试只返回部分字段
    @Test
    @Transactional
    public void findByHql4(){
        List<Object[]> list = wtService.findByHql4("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("o是否是数组类型：{} ,字段1={},字段2={},字段3={}",o.getClass().isArray(),
                    o[0],
                    o[1],
                    o[2]
            );
        });
    }


    //15. 测试只返回部分字段
    @Test
    @Transactional
    public void findByHql15(){
        List<WorkTicket> list = wtService.findByHql5("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("o = {}  ",o  );
        });
    }

    //16. 测试只返回部分字段 用MAP 接收
    @Test
    @Transactional
    public void findByHql16(){
        List<Map<String,Object>> list = wtService.findByHql16("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("class = {} , ",o.getClass() );
            o.forEach((k,v)-> log.debug("key = {},value = {} ",k,v));
        });
    }

    //17. 测试只返回部分字段 用MAP 接收
    @Test
    @Transactional
    public void findByHql17(){
        List<Map<Integer,Object>> list = wtService.findByHql17("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("class = {} , ",o.getClass() );
            o.forEach((k,v)-> log.debug("key = {},value = {} ",k,v));
        });
    }

    //18. 测试只返回部分字段 用MAP 接收
    @Test
    @Transactional
    public void findByHql18(){
        List<Map<String,Object>> list = wtService.findByHql18("测试%", "mainId%");
        log.debug("查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("class = {} , ",o.getClass() );
            o.forEach((k,v)-> log.debug("key = {},value = {} ",k,v));

        });
    }

    //19. 测试只返回部分字段 自定义接口接收 接收
    @Test
    @Transactional
    public void findByHql19(){
        List<WorkTicketRepository.WtResultIterface> list = wtService.findByContentContainsAndMainIdContains("测试", "mainId");
        log.debug("findByHql19 查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("id={},content={},mainId={}, class = {} , ",o.getId(),o.getContent(),o.getMainId(),o.getClass());
        });
    }

    //20. 测试只返回部分字段 自定义接口接收 接收
    @Test
    @Transactional
    public void findByHql20(){
        List<WorkTicketRepository.WtResultIterface2> list = wtService.findByContentContains("测试");
        log.debug("findByHql20 查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("id={},content={},mainId={}, class = {} , ",o.getId(),o.getContent(),o.getMainId(),o.getClass());
        });
    }

    //21. 测试只返回部分字段 自定义接口接收 接收
    @Test
    @Transactional
    public void findByHql21(){
        List<WorkTicketRepository.WtResultIterface3> list = wtService.findByMainIdContains("mainId");
        log.debug("findByHql21 查询成功:{} ",list.size());
        list.forEach(o->{
            log.debug("id={},content={},mainId={}, class = {} , ",o.getId(),o.getContent(),o.getMainId(),o.getClass());
        });
    }


}