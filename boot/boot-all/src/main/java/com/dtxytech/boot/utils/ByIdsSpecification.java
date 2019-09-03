package com.dtxytech.boot.utils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @NAME : bootweb com.dtxytech.boot.utils
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 20:42
 * @DESC :
 */
public class ByIdsSpecification<T> implements Specification<T> {

    private final JpaEntityInformation<T, ?> entityInformation;

    @Nullable
    public ParameterExpression<Iterable> parameter;

    public ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
        this.entityInformation = entityInformation;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Path<?> path = root.get(entityInformation.getIdAttribute());
        parameter = cb.parameter(Iterable.class);
        return path.in(parameter);
    }
}
