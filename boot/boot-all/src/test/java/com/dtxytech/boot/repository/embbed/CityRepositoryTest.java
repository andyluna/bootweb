package com.dtxytech.boot.repository.embbed;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.entity.embbed.City;
import com.dtxytech.boot.entity.embbed.CitySymbol;
import com.dtxytech.boot.entity.embbed.CityZipCode;
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


/**
 * @NAME : bootweb com.dtxytech.boot.repository.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:58
 * @DESC : 内嵌 继承
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;
    //0.测试之前先批量保存 100条数据
    @Test
    @Transactional//因为cityRepository默认实现类 SimpleJpaRepository只支持readOny=true事务 所以这个要加上注解
    @Rollback(false)//测试环境下加上这个注解 不然 全部保存成功之后 因为是测试环境🈶马上回滚了
    public void testSave(){
        List<City> citys= new ArrayList<>();
        City c = null;
        for (int i = 0; i <100 ; i++) {
            c = new City();
            c.setName("长沙"+i);
            c.setTotalPeople(100000000L+i);
            c.setCreateDate(new Date());
            c.setCreateUserId("userId"+i);
            c.setCreateUserName("userName"+i);
            c.setZcZipCode("测试一下特殊字段"+i);
            c.setCs(new CitySymbol("毛泽东雕像"+i,"臭豆腐"+i));
            c.setZc(new CityZipCode("430701"+i,"五一大道"+i));
            citys.add(c);
        }

        List<City> res = cityRepository.saveAll(citys);
        log.debug("批量保存成功成功:{}",res.size());
        res.forEach(System.out::println);
    }



    //1.根据城市名字查询
    //因为这是一个查询方法 所以不需要加@Transitional注解。默认实现类已经加了
    @Test
    public void findByCityName(){
        City city = cityRepository.findCityByName("长沙73");
        log.debug("{}",city);
    }

    //2. 多层路径解析 spring 会解析成 zc.zipName去查询   支持多层嵌入的写法
    // 为什么会这样 因为没有歧义
    @Test
    public void  findByZcZipName(){
        City city = cityRepository.findByZcZipName("五一大道70");
        log.debug("findByZcZipName: {}",city);
    }

    //3. 演示有歧义的方法
    //  这个方法 到底是用来根据直接属性 zcZipCode还是根据
    //  zc类里面的 zipCode属性来查询
    // 结论：优先根据直接属性查询
    @Test
    public void  findByZcZipCode(){
        City city = cityRepository.findByZcZipCode("测试一下特殊字段73");
        log.debug("findByZcZipCode: {}",city);
    }


    //4.spring官方推荐方式 解决Embedded方式下属性歧义 是使用下划线
    // 结论：如果含有下滑线 spring会优先理解成 这是一个 嵌入式类 根据嵌入类里的属性来查询
    @Test
    public void  findByZc_ZipCode(){
        City city = cityRepository.findByZc_ZipCode("43070170");
        log.debug("findByZc_ZipCode: {}",city);
    }


    //5.之前使用内嵌属性查询
    @Test
    public void findByCs(){
        CitySymbol cs = new CitySymbol("毛泽东雕像68","臭豆腐68");
        City city = cityRepository.findByCs(cs);
        log.debug("findByZc_ZipCode: {}",city);
    }

}