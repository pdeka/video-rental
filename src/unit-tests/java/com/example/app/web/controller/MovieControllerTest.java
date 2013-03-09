package com.example.app.web.controller;

import com.example.app.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    Model model;

    @Test
    public void shouldRedirectToHome() {
        MovieController controller = new MovieController(new MovieRepository());
        String viewName = controller.index(model);
        assertThat(viewName, is("home"));
    }


}
