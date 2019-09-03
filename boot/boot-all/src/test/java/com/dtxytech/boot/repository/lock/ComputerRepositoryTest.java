package com.dtxytech.boot.repository.lock;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.lock.Computer;
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
 * @NAME : bootweb com.dtxytech.boot.repository.lock
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 11:50
 * @DESC : 测试悲观锁 乐观锁
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
@Transactional
@Rollback(false)
public class ComputerRepositoryTest {
    private static Random r = new Random();
    private final String[] cpus={"intel-i3","intel-i5","intel-i7","intel-i9"};
    private final String[] memory={"2G","4G","8G","16G","32G"};
    private final String[] brand={"mac","HUAWEI","lenovo","xiaomi"};
    @Autowired
    private ComputerRepository computerRepository;


    //首先创建100条测试数据
    @Test
    public void testAllAll(){
        List<Computer> computers = new ArrayList<>();
        Computer s = null;
        for (int i = 0; i < 100; i++) {
           s = new Computer(
                   cpus[r.nextInt(cpus.length)],
                   memory[r.nextInt(memory.length)],
                   brand[r.nextInt(brand.length)],
                   new Date());
            computers.add(s);
        }
        computerRepository.saveAll(computers);
    }

    @Test
    public void testLocked(){
        String cpu = cpus[r.nextInt(cpus.length)];
        List<Computer> cs = computerRepository.findByCpu(cpu);
        log.debug("根据cpu={} 查询 总有{}条数据",cpu,cs.size());
        cs.forEach(System.out::println);
    }











}