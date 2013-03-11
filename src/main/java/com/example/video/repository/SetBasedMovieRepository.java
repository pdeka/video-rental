package com.example.video.repository;

import com.example.video.domain.Movie;
import com.example.video.domain.specification.MovieWithTitleSpecification;
import com.example.video.domain.specification.OrderComparator;
import com.example.video.domain.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

@Repository
public class SetBasedMovieRepository extends SetBasedRepository<Movie> implements MovieRepository {

    public SetBasedMovieRepository() {
        super();
    }

    public SetBasedMovieRepository(final Collection<Movie> entities) {
        super(entities);
    }

    @Override
    public Set<Movie> selectAll(OrderComparator<Movie> comparator) {
        return selectAll((Comparator<Movie>) comparator);
    }

    @Override
    public Set<Movie> selectSatisfying(final Specification<Movie> specification, final OrderComparator<Movie> comparator) {
        return selectSatisfying(specification, (Comparator<Movie>) comparator);
    }

    @Override
    public Set<Movie> withTitles(final String... titles) {
        return selectSatisfying(new MovieWithTitleSpecification(titles));
    }
}
