package com.example.video.domain;

public class NewReleasePrice implements Price {

	public double getCharge(final int daysRented) {
		return daysRented * 3;
	}

	public int getFrequentRenterPoints(final int daysRented) {
		// add bonus for a two day new release rental
		if (daysRented > 1)
			return 2;
		else
			return 1;
	}

    @Override
    public int getFreeDays(int daysRented) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
