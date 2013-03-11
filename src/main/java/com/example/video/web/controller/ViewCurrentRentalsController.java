package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import com.example.video.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ViewCurrentRentalsController {

    private final RentalRepository rentalRepository;
    private Collection<Rental> rentals;
    private Customer customer;

    @Autowired
    public ViewCurrentRentalsController(final RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Collection<Rental> getRentals() {
        return rentals;
    }

    public String execute() throws Exception {
        rentals = rentalRepository.currentRentalsFor(customer);
        return "viewName";
    }

}
