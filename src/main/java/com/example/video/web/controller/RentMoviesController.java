package com.example.video.web.controller;

import com.example.video.domain.*;
import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.LocalDate;
import com.example.video.domain.dates.LocalDateTime;
import com.example.video.repository.MovieRepository;
import com.example.video.repository.RentalRepository;
import com.example.video.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class RentMoviesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;
    private final TransactionRepository transactionRepository;

    private Customer customer;
    private String statement;
    private String[] movieNames;
    private int rentalDuration;

    @Autowired
    public RentMoviesController(final MovieRepository movieRepository,
                                final RentalRepository rentalRepository,
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

    @RequestMapping(value = "/rentMovies", method = RequestMethod.POST)
    public ModelAndView get(@RequestParam(value = "movieNames") String[] movieNames,
                            @RequestParam(value = "rentalDuration") String rentalDuration,
                            HttpSession session) throws Exception {

        logger.info("GET Rent movies Controller");
        Customer customer = (Customer) session.getAttribute("customer");

        final Set<Movie> movies = movieRepository.withTitles(movieNames);
        final Period rentalPeriod = Period.of(LocalDate.today(), Duration.ofDays(new Integer(rentalDuration)));
        final Period oneDayRentalPeriod = Period.of(LocalDate.today(), Duration.ofDays(new Integer(1)));

        final Set<Rental> rentals = new LinkedHashSet<Rental>();
        for (final Movie movie : movies) {
            Rental rental;
            if (movie.getPrice().equals(Movie.NEW_RELEASE)) {
                rental = new Rental(customer, movie, oneDayRentalPeriod);
            } else {
                rental = new Rental(customer, movie, rentalPeriod);

            }
            rentals.add(rental);
        }

        rentalRepository.add(rentals);

        final Transaction transaction = new Transaction(LocalDateTime.now(), customer, rentals);
        transactionRepository.add(transaction);

        String msg ="";
        for (Rental rental : rentals) {
            if (rental.getMovie().getPrice().equals(Movie.NEW_RELEASE))
            {
                msg = rental.getMovie().getTitle() + " is a new release, can only rent for a day";
            }
        }

        statement = customer.statement(transaction.getRentals());
        ModelAndView modelAndView = new ModelAndView("statement");
        modelAndView.getModelMap().put("statement", statement);
        modelAndView.getModelMap().put("message", msg);
        return modelAndView;
    }


}
