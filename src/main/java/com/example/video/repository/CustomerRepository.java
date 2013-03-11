package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import com.example.video.repository.exception.NullObjectAddedException;

import java.util.Collection;
import java.util.Set;

public interface CustomerRepository {
    void add(Customer entity) throws NullObjectAddedException;

    void add(Collection<Customer> entities) throws NullObjectAddedException;

    Set<Customer> selectAll();

    Set<Customer> selectAll(OrderComparator<Customer> comparator);

    Set<Customer> selectSatisfying(Specification<Customer> specification);

    Set<Customer> selectSatisfying(Specification<Customer> specification, OrderComparator<Customer> comparator);

    Customer selectUnique(Specification<Customer> specification) throws NonUniqueObjectSelectedException;
}