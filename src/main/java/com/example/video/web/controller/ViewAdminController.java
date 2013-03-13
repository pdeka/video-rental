package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class ViewAdminController {

    private final CustomerRepository customerRepository;

    @Autowired
    public ViewAdminController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Set<Customer> getUsers() {
        return customerRepository.selectAll();
    }

    public String get() throws Exception {
        return "viewName";
    }

}
