package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

@Repository
public class SetBasedCustomerRepository extends SetBasedRepository<Customer> implements CustomerRepository {

    public SetBasedCustomerRepository() {
        }

    public SetBasedCustomerRepository(final Collection<Customer> entities) {
        super(entities);
    }

    @Override
    public Set<Customer> selectAll(OrderComparator<Customer> comparator) {
        return selectAll((Comparator<Customer>) comparator);
    }

    @Override
    public Set<Customer> selectSatisfying(Specification<Customer> specification, OrderComparator<Customer> comparator) {
        return selectSatisfying(specification, (Comparator<Customer>) comparator);
    }

}
