package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.specification.CustomerWithNameSpecification;
import com.example.video.domain.specification.CustomersOrderedByNameComparator;
import com.example.video.repository.CustomerRepository;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CustomerRepository customerRepository;
    private Customer loggedInCustomer;

    @Autowired
    public LoginController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Set<Customer> getCustomers() {
        return customerRepository.selectAll(new CustomersOrderedByNameComparator());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView get() throws Exception, NonUniqueObjectSelectedException {
        logger.info("Executing GET on Login Controller");
        ModelAndView modelAndView = new ModelAndView("login");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put("customers", getCustomers());
        return modelAndView;
    }

    @RequestMapping(value = "/login/process", method = RequestMethod.POST)
    public String post(String customerName) throws Exception, NonUniqueObjectSelectedException {
        logger.info("Executing POST on Login Controller");
        loggedInCustomer = customerRepository.selectUnique(new CustomerWithNameSpecification(customerName));
        logger.info("Going to home for " + customerName);
        return "redirect:/home";
    }
}
