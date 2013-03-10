package com.example.video.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegularPriceTests {

    @Test
    public void shouldReturnTwoForAOneDayRental() {
        Price regularPrice = new RegularPrice();
        double charge = regularPrice.getCharge(1);
        assertThat(charge, is(2.0));
    }
}
