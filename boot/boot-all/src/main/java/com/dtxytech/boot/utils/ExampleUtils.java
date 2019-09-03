package com.dtxytech.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @NAME : bootweb com.dtxytech.boot.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 20:39
 * @DESC : Example查询匹配器 工具类
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ExampleUtils {

    private static Logger logger = LoggerFactory.getLogger(ExampleUtils.class);


    public static <T> Example<T> findOneExample(T t){
        ExampleMatcher matcher = baseMatcher();
        ExampleMatcher.PropertyValueTransformer  transformer = ExampleUtils.filterEmptyString();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields){
            matcher = matcher.withTransformer(field.getName(),transformer);
        }
        return Example.of(t,matcher);
    }


    public static <T> Example<T> findAllExample1(T t){
        ExampleMatcher.PropertyValueTransformer  transformer = ExampleUtils.filterEmptyString();
        ExampleMatcher matcher = baseMatcher();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields){
            matcher = matcher.withTransformer(field.getName(),transformer);
            //matcher.withMatcher(field.getName(), ExampleMatcher.GenericPropertyMatchers.startsWith());
        }
        matcher = matcher.withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        return Example.of(t,matcher);
    }



    public static <T> Example<T> findAllExample(T t){
        ExampleMatcher.PropertyValueTransformer  transformer = ExampleUtils.filterEmptyString();
        ExampleMatcher matcher = baseMatcher();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields){
            matcher = matcher.withTransformer(field.getName(),transformer);
            if(field.getType().equals(String.class)){
                matcher = matcher.withMatcher(field.getName(), ExampleMatcher.GenericPropertyMatchers.startsWith());
            }
        }
        return Example.of(t,matcher);
    }

    public static <T> Specification<T> findAllSpecification(T t){
        return findAllSpecification(t,ExampleMatcher.StringMatcher.STARTING);
    }


    public static <T> Specification<T> findAllSpecification(T t,ExampleMatcher.StringMatcher stringMatcher,String ... ignorePaths){
        Specification spec = (Specification<T>) (root, query, cb) -> {
            Field[] fields = t.getClass().getDeclaredFields();
            List<Predicate> list = new ArrayList<>();
            for (Field field:fields){
                if(ignorePaths==null || Stream.of(ignorePaths).anyMatch(p->p.equals(field.getName()))){
                    continue;
                }
                try{
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),t.getClass());
                    Object value = pd.getReadMethod().invoke(t);
                    if(value!=null){
                        if(field.getType().equals(String.class)){
                            String va = value.toString();
                            if(StringUtils.hasText(va)){
                                switch (stringMatcher){
                                    case DEFAULT:
                                    case EXACT:
                                        list.add(cb.equal(root.get(field.getName()), StringUtils.trimWhitespace(va)));
                                        break;
                                    case CONTAINING:
                                        list.add(cb.like(root.get(field.getName()), "%" + StringUtils.trimWhitespace(va) + "%"));
                                        break;
                                    case STARTING:
                                        list.add(cb.like(root.get(field.getName()), StringUtils.trimWhitespace(va) + "%"));
                                        break;
                                    case ENDING:
                                        list.add(cb.like(root.get(field.getName()), "%" + StringUtils.trimWhitespace(va)));
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }else{
                            list.add(cb.equal(root.get(field.getName()),value));
                        }
                    }
                }catch (Exception e){
                    logger.warn("findAllSpecification赋值出错:{}",e.getMessage());
                }
            }
            return list.size()>0?cb.and(list.toArray(new Predicate[]{})):null;
        };
        return spec;
    }



    /**
     * 排除 NULL 或者空字符串
     * @return
     */
    public static ExampleMatcher.PropertyValueTransformer filterEmptyString(){
        return filterEmptyString(true);
    }


    /**
     * 排除 NULL 或者空字符串
     * @return
     */
    public static ExampleMatcher.PropertyValueTransformer filterEmptyString(boolean trim){
        return obj->{
            Object c = obj.orElse(null);
            if(c==null){
                return Optional.empty();
            }
            if(c.getClass().equals(String.class)){
                if(StringUtils.hasText(c.toString())){
                    return Optional.ofNullable(trim?c.toString().trim():c);
                }else{
                    return Optional.empty();
                }
            }else{
                return Optional.ofNullable(c);
            }
        };
    }



    public static ExampleMatcher baseMatcher(){
        return baseMatcher(ExampleMatcher.MatchMode.ALL,ExampleMatcher.NullHandler.IGNORE,ExampleMatcher.StringMatcher.DEFAULT,false);
    }

    public static ExampleMatcher startingMatcher(){
        return baseMatcher(ExampleMatcher.MatchMode.ALL,ExampleMatcher.NullHandler.IGNORE,ExampleMatcher.StringMatcher.STARTING,false);
    }

    public static ExampleMatcher baseMatcher(
            ExampleMatcher.MatchMode mode,
            ExampleMatcher.NullHandler nullHandler,
            ExampleMatcher.StringMatcher stringMatcher,
            boolean defaultIgnoreCase,
            String ... ignorePaths ){
        ExampleMatcher matcher = null;
        if(mode == ExampleMatcher.MatchMode.ALL){
            matcher = ExampleMatcher.matchingAll();
        }else{
            matcher = ExampleMatcher.matchingAny();
        }
        matcher = matcher.withNullHandler(nullHandler)
                .withStringMatcher(stringMatcher)
                .withIgnoreCase(defaultIgnoreCase);
        if(ignorePaths!=null && ignorePaths.length>0){
            matcher = matcher.withIgnorePaths(ignorePaths);
        }
        return matcher;
    }



}
