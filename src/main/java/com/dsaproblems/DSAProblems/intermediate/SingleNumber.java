package com.dsaproblems.DSAProblems.intermediate;

import java.util.Arrays;
import java.util.List;

public class SingleNumber {

	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1, 2, 2, 3, 1);
		System.out.println(findUniqueElementv1(input));
		System.out.println(findUniqueElementv2(input));
	}

	// working solution
	private static Integer findUniqueElementv2(List<Integer> input) {
		return input.parallelStream().reduce(0, (acc, x) -> acc ^ x);
	}

	// working solution
	private static int findUniqueElementv1(List<Integer> input) {
		int num = 0;
		for (int element : input) {
			num = num ^ element;
		}
		return num;
	}

}
