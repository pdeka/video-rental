package com.example.video.web.controller;

import com.example.video.domain.*;
import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.FiniteLocalDate;
import com.example.video.domain.dates.LocalDate;
import com.example.video.domain.dates.LocalDateTime;
import com.example.video.repository.MovieRepository;
import com.example.video.repository.RentalRepository;
import com.example.video.repository.SetBasedMovieRepository;
import com.example.video.repository.TransactionRepository;
import com.example.video.repository.exception.NullObjectAddedException;
import org.hamcrest.Matcher;
import org.hibernate.validator.AssertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.video.domain.Period.of;
import static com.example.video.domain.dates.Duration.ofDays;
import static com.example.video.domain.dates.LocalDate.today;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RentMoviesControllerTest {

    private static final Movie THE_GODFATHER = new Movie("The Godfather", Movie.REGULAR);
    private static final Movie PULP_FICTION = new Movie("Pulp Fiction", Movie.REGULAR);
    private static final Movie FINDING_NEMO = new Movie("Finding Nemo", Movie.CHILDRENS);
    private static final Movie SKY_FALL = new Movie("Sky Fall", Movie.NEW_RELEASE);

    private MovieRepository movieRepository;
    private RentalRepository rentalRepository;
    private TransactionRepository transactionRepository;
    private RentMoviesController controller;
    private Customer customer;

    @Mock
    HttpSession session;

    @Mock
    Period mockPeriod;

//    @Mock
//    Duration mockDuration;


    @Before
    public void setUp() throws Exception {
        movieRepository = new SetBasedMovieRepository();
        movieRepository.add(THE_GODFATHER);
        movieRepository.add(PULP_FICTION);
        movieRepository.add(FINDING_NEMO);
        movieRepository.add(SKY_FALL);

        rentalRepository = mock(RentalRepository.class);
        transactionRepository = mock(TransactionRepository.class);
        controller = new RentMoviesController(movieRepository, rentalRepository, transactionRepository);
        customer = mock(Customer.class);

        when(session.getAttribute("customer")).thenReturn(customer);

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
        String[] movieNames = {THE_GODFATHER.getTitle(), PULP_FICTION.getTitle()};
        controller.get(movieNames, "1", session);

        verify(rentalRepository).add(argThat(isRentalsForDurationAndOf(1, THE_GODFATHER, PULP_FICTION)));
    }

    @Test
    public void shouldCreateTransactionForAllRentals() throws Exception {
        String[] movieNames = {THE_GODFATHER.getTitle(), FINDING_NEMO.getTitle()};
        controller.get(movieNames, "6", session);

        verify(transactionRepository).add(argThat(isTransactionWithRentalsForDurationAndOf(6, THE_GODFATHER, FINDING_NEMO)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldRetrieveCustomerStatement() throws Exception {
        String[] movieNames = {THE_GODFATHER.getTitle(), PULP_FICTION.getTitle()};
        final int days = 3;
        final String statement = "my statement";
        when(customer.statement((Set<Rental>) anyObject())).thenReturn(statement);
        controller.get(movieNames, "3", session);

        verify(customer).statement(argThat(isRentalsForDurationAndOf(days, THE_GODFATHER, PULP_FICTION)));
        assertEquals(statement, controller.getStatement());
    }

    @Test
    public void shouldSayEligibleWhenTenOrMoreFrequentPoints() {
        Set<Rental> rentals = new HashSet<Rental>();

        Customer customer = new Customer("rommel");

        for(int i=0; i < 6; i++)
        {
            Period period = of(today(), ofDays(10));
            Movie movie = new Movie("Movie" + i, new NewReleasePrice());
            rentals.add(new Rental(customer, movie, period));
        }

        assertTrue(customer.statement(rentals).contains("Congratulations"));


    }

    @Test
    public void shouldNotSayEligibleWhenLessThanTen() {
        Set<Rental> rentals = new HashSet<Rental>();

        Customer customer = new Customer("rommel");
        Movie movie = new Movie("Movie1", new NewReleasePrice());
        Period tenDays = of(today(), ofDays(10));
        Rental firstRental = new Rental(customer, movie, tenDays);

        rentals.add(firstRental);

        assertTrue(!customer.statement(rentals).contains("Congratulations"));

    }

    @Test
    public void shouldDefaultToOneDayWhenRentingANewRelease() throws Exception {
        String[] movieNames = {SKY_FALL.getTitle()};
        controller.get(movieNames, "3", session);

        verify(rentalRepository).add(argThat(isRentalsForDurationAndOf(1, SKY_FALL)));
    }

    @SuppressWarnings("unchecked")
    private Matcher<Set<Rental>> isRentalsForDurationAndOf(final int days,
                                                           final Movie firstMovie,
                                                           final Movie... movies) {

        final Period period = of(today(), ofDays(days));

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
