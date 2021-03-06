package com.example.video.domain;

import java.util.Set;

public class Customer {
	private String name;
	private int frequentRenterPoints = 0;
    private Double amountDue = 0.0;

    public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

    public double getTotalAmount(final Set<Rental> newRentals) {
        double totalAmount = 0;
        for (Rental rental : newRentals) {
            final Integer rentalDays = rental.getPeriod().getDuration().getDays();
            totalAmount += rental.getMovie().getPrice().getCharge(rentalDays);
        }
        return totalAmount;
    }

	public String statement(final Set<Rental> newRentals) {
		String result = "Rental Record for " + getName() + "\n";

		double totalAmount = 0;
        int freeDays = 0;
		for (Rental rental : newRentals) {
			// show figures for this rental
			final Integer rentalDays = rental.getPeriod().getDuration().getDays();

            freeDays = rental.getMovie().getPrice().getFreeDays(rentalDays);
            if (freeDays >= 1) {
                result += freeDays + " extra day";
            }

			result += "  " + rental.getMovie().getTitle() + "  -  $"
					+ String.valueOf(rental.getMovie().getPrice().getCharge(rentalDays)) + "\n";

			totalAmount += rental.getMovie().getPrice().getCharge(rentalDays);

			frequentRenterPoints += rental.getMovie().getPrice().getFrequentRenterPoints(rentalDays);

		}

        amountDue += totalAmount;
            // add footer lines
		result += "Amount charged is $" + String.valueOf(totalAmount) + "\n";
		result += "You have a new total of " + String.valueOf(frequentRenterPoints) + " frequent renter points" + "\n \n";
        result += "Total Amount Due: $" + String.valueOf(amountDue) + "\n \n";
        if (frequentRenterPoints >= 10) {
            result += "Congratulations, You are eligible for a free rental";
        }


		return result;
	}

    public Double getAmountDue() {
        return amountDue;
    }
}
