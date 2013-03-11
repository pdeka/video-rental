package com.example.video.repository;

import com.example.video.domain.Movie;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import com.example.video.repository.exception.NonUniqueObjectSelectedException;
import com.example.video.repository.exception.NullObjectAddedException;

import java.util.Collection;
import java.util.Set;

public interface MovieRepository {
    void add(Movie entity) throws NullObjectAddedException;

    void add(Collection<Movie> entities) throws NullObjectAddedException;

    Set<Movie> selectAll();

    Set<Movie> selectAll(OrderComparator<Movie> comparator);

    Set<Movie> selectSatisfying(Specification<Movie> specification);

    Set<Movie> selectSatisfying(Specification<Movie> specification, OrderComparator<Movie> comparator);

    Movie selectUnique(Specification<Movie> specification) throws NonUniqueObjectSelectedException;

    Set<Movie> withTitles(String... titles);
}