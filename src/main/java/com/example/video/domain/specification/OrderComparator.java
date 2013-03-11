package com.example.video.domain.specification;

import org.hibernate.Criteria;

import java.util.Comparator;

public interface OrderComparator<T> extends Comparator<T> {
    void populateCriteria(Criteria criteria);
}