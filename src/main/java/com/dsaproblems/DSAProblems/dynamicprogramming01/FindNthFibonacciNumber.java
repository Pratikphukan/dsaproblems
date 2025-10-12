package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindNthFibonacciNumber {

	public static void main(String[] args) {
		int n = 6;
		System.out.println(getFibonacciNumber(n)); // 0,1,1,2,3,5,8,...

		Integer[] a = new Integer[n + 1];
		for (int i = 0; i <= n; i++) {
			a[i] = -1;
		}
		List<Integer> A = new ArrayList<>(Arrays.asList(a));

		System.out.println(getFibonacciNumberTD(A, n)); // DP with memoization
		System.out.println(getFibonacciNumberTDv1(n)); // DP with memoization
		System.out.println(A);

		System.out.println(getFibonacciNumberBU(n));
		System.out.println(getFibonacciNumberBUBetter(n));
	}

	private static int getFibonacciNumberBUBetter(int n) {
		int a = 0;
		int b = 1;
		for (int i = 2; i <= n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return b;
	}

	private static Integer getFibonacciNumberBU(int n) {
		List<Integer> A = new ArrayList<>();
		A.add(0);
		A.add(1);
		for (int i = 2; i <= n; i++) {
			A.add(A.get(i - 1) + A.get(i - 2));
		}
		return A.get(n);
	}

	private static int getFibonacciNumberTDv1(int n) {
		Integer[] input = new Integer[n + 1];
		Function<Integer, Integer> changeToNegative = (x) -> -1;
		List<Integer> dp = new ArrayList<>(Arrays.asList(input));
		dp = dp.stream().map(changeToNegative).collect(Collectors.toList());
		return getFibonacciNumberTD(dp, n);
	}

	private static int getFibonacciNumberTD(List<Integer> A, int n) {
		if (A.get(n) == -1) {
			if (n < 2) {
				A.set(n, n);
			} else {
				A.set(n, getFibonacciNumberTD(A, n - 1) + getFibonacciNumberTD(A, n - 2));
			}
		}
		return A.get(n);
	}

	// fibonacci numbers: 0,1,1,2,3,5,8,.....
	// indices start from 0,1,2,...
	private static int getFibonacciNumber(int n) {
		if (n < 2) {
			return n;
		}
		return getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
	}

}
