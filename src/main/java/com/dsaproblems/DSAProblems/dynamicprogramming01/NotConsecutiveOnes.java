package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.List;

public class NotConsecutiveOnes {

	public static void main(String[] args) {
		int n = 4;
		int ans = noOfPossibilities(1, n) + noOfPossibilities(0, n);
		System.out.println(ans);

		ans = noOfPossibilities2(n);
		System.out.println(ans);
	}

	private static int noOfPossibilities(int prev, int lastDigit) {
		if (lastDigit == 1)
			return 0;
		if (lastDigit == 2) {
			if (prev == 0)
				return 2;
			if (prev == 1)
				return 1;
		}
		if (prev == 1)
			return noOfPossibilities(0, lastDigit - 1);
		return noOfPossibilities(0, lastDigit - 1) + noOfPossibilities(1, lastDigit - 1);
	}

	private static int noOfPossibilities2(int n) {
		List<Integer> endInZeroPossibilities = new ArrayList<>();
		List<Integer> endInOnePossibilities = new ArrayList<>();
		endInOnePossibilities.add(1);
		endInZeroPossibilities.add(1);
		for (int i = 1; i < n; i++) {
			int prevZero = endInZeroPossibilities.get(i - 1) % 1000000007;
			int prevOne = endInOnePossibilities.get(i - 1) % 1000000007;
			endInZeroPossibilities.add((prevZero + prevOne) % 1000000007);
			endInOnePossibilities.add(prevZero);
		}
		return (endInOnePossibilities.get(n - 1) + endInZeroPossibilities.get(n - 1)) % 1000000007;
	}

}
