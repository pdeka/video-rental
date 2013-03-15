package com.example.video.domain;

import com.example.video.domain.dates.Duration;
import com.example.video.domain.dates.LocalDate;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Client
 * Date: 15/03/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerTest {

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

}
