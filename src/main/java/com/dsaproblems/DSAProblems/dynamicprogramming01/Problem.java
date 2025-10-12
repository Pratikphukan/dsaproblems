package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {

	public static void main(String[] args) {
		findCountOfNumbersWhoseSquareSum(12);
		System.out.println(findCountOfNumbersWhoseSquareSumv1(12));
	}

	private static int findCountOfNumbersWhoseSquareSumv1(int n) {
		List<Integer> dp = new ArrayList<>(Arrays.asList(0, 1));
		for (int i = 2; i <= n; i++) {
			dp.add(i);// base case as every number can be represented as sum of 1s
			for (int x = 1; x * x <= i; x++) {
				dp.add(Math.min(dp.get(i), dp.get(i - x * x)) + 1);
			}
		}
		System.out.println(dp);
		return dp.get(n);
	}

	private static int findCountOfNumbersWhoseSquareSum(int n) {
		List<Integer> possibleNumbers = new ArrayList<>();
		for (int i = 1; i * i <= n; i++) {
			if (i * i == n) {
				return 1;
			}
			possibleNumbers.add(i);
		}
		System.out.println(possibleNumbers);
		int sum = 0;
		for (int i = possibleNumbers.size() - 1; i >= 0; i--) {
			sum += findCountOfNumbersWhoseSquareSum(n - possibleNumbers.get(i) * possibleNumbers.get(i));
		}
		return sum;
	}

}
