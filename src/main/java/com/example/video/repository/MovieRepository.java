package com.example.video.repository;

import com.example.video.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MovieRepository {

    private HibernateOperations hibernateOperations;

    @Autowired
    public MovieRepository(HibernateOperations hibernateOperations) {
        this.hibernateOperations = hibernateOperations;
    }

    public List<Movie> selectAll() {
        return hibernateOperations.find("from com.example.video.domain.Movie");
    }
}
