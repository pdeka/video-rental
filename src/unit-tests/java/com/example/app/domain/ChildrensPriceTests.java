package com.example.app.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ChildrensPriceTests {

    @Test
    public void shouldReturnAChargeOfThreeWhenRentedForFourDays() {
        // arrange
        Price price = new ChildrensPrice();
        int daysRented = 4;

        // act
        Double charge = price.getCharge(daysRented);

        // assert
        assertThat(charge, is(3.0));

    }

    @Test
    public void shouldReturnOneFrequentRenterPointWhenRentedForThreeDays() {
        Price price = new ChildrensPrice();
        int frequentRenterPoints = price.getFrequentRenterPoints(3);
        assertThat(frequentRenterPoints, is(1));
    }
}
