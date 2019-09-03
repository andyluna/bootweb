package com.dtxytech.boot.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @NAME : bootweb com.dtxytech.boot.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 20:43
 * @DESC :
 */
public class ExampleSpecification <T> implements Specification<T> {
    private static final long serialVersionUID = 350168992673426126L;
    private final Example<T> example;

    public ExampleSpecification(Example<T> example) {

        Assert.notNull(example, "Example must not be null!");
        this.example = example;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return QueryByExamplePredicateBuilder.getPredicate(root, cb, example);
    }
}
