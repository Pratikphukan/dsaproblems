package com.dsaproblems.DSAProblems.greedy;

import java.util.Comparator;

public class ValidityPriceCompare implements Comparator<Car> {

	@Override
	public int compare(Car car1, Car car2) {
		int value = car1.getDiscountValidity() - car2.getDiscountValidity();
		if (value == 0) {
			return car2.getPrice() - car1.getPrice();
		}
		return value;
	}

}
