package com.example.video.web.controller;

import com.example.video.domain.Customer;
import com.example.video.domain.Transaction;
import com.example.video.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class ViewHistoryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    public ModelAndView display(HttpSession session) throws Exception {

        logger.info("GET Rent movies Controller");

        Customer customer = (Customer) session.getAttribute("customer");
        transactions = transactionRepository.transactionsBy(customer);

        ModelAndView modelAndView = new ModelAndView("history");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.put("transactions", transactions);
        return modelAndView;
    }

}
