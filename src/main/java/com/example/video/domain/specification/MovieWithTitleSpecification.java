package com.example.video.domain.specification;

import com.example.video.domain.Movie;
import org.hibernate.Criteria;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class MovieWithTitleSpecification implements Specification<Movie> {

    private Collection<String> movieTitles;

    public MovieWithTitleSpecification(final String... movieTitles) {
        this.movieTitles = new HashSet<String>(Arrays.asList(movieTitles));
    }

    @Override
    public boolean isSatisfiedBy(final Movie movie) {
        return movieTitles.contains(movie.getTitle());
    }

    @Override
    public void populateCriteria(final Criteria criteria) {
        throw new UnsupportedOperationException("not implemented");
    }

}
