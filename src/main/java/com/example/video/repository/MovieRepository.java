package com.example.video.repository;

import com.example.video.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOperations;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.commons.lang.StringUtils.lowerCase;

@Repository
public class MovieRepository {

    private HibernateTemplate hibernateOperations;

    @Autowired
    public MovieRepository(HibernateTemplate hibernateOperations) {
        this.hibernateOperations = hibernateOperations;
    }

    public List<Movie> selectAll() {
        return hibernateOperations.find("from Movie");
    }
}
