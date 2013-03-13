package com.example.video.web.controller;

import com.example.video.domain.Movie;
import com.example.video.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class ViewHomeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MovieRepository movieRepository;

    @Autowired
    public ViewHomeController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Set<Movie> getMovies() {
        return movieRepository.selectAll();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView get() throws Exception {
        logger.info("Executing GET on Home Controller");
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.getModelMap().put("movies", getMovies());
        return modelAndView;
    }

}
