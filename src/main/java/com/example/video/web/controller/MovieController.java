package com.example.video.web.controller;

import com.example.video.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MovieRepository movieRepository;

    @Autowired
    public MovieController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("logging the movies controller");
        model.addAttribute("movies", movieRepository.selectAll());
        return "home";
    }

}