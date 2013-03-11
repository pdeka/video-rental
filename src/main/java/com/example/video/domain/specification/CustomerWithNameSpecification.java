package com.example.video.domain.specification;

import com.example.video.domain.Customer;
import org.hibernate.Criteria;

public class CustomerWithNameSpecification implements Specification<Customer> {

    private final String customerName;

    public CustomerWithNameSpecification(final String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean isSatisfiedBy(final Customer customer) {
        return customer.getName().equals(customerName);
    }

    @Override
    public void populateCriteria(final Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
