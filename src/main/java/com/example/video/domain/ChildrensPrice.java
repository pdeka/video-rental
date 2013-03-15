package com.example.video.domain;

public class ChildrensPrice implements Price {

	public double getCharge(final int daysRented) {
		double result = 1.5;
		if (daysRented > 3)
			result += (daysRented - 3) * 1.5;
		return result;
	}

	public int getFrequentRenterPoints(final int daysRented) {
		return 1;
	}

    @Override
    public int getFreeDays(int daysRented) {
        int result = 0;
        if (daysRented > 2) {
            result = 1;
        }
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
