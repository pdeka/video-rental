package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import com.example.video.repository.RentalRepository;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.sun.istack.internal.Nullable;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Client
 * Date: 15/03/13
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReturnMoviesController {
    private RentalRepository rentalRepository;
    public ReturnMoviesController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void put(final String[] movieNames, HttpSession httpSession) {
        final List<String> movieNameList = Arrays.asList(movieNames);
        Customer customer = (Customer) httpSession.getAttribute("customer");
        Set<Rental> rentals = rentalRepository.currentRentalsFor(customer);
        Collection<Rental> returnedRentals = Collections2.filter(rentals, new Predicate<Rental>() {
            @Override
            public boolean apply(@Nullable Rental rental) {
                return movieNameList.contains(rental.getMovie().getTitle());
            }
        });

        rentalRepository.removeAll(returnedRentals);
    }
}
