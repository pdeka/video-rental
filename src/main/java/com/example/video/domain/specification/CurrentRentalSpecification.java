package com.example.video.domain.specification;

import com.example.video.domain.Rental;
import org.hibernate.Criteria;

public class CurrentRentalSpecification implements Specification<Rental> {

    @Override
    public boolean isSatisfiedBy(final Rental rental) {
        return !rental.getPeriod().getEndDate().isBeforeNow();
    }

    @Override
    public void populateCriteria(final Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
