package com.example.video.repository;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import com.example.video.domain.specification.*;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Set;

@Repository
public class SetBasedRentalRepository extends SetBasedRepository<Rental> implements RentalRepository {

    public SetBasedRentalRepository() {
        super();
    }

    @Override
    public Set<Rental> selectAll(OrderComparator<Rental> comparator) {
        return selectAll((Comparator<Rental>) comparator);
    }

    @Override
    public Set<Rental> selectSatisfying(final Specification<Rental> specification,
                                        final OrderComparator<Rental> comparator) {
        return selectSatisfying(specification, (Comparator<Rental>) comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Rental> currentRentalsFor(final Customer customer) {
        return selectSatisfying(new AndSpecification<Rental>(new RentalForCustomerSpecification(customer),
                new CurrentRentalSpecification()));
    }
}
