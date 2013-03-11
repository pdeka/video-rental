package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import com.example.video.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class ViewHistoryController {

    private final TransactionRepository transactionRepository;
    private Collection<Transaction> transactions;
    private Customer customer;

    public ViewHistoryController(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String display() throws Exception {
        transactions = transactionRepository.transactionsBy(customer);
        return "viewName";
    }

}
