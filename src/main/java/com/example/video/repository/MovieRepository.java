package com.example.video.repository;

import com.example.video.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MovieRepository {
    public List<Movie> selectAll() {
        return Arrays.asList();
    }
}
