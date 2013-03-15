package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import com.example.video.repository.exception.NullObjectAddedException;

import java.util.Collection;
import java.util.Set;

public interface RentalRepository {
    void add(Rental entity) throws NullObjectAddedException;

    void add(Collection<Rental> entities) throws NullObjectAddedException;

    Set<Rental> selectAll();

    Set<Rental> selectAll(OrderComparator<Rental> comparator);

    Set<Rental> selectSatisfying(Specification<Rental> specification);

    Set<Rental> selectSatisfying(Specification<Rental> specification, OrderComparator<Rental> comparator);

    Rental selectUnique(Specification<Rental> specification) throws NonUniqueObjectSelectedException;

    Set<Rental> currentRentalsFor(Customer customer);

    void removeAll(Collection<Rental> returnRentals);
}