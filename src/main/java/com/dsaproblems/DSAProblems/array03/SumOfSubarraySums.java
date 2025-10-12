package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfSubarraySums {

	public static void main(String[] args) {
		// -1, 4, 7, 6, -2, 7, 8, 10
		// 2, 1, 3
		// 2, 9, 5
		List<Integer> A = new ArrayList<Integer>(Arrays.asList(-1, 4, 7, 6, -2, 7, 8, 10));
		System.out.println(subarraySumv1(A));

		System.out.println(subarraySumv2(A));

		System.out.println(subarraySumv3(A));
	}

	private static Long subarraySumv3(List<Integer> arr) {
		long sum = 0L;
		int len = arr.size();
		int end = (len - 1) / 2;
		long mul = 1;
		for (int i = 0; i <= end; i++) {
			mul = Long.valueOf(i + 1) * Long.valueOf(len - 1);
			if (i == len - 1 - i) {
				sum += arr.get(i) * mul;
			} else {
				sum += (arr.get(i) + arr.get(len - 1 - i)) * mul;
			}
		}
		return sum;
	}

	// working solution
	public static Long subarraySumv1(List<Integer> A) {
		long sum = 0L;
		int len = A.size();
		int end = (len - 1) / 2;// in case of even, first mid, in case of odd, actual mid
		for (int i = 0; i <= end; i++) {
			long mul = (long) (i + 1) * (long) (len - i);
			if (i == (len - i - 1)) {
				sum += (long) A.get(i) * mul;
			} else {
				sum += (long) (A.get(i) + A.get(len - i - 1)) * mul;
			}
		}
		return sum;
	}

	private static Long subarraySumv2(List<Integer> arr) {
		long sum = 0L;
		int len = arr.size();
		for (int i = 0; i < len; i++) {
			sum += arr.get(i) * (i + 1) * (len - i);
		}
		return sum;
	}

}
