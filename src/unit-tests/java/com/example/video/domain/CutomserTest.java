package com.example.video.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.example.video.domain.Period.of;
import static com.example.video.domain.dates.Duration.ofDays;
import static com.example.video.domain.dates.LocalDate.today;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created with IntelliJ IDEA.
 * User: Client
 * Date: 15/03/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class CutomserTest {

    private final  static String CUSTOMER_NAME = "Johnny Customer";
    Customer customer;
    Set<Rental> rentals = new HashSet<Rental>();
    Movie movie1 = new Movie("Movie1", new NewReleasePrice());
    Movie movie2 = new Movie("Movie2", new NewReleasePrice());
    Period tenDays = of(today(), ofDays(10));
    Rental firstRental = new Rental(customer, movie1, tenDays);

    @Before
    public void setUp() {
        customer = new Customer(CUSTOMER_NAME);
    }
    @Test
    public  void shouldGenerateAStatementWithHeaderForCustomerWithNoRental()
    {

        String statement = customer.statement(rentals);
        Assert.assertTrue(statement.contains(CUSTOMER_NAME));
    }

    @Test
    public void shouldAmountOwingBeingUpdateWhenRentalsAreProcessed() {
        rentals.add(firstRental);
        customer.statement(rentals);
        Assert.assertThat(customer.getAmountDue(), equalTo(30.0));
    }

    @Test
    public void shouldIncrementAmountDueForSubsequentRental() {
        rentals.add(firstRental);
        customer.statement(rentals);
        double  initialAmount =  customer.getAmountDue();

        Rental secondRental = new Rental(customer, movie2, tenDays);;

        rentals.remove(firstRental);
        rentals.add(secondRental);
        customer.statement(rentals);


        double subsequent =  customer.getAmountDue();
        Assert.assertThat(subsequent - initialAmount, equalTo(30.0));
    }
}
