package com.example.video.web.controller;

import com.example.video.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.ui.Model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    Model model;

    @Test
    public void shouldRedirectToHome() {
        MovieController controller = new MovieController(new MovieRepository(Mockito.mock(HibernateTemplate.class)));
        String viewName = controller.index(model);
        assertThat(viewName, is("home"));
    }


}
