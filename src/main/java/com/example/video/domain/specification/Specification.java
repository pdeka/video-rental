package com.example.video.domain.specification;

import org.hibernate.Criteria;

public interface Specification<T> {

    boolean isSatisfiedBy(T object);

    void populateCriteria(Criteria criteria);

}
