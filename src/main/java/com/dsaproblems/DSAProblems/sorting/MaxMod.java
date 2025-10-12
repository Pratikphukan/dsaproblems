package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxMod {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(683, 354, 95, 937, 78, 246, 319, 516, 913, 112));
		System.out.println(computeMaxModOfPairv1(A));
		System.out.println(computeMaxModOfPairv2(A));
	}

	private static int computeMaxModOfPairv2(List<Integer> input) {
		int firstMax = Integer.MIN_VALUE;
		for (Integer num : input) {
			firstMax = Math.max(num, firstMax);
		}
		int maxMod = 0;
		for (Integer num : input) {
			maxMod = Math.max(num % firstMax, maxMod);
		}
		return maxMod;
	}

	// a%b->[0,b-1], if a>b
	// a%b->a, if a<b
	// main point: check if the second max lies after the first max in the array
	private static int computeMaxModOfPairv1(List<Integer> input) {
		int firstMax = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;
		for (Integer num : input) {
			if (firstMax < num) {
				secondMax = firstMax;
				firstMax = num;
			}
			if (secondMax < num && firstMax != num) {
				secondMax = num;
			}
		}
		if (secondMax != Integer.MIN_VALUE) {
			return secondMax % firstMax;
		}
		return 0;
	}

}
