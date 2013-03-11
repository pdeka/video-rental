package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import com.example.video.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class ViewHistoryController {

    private final TransactionRepository transactionRepository;
    private Collection<Transaction> transactions;
    private Customer customer;

    @Autowired
    public ViewHistoryController(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String display() throws Exception {
        ModelAndView modelAndView = new ModelAndView("login");
        ModelMap modelMap = modelAndView.getModelMap();
        // TODO: should we pull the customer out of the session?
        transactions = transactionRepository.transactionsBy(customer);
        modelMap.put("transactions", transactions);
        return "history";
    }

}
