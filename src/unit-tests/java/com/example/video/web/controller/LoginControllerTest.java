package com.example.video.web.controller;

import com.example.video.repository.CustomerRepository;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LoginControllerTest {


    private CustomerRepository customerRepository;
    private LoginController loginController;

    @Before
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        loginController = new LoginController(customerRepository);
    }

    @Test
    public void shouldReturnLoginWhenProvidedNoCustomerName() throws Exception, NonUniqueObjectSelectedException {
        ModelAndView modelAndView = loginController.get();
        assertEquals("login", modelAndView.getViewName());
    }


}
