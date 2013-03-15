package com.example.video.spring;

import com.example.video.domain.Customer;
import com.example.video.domain.Movie;
import com.example.video.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class VideoRentalConfiguration {

    @Bean
    public MovieRepository movieRepository() {
        final Movie avatar = new Movie("Avatar", Movie.NEW_RELEASE);
        final Movie upInTheAir = new Movie("Up In The Air", Movie.REGULAR);
        final Movie findingNemo = new Movie("Finding Nemo", Movie.CHILDRENS);
        final Movie cars = new Movie("Cars", Movie.CHILDRENS);
        final Movie shawShank = new Movie("Shawshank Redemption", Movie.REGULAR);
        final Movie topGun = new Movie("Top Gun", Movie.REGULAR);
        final Movie scaryMovie = new Movie("Scary Movie", Movie.REGULAR);
        return new SetBasedMovieRepository(Arrays.asList(avatar, upInTheAir, findingNemo, cars, shawShank, topGun, scaryMovie));
    }

    @Bean
    public CustomerRepository customerRepository() {
        final Customer customer1 = new Customer("James Madison");
        final Customer customer2 = new Customer("Zackery Taylor");
        final Customer customer3 = new Customer("Benjamin Harrison");
        return new SetBasedCustomerRepository(Arrays.asList(customer1, customer2, customer3));
    }

    @Bean
    public RentalRepository rentalRepository() {
        return new SetBasedRentalRepository();
    }

    @Bean
    public TransactionRepository transactionRepository() {
        return new SetBasedTransactionRepository();
    }


}
