package com.example.video.web.controller;

import com.example.video.domain.Movie;
import com.example.video.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class ViewHomeController {

    private final MovieRepository movieRepository;

    @Autowired
    public ViewHomeController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Set<Movie> getMovies() {
        return movieRepository.selectAll();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String get() throws Exception {
        return "home";
    }

}
