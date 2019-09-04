package com.dtxytech.boot.repository.embbed;

import com.dtxytech.boot.entity.embbed.City;
import com.dtxytech.boot.entity.embbed.CitySymbol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:56
 * @DESC : City 接口测试
 */
public interface CityRepository extends JpaRepository<City,Integer> {

    //1.根据城市名字查询
    City findCityByName(String cityName);

    //2. 多层路径解析 spring 会解析成
    // zc.zipName去查询   支持多层嵌入的写法
    // 为什么会这样 因为没有歧义
    City findByZcZipName(String zipName);

    //3. 演示有歧义的方法
    //  这个方法 到底是用来根据直接属性 zcZipCode还是根据 zc类里面的 zipCode属性来查询
    // 结论：优先根据直接属性查询
    City findByZcZipCode(String zcZipCode);


    //4.spring官方推荐方式 解决Embedded方式下属性歧义 是使用下划线
    // 结论：如果含有下滑线 spring会优先理解成 这是一个 嵌入式类 根据嵌入类里的属性来查询
    //      spring 官方不介意 实体类 的属性含有下划线
    City findByZc_ZipCode(String zipCode);




    //5.之前使用内嵌属性查询
    City findByCs(CitySymbol cs);





}
