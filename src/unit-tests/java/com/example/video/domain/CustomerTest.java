package com.example.video.domain;

import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.LocalDate;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.example.video.domain.Period.of;
import static com.example.video.domain.dates.Duration.ofDays;
import static com.example.video.domain.dates.LocalDate.today;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Client
 * Date: 15/03/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTest {

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
    public void shouldGetTotalAmountForOneRental() {
        Customer ian = new Customer("Ian");
        Set<Rental> ianRentals = new HashSet<Rental>();
        Rental ianFavouriteRental = new Rental(ian, new Movie("Pulp Fiction", new NewReleasePrice()), Period.of(LocalDate.today(), Duration.ofDays(7)));
        ianRentals.add(ianFavouriteRental);
        double amount = ian.getTotalAmount(ianRentals);

        Assert.assertEquals(21.0, amount);
    }

    @Test
    public void shouldGetTotalAmountForASetOfRentals() {
        Customer ian = new Customer("Ian");
        Set<Rental> ianRentals = new HashSet<Rental>();
        Rental ianFavouriteRental = new Rental(ian, new Movie("Pulp Fiction", new NewReleasePrice()), Period.of(LocalDate.today(), Duration.ofDays(7)));
        Rental ianWifeRental = new Rental(ian, new Movie("Help", new ChildrensPrice()), Period.of(LocalDate.today(), Duration.ofDays(7)));
        ianRentals.add(ianFavouriteRental);
        ianRentals.add(ianWifeRental);
        double amount = ian.getTotalAmount(ianRentals);

        Assert.assertEquals(28.5, amount);
    }

    @Test
    public  void shouldGenerateAStatementWithHeaderForCustomerWithNoRental()
    {

        String statement = customer.statement(rentals);
        org.junit.Assert.assertTrue(statement.contains(CUSTOMER_NAME));
    }

    @Test
    public void shouldAmountOwingBeingUpdateWhenRentalsAreProcessed() {
        rentals.add(firstRental);
        customer.statement(rentals);
        org.junit.Assert.assertThat(customer.getAmountDue(), equalTo(30.0));
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
        org.junit.Assert.assertThat(subsequent - initialAmount, equalTo(30.0));
    }

}
