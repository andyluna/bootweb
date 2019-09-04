package com.dtxytech.boot.repository.dsl;

import com.dtxytech.boot.BootAllApplication;
import com.dtxytech.boot.bean.MouseDTO;
import com.dtxytech.boot.entity.dsl.Mouse;
import com.dtxytech.boot.entity.dsl.QMouse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.dsl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 17:03
 * @DESC : DSL 动态高级查询
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootAllApplication.class})
@Transactional
@Rollback(false)
public class MouseRepositoryTest {
    private static Random r = new Random();
    private final String[] names={"有限鼠标","无线鼠标","滚球鼠标"};
    private final String[] brand={"mac","huawei","honor","lenovo","xiaomi","redmi"};

    @Autowired
    private MouseRepository mouseRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //先批量保存100条数据
    @Test
    public void saveAll(){
        List<Mouse> mouseList = new ArrayList<>();
        Mouse m = null;
        for (int i = 0; i<100;i++){
            m = new Mouse(
                    names[r.nextInt(names.length)]+i,
                           brand[r.nextInt(brand.length)],
                    r.nextFloat()*r.nextInt(1000),
                    new Date());
            mouseList.add(m);
        }
        mouseRepository.saveAll(mouseList);
        log.debug("鼠标批量保存成功:共保存{}条数据",mouseList.size());
        mouseList.forEach(System.out::println);
    }

    //1.查询一个
    @Test
    public void testFindOne(){
        QMouse mouse = QMouse.mouse;
        Mouse res = jpaQueryFactory
                        .selectFrom(mouse)
                        .where(
                                mouse.name.eq("有限鼠标61"),
                                mouse.brand.eq("lenovo")
                        )
                        .fetchOne();
        log.debug("query by dsltestFindOne 查询成功:{} ",res);
    }

    //2.查询所有 并排序
    @Test
    public void testFindAll(){
        QMouse mouse = QMouse.mouse;
        List<Mouse> resList = jpaQueryFactory
                .selectFrom(mouse)
                .orderBy(
                        mouse.name.asc(),
                        mouse.brand.desc()
                )
                .fetch();
        log.debug("query by dsl testFindAll查询成功 总数:{} ",resList.size());
        resList.forEach(System.out::println);
    }

    //3.查询所有 并排序   分页
    @Test
    public void testFindAllPage(){
        QMouse mouse = QMouse.mouse;
        QueryResults<Mouse> resList = jpaQueryFactory
                .selectFrom(mouse)
                .orderBy(
                        mouse.name.asc(),
                        mouse.brand.desc()
                )
                .offset(1)//第2页
                .limit(5)//5条
                .fetchResults();
        log.debug("query by dsl testFindAllPage查询成功 \n" +
                        "总数    : {} \n" +
                        "limit  : {} \n" +
                        "offset : {} \n" +
                        "total  : {} \n"
                ,resList.getResults().size(),
                resList.getLimit(),
                resList.getOffset(),
                resList.getTotal()
        );
        resList.getResults().forEach(System.out::println);
    }


    //4.使用between
    @Test
    public void testFindByBetween(){
        QMouse mouse = QMouse.mouse;
        List<Mouse> resList = jpaQueryFactory
                .selectFrom(mouse)
                .where(
                        mouse.id.between(20,30)
                )
                .orderBy(
                        mouse.id.asc(),
                        mouse.name.asc(),
                        mouse.brand.desc()
                )
                .fetch();
        log.debug("testFindByBetween 查询成功 总数    : {}  "
                ,resList.size()
        );
        resList.forEach(System.out::println);
    }

    //5.查询部分字段  可以自定义返回任意对象
    @Test
    public void testFindAll2(){
        QMouse mouse = QMouse.mouse;
        List<MouseDTO> resList = jpaQueryFactory
                .select(
                        mouse.id,
                        mouse.name,
                        mouse.brand
                ).from(mouse)
                .where( mouse.id.between(20,40) )
                .offset(1)
                .limit(8)
                .orderBy( mouse.id.asc(),  mouse.name.asc(), mouse.brand.desc()  )
                .fetch()
                .stream()
                .map(tuple->
                     new MouseDTO(
                             tuple.get(mouse.id),
                             tuple.get(mouse.name),
                             tuple.get(mouse.brand)
                     )
                ).collect(Collectors.toList());
        log.debug("testFindAll2  查询部分字段 成功    : {}  "  ,resList.size()  );
        resList.forEach(System.out::println);
    }


    //6.查询部分字段2  可以自定义返回任意对象
    //自带的Projections方式,不够灵活，不能转换类型，但是可以使用as转换名字
    @Test
    public void testFindAll3(){
        QMouse mouse = QMouse.mouse;
        List<MouseDTO> resList = jpaQueryFactory
                .select(
                    Projections.bean(
                        MouseDTO.class,
                        mouse.id,
                        mouse.name,
                        mouse.brand
                        )
                ).from(mouse)
                .fetch();
        log.debug("testFindAll2  查询部分字段 成功    : {}  "  ,resList.size()  );
        resList.forEach(System.out::println);
    }



    /************************************以下展示使用与SpringDataJPA整合的dsl********************************/
    //7.dsl与Springdatajpa整合查询1
    @Test
    public void testfind1(){
        QMouse mouse = QMouse.mouse;
        String name="滚球鼠标%";
        String brand="xiaomi";
        List<Mouse> mouseList = (List<Mouse>) mouseRepository.findAll(
                mouse.name.like(name)
                        .and(mouse.brand.eq(brand))
                ,mouse.id.asc()
        );

        log.debug("dsl与Springdatajpa整合查询  成功    : {}  "  ,mouseList.size()  );
        mouseList.forEach(System.out::println);
    }
    //8.所有条件动态分配
    @Test
    public void testfind2(){
        //分页参数
        Pageable pageable = PageRequest.of(1,5, Sort.by("id"));

        QMouse mouse = QMouse.mouse;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = mouse.isNotNull().or(mouse.isNull());
        //模拟查询条件
        Mouse queryParam = new Mouse();
        queryParam.setName("滚球鼠标");
        queryParam.setBrand("xiaomi");

        //执行动态条件拼装
        predicate = queryParam.getId()    == null ? predicate : ExpressionUtils.and(predicate,mouse.id.eq(queryParam.getId()));
        predicate = queryParam.getName()  == null ? predicate : ExpressionUtils.and(predicate,mouse.name.like(queryParam.getName()+"%"));
        predicate = queryParam.getBrand() == null ? predicate : ExpressionUtils.and(predicate,mouse.brand.like(queryParam.getBrand()+"%"));

        Page<Mouse> page = mouseRepository.findAll(predicate, pageable);

        log.debug("dsl与Springdatajpa整合查询2  成功    : {}  "  ,page);
        page.forEach(System.out::println);
    }












}