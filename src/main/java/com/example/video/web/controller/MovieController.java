package com.example.video.web.controller;

import com.example.video.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("movies", Arrays.asList());
        return "home";
    }

}