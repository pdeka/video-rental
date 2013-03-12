package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Rental;
import com.example.video.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public ModelAndView execute(HttpSession session) throws Exception {
        Customer customer = (Customer) session.getAttribute("customer");
        rentals = rentalRepository.currentRentalsFor(customer);
        ModelAndView modelAndView = new ModelAndView("rentals");
        modelAndView.getModelMap().put("rentals", rentals);
        return modelAndView;
    }

}
