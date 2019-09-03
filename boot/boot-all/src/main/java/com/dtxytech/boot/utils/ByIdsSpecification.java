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
@SuppressWarnings({"rawtypes","unchecked"})
public class ByIdsSpecification<T> implements Specification<T> {
    private static final long serialVersionUID = -8298854426547208032L;
    private final JpaEntityInformation<T, ?> entityInformation;

    @Nullable
    public ParameterExpression<Iterable> parameter;

    public ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
        this.entityInformation = entityInformation;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Path<?> path = root.get(entityInformation.getIdAttribute());
        parameter = cb.parameter(Iterable.class);
        return path.in(parameter);
    }
}
