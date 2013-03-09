package com.example.app.domain;

public interface Price {
    double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented);
}
