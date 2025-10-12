package com.dsaproblems.DSAProblems.heap01;

import java.util.Comparator;

public class DoubleCompare implements Comparator<Double> {

	@Override
	public int compare(Double num1, Double num2) {
		return Double.compare(num1, num2);
	}

}
