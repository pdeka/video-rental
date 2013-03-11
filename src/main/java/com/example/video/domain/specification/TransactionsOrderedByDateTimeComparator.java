package com.example.video.domain.specification;

import com.example.video.domain.Transaction;
import org.hibernate.Criteria;

public class TransactionsOrderedByDateTimeComparator implements OrderComparator<Transaction> {

    @Override
    public int compare(final Transaction transaction1, final Transaction transaction2) {
        return transaction1.getDateTime().compareTo(transaction2.getDateTime());
    }

    @Override
    public void populateCriteria(final Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
