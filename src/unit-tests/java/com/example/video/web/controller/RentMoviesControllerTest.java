package com.example.video.web.controller;

import com.example.video.domain.*;
import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.LocalDate;
import com.example.video.domain.dates.LocalDateTime;
import com.example.video.repository.MovieRepository;
import com.example.video.repository.RentalRepository;
import com.example.video.repository.SetBasedMovieRepository;
import com.example.video.repository.TransactionRepository;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class RentMoviesControllerTest {

    private static final Movie THE_GODFATHER = new Movie("The Godfather", Movie.REGULAR);
    private static final Movie PULP_FICTION = new Movie("Pulp Fiction", Movie.REGULAR);
    private static final Movie FINDING_NEMO = new Movie("Finding Nemo", Movie.CHILDRENS);

    private MovieRepository movieRepository;
    private RentalRepository rentalRepository;
    private TransactionRepository transactionRepository;
    private RentMoviesController controller;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        movieRepository = new SetBasedMovieRepository();
        movieRepository.add(THE_GODFATHER);
        movieRepository.add(PULP_FICTION);
        movieRepository.add(FINDING_NEMO);

        rentalRepository = mock(RentalRepository.class);
        transactionRepository = mock(TransactionRepository.class);
        controller = new RentMoviesController(movieRepository, rentalRepository, transactionRepository);
        customer = mock(Customer.class);
        controller.setCustomer(customer);
    }

    @Before
    public void fixDate() {
        LocalDateTime.setSystemDateTime(LocalDateTime.now());
    }

    @After
    public void resetDate() {
        LocalDateTime.resetSystemDateTime();
    }

    @Test
    public void shouldCreateRentalForEachMovie() throws Exception {
        controller.setMovieNames(new String[]{THE_GODFATHER.getTitle(), PULP_FICTION.getTitle()});
        final int days = 1;
        controller.setRentalDuration(days);
        controller.get(null, null, null);

        verify(rentalRepository).add(argThat(isRentalsForDurationAndOf(days, THE_GODFATHER, PULP_FICTION)));
    }

    @Test
    public void shouldCreateTransactionForAllRentals() throws Exception {
        controller.setMovieNames(new String[]{THE_GODFATHER.getTitle(), FINDING_NEMO.getTitle()});
        final int days = 6;
        controller.setRentalDuration(days);
        controller.get(null, null, null);

        verify(transactionRepository).add(argThat(isTransactionWithRentalsForDurationAndOf(days, THE_GODFATHER, FINDING_NEMO)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldRetrieveCustomerStatement() throws Exception {
        controller.setMovieNames(new String[]{THE_GODFATHER.getTitle(), PULP_FICTION.getTitle()});
        final int days = 3;
        controller.setRentalDuration(days);

        final String statement = "my statement";
        when(customer.statement((Set<Rental>) anyObject())).thenReturn(statement);
        controller.get(null, null, null);

        verify(customer).statement(argThat(isRentalsForDurationAndOf(days, THE_GODFATHER, PULP_FICTION)));
        assertEquals(statement, controller.getStatement());
    }

    @SuppressWarnings("unchecked")
    private Matcher<Set<Rental>> isRentalsForDurationAndOf(final int days,
                                                           final Movie firstMovie,
                                                           final Movie... movies) {

        final Period period = Period.of(LocalDate.today(), Duration.ofDays(days));

        final List rentalMatchers = new ArrayList();
        rentalMatchers.add(hasSize(movies.length + 1));
        rentalMatchers.add(hasItem(allOf(hasProperty("period", equalTo(period)), hasProperty("movie",
                sameInstance(firstMovie)))));
        for (final Movie movie : movies) {
            rentalMatchers.add(hasItem(allOf(hasProperty("period", equalTo(period)), hasProperty("movie", sameInstance(movie)))));
        }

        return allOf((Iterable) rentalMatchers);
    }

    private Matcher<Transaction> isTransactionWithRentalsForDurationAndOf(final int days, final Movie firstMovie,
                                                                          final Movie... movies) {
        return hasProperty("rentals", isRentalsForDurationAndOf(days, firstMovie, movies));
    }

}
