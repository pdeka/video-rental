package com.example.video.web.controller;

import com.example.video.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ViewHomeControllerTest {

    @Mock
    MovieRepository movieRepository;

    @Test
    public void shouldAddMoviesToTheModelMap() throws Exception {
        ViewHomeController controller = new ViewHomeController(movieRepository);
        ModelAndView modelAndView = controller.get();
        assertThat(modelAndView.getModelMap(), hasKey("movies"));
    }

}
