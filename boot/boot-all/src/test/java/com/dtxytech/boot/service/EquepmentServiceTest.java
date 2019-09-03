package com.dtxytech.boot.service;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.event.Equepment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.event
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 12:47
 * @DESC : 测试event
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
public class EquepmentServiceTest {

    @Autowired
    private EquepmentService equepmentService;
    //1.1 保存的时候会触发事件 这个方法 我特意没有设置 创建时间 和更新时间
    @Test
    public void testSave(){
        Equepment e = new Equepment();
        e.setName("电饭煲");
        e.setKks("123456789");
        e.setAttachment("测试数据".getBytes());//这里本应该放上文件二进制
        e.setDescription("描述一下");
        equepmentService.save(e);
        log.debug("保存成功:{}",e);
    }
    //1.2 保存全部也会触发事件  这个方法 我特意没有设置 创建时间 和更新时间
    @Test
    public void testSaveAll(){
        List<Equepment> list = new ArrayList<>();
        Equepment e = null;
        for (int i = 0; i <100 ; i++) {
            e = new Equepment();
            e.setName("发电机"+i);
            e.setKks("123456789"+i);
            e.setAttachment(("测试数据"+i).getBytes());//这里本应该放上文件二进制
            e.setDescription("描述一下"+i);
            list.add(e);
        }
        equepmentService.saveAll(list);
        log.debug("批量保存成功:{}",list.size());
        list.forEach(System.out::println);
    }


    //2.1根据ID查询
    @Test
    public void getById() {
        Equepment equepment = equepmentService.getById(13);
        log.debug("getById 查询成功:{}",equepment);
    }
    //2.2根据名字查询
    @Test
    public void getByName() {
        Equepment equepment = equepmentService.getByName("发电机14");
        log.debug("getByName 查询成功:{}",equepment);
    }
    //2.2根据名字 且通过hql查询查询
    @Test
    public void getByHql() {
        Equepment equepment = equepmentService.getByHql("发电机15");
        log.debug("getByHql 查询成功:{}",equepment);
    }

    @Test
    public void update() {
        Equepment equepment = equepmentService.getByHql("发电机15");
        log.debug("getByHql 查询成功:{}",equepment);
        equepment.setName(equepment.getName()+"修改之后的哦");
        equepmentService.update(equepment);
        log.debug("update修改成功");
    }

    @Test
    public void updateByHql() {
        equepmentService.updateByHql("通过HQL修改的名字",17);
        log.debug("updateByHql修改成功");
    }

    @Test
    public void deleteById() {
        equepmentService.deleteById(80);
        log.debug("deleteById删除成功");
    }

    @Test
    public void deleteByHql() {
        equepmentService.deleteByHql(81);
        log.debug("deleteByHql删除成功");
    }


}