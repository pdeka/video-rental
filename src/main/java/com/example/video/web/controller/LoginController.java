package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.specification.CustomerWithNameSpecification;
import com.example.video.domain.specification.CustomersOrderedByNameComparator;
import com.example.video.repository.CustomerRepository;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class LoginController {

    private final CustomerRepository customerRepository;
    private String customerName;
    private Customer loggedInCustomer;

    @Autowired
    public LoginController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCustomerName(final String customer) {
        this.customerName = customer;
    }

    public Set<Customer> getCustomers() {
        return customerRepository.selectAll(new CustomersOrderedByNameComparator());
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String get() throws Exception, NonUniqueObjectSelectedException {
        if (customerName == null) {
            return "login";
        }
        loggedInCustomer = customerRepository.selectUnique(new CustomerWithNameSpecification(customerName));
        if (loggedInCustomer == null) {
            return "login";
        }
        return "movies";
    }
}
