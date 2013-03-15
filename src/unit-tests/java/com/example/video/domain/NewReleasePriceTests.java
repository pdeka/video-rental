package com.example.video.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NewReleasePriceTests {

    @Test
    public void shouldReturnNineForAThreeDayRentalCharge() throws Exception {
        Price newReleasePrice = new NewReleasePrice();
        Double charge = newReleasePrice.getCharge(3);
        assertThat(charge, is(9.0));
    }

    @Test
    public void shouldGetADoubleBonusForATwoDayRental() throws Exception {
        Price price = new NewReleasePrice();
        int frequentRenterPoints = price.getFrequentRenterPoints(2);
        assertThat(frequentRenterPoints, is(2));
    }

    @Test
    public void shouldGetOnePointForASingleDayRental() throws Exception {
        Price price = new NewReleasePrice();
        int frequentRenterPoints = price.getFrequentRenterPoints(1);
        assertThat(frequentRenterPoints, is(1));
    }


}
