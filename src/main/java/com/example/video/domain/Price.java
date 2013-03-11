package com.example.video.domain;

public interface Price {
	double getCharge(int daysRented);

	int getFrequentRenterPoints(int daysRented);
}
