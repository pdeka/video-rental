package com.example.video.domain.specification;

import com.example.video.domain.Customer;
import org.hibernate.Criteria;

public class CustomersOrderedByNameComparator implements OrderComparator<Customer> {

    @Override
    public int compare(final Customer customer1, final Customer customer2) {
        return (customer1 == customer2) ? 0 : customer1.getName().compareTo(customer2.getName());
    }

    @Override
    public void populateCriteria(Criteria arg0) {
        throw new UnsupportedOperationException("not implemented");
    }

}
