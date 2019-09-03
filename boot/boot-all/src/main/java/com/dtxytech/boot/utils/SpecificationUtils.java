package com.dtxytech.boot.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @NAME : bootweb com.dtxytech.boot.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 20:41
 * @DESC :
 */
public class SpecificationUtils extends ExampleUtils{


    public static <T> Specification getExactSpec(
            Class<T> domainClass, String property, Object value){
        Assert.notNull(domainClass, "domainClass must not be null");
        return (Specification<T>) (root, query, cb) -> cb.equal(root.get(property), value);
    }

    public static <T> Specification getExactSpec(
            Class<T> domainClass, Map<String,Object> param){
        Assert.notNull(domainClass, "domainClass must not be null");
        if (param == null || param.size() == 0) {
            return null;
        }
        return (Specification<T>) (root, query, cb) ->{
            Set<String> keySet = param.keySet();
            List<Predicate> list = new ArrayList<>();
            for (Map.Entry<String,Object> entry : param.entrySet()) {
                list.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
            }
            return cb.and(list.toArray(new Predicate[]{}));
        };
    }

    public static <T> Specification getExactSpec(Object o){
        return o==null?null:new ExampleSpecification(Example.of(o));
    }







    public static <T> Example<T> findDataGridExample(T t){
        ExampleMatcher.PropertyValueTransformer  transformer = null;
        ExampleMatcher matcher = startingMatcher();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields){
            matcher = matcher.withTransformer(field.getName(),transformer);
            if(field.getType().equals(String.class)){
                matcher =  matcher.withMatcher(field.getName(), ExampleMatcher.GenericPropertyMatchers.startsWith());

            }
        }
        return Example.of(t,matcher);
    }




    public static <T> Specification<T> findDataGridSpec(T t){
        return findAllSpecification(t);
    }




}
