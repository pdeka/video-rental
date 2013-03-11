package com.example.video.domain.specification;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import org.hibernate.Criteria;

public class RentalForCustomerSpecification implements Specification<Rental> {

    private final Customer customer;

    public RentalForCustomerSpecification(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean isSatisfiedBy(final Rental rental) {
        return customer.equals(rental.getCustomer());
    }

    @Override
    public void populateCriteria(final Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
