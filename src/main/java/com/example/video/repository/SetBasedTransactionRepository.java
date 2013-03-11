package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import com.example.video.domain.specification.TransactionsForCustomerSpecification;
import com.example.video.domain.specification.TransactionsOrderedByDateTimeComparator;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

@Repository
public class SetBasedTransactionRepository extends SetBasedRepository<Transaction> implements TransactionRepository {

    public SetBasedTransactionRepository() {
        super();
    }

    @Override
    public Set<Transaction> selectAll(OrderComparator<Transaction> comparator) {
        return selectAll((Comparator<Transaction>) comparator);
    }

    @Override
    public Set<Transaction> selectSatisfying(final Specification<Transaction> specification,
                                             final OrderComparator<Transaction> comparator) {
        return selectSatisfying(specification, (Comparator<Transaction>) comparator);
    }

    @Override
    public Collection<Transaction> transactionsBy(Customer customer) {
        return selectSatisfying(
                new TransactionsForCustomerSpecification(customer),
                new TransactionsOrderedByDateTimeComparator());
    }
}
