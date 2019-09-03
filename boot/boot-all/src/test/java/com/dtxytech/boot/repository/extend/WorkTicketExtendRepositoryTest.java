package com.dtxytech.boot.repository.extend;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.WorkTicket;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.extend
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 19:39
 * @DESC : 测试 spring-data-jpa 自定义方法扩展
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
public class WorkTicketExtendRepositoryTest {
    @Autowired
    private WorkTicketExtendRepository wteRepository;

    //1. 自定义查询
    @Test
    public void findByAnyThing(){
        WorkTicket test = wteRepository.findByAnyThing("anyThing");
        log.debug("test = {}",test);
    }

    //2. 根据内容 自定义查询 通过EntityManager
    @Test
    public void findByContentZdy(){
        WorkTicket test = wteRepository.findByContentZdy("测试38");
        log.debug("test = {}",test);
    }

    //3. 通过Session查询
    @Test
    @Transactional
    public void findByIdByHibernateSession(){
        WorkTicket wt = wteRepository.findByIdByHibernateSession("ff8080816ce7540c016ce75412580026");
        log.debug("wt = {}",wt);
    }




}