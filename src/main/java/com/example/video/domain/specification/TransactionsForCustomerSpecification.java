package com.example.video.domain.specification;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import org.hibernate.Criteria;

public class TransactionsForCustomerSpecification implements Specification<Transaction> {
    private final Customer customer;

    public TransactionsForCustomerSpecification(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean isSatisfiedBy(final Transaction transaction) {
        return customer.equals(transaction.getCustomer());
    }

    @Override
    public void populateCriteria(Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
