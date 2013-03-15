package com.example.video.domain;

public class RegularPrice implements Price {

	public double getCharge(final int daysRented) {
		double result = 2;
		if (daysRented > 2)
			result += (daysRented - 2) * 1.5;
		return result;
	}

	public int getFrequentRenterPoints(final int daysRented) {
		return 1;
	}

    @Override
    public int getFreeDays(int daysRented) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
