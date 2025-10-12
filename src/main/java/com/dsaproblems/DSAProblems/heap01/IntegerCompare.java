package com.dsaproblems.DSAProblems.heap01;

import java.util.Comparator;

public class IntegerCompare implements Comparator<Integer> {

	@Override
	public int compare(Integer a, Integer b) {
		return b - a;
	}

}
