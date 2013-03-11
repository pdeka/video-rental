package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import com.example.video.repository.exception.NullObjectAddedException;

import java.util.Collection;
import java.util.Set;

public interface TransactionRepository {
    void add(Transaction entity) throws NullObjectAddedException;

    void add(Collection<Transaction> entities) throws NullObjectAddedException;

    Set<Transaction> selectAll();

    Set<Transaction> selectAll(OrderComparator<Transaction> comparator);

    Set<Transaction> selectSatisfying(Specification<Transaction> specification);

    Set<Transaction> selectSatisfying(Specification<Transaction> specification, OrderComparator<Transaction> comparator);

    Transaction selectUnique(Specification<Transaction> specification) throws NonUniqueObjectSelectedException;

    Collection<Transaction> transactionsBy(Customer customer);
}
