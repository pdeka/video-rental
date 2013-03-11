package com.example.video.web.controller;

import com.example.video.domain.*;
import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.LocalDate;
import com.example.video.domain.dates.LocalDateTime;
import com.example.video.repository.MovieRepository;
import com.example.video.repository.RentalRepository;
import com.example.video.repository.TransactionRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class RentMoviesController {

    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;
    private final TransactionRepository transactionRepository;

    private Customer customer;
    private String statement;
    private String[] movieNames;
    private int rentalDuration;

    public RentMoviesController(final MovieRepository movieRepository, final RentalRepository rentalRepository,
                                final TransactionRepository transactionRepository) {
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
        this.transactionRepository = transactionRepository;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public void setMovieNames(final String[] movieNames) {
        this.movieNames = movieNames;
    }

    public void setRentalDuration(final int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getStatement() {
        return statement;
    }

    // TODO: spring request mapping
    public String get() throws Exception {
        final Set<Movie> movies = movieRepository.withTitles(movieNames);
        final Period rentalPeriod = Period.of(LocalDate.today(), Duration.ofDays(rentalDuration));

        final Set<Rental> rentals = new LinkedHashSet<Rental>();
        for (final Movie movie : movies) {
            final Rental rental = new Rental(customer, movie, rentalPeriod);
            rentals.add(rental);
        }

        rentalRepository.add(rentals);
        final Transaction transaction = new Transaction(LocalDateTime.now(), customer, rentals);
        transactionRepository.add(transaction);

        statement = customer.statement(transaction.getRentals());
        return "success";
    }


}
