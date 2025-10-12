package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortAccordingToFactors {

	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList<>(Arrays.asList(3, 9, 4, 6, 12, 10, 7));
		List<Factor> factors = new ArrayList<>();
		Factor factor = null;
		for (Integer num : input) {
			factor = new Factor(num);
			System.out.println(factor.getCountOfFactors());
			factors.add(factor);
		}
		// Collections.sort(factors);
	}

}
