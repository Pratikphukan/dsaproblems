package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;

public class Program4 {

	public static void main(String[] args) {

		List<Integer> arr0 = List.of(-1, 4, 7, 6, -2, 7, 8, 10);
		List<Integer> arr1 = new ArrayList<>(arr0);
		int k = 3;
		// printSubArrayLen(arr1, k);
		// System.out.println(arr1);
		// System.out.println(maxSubArraySumLen1(arr1, k));
		// System.out.println(maxSubArraySumLen2(arr1, k));
		printSubArraySums1(arr1);
		printSubArraySums2(arr1);

		List<Integer> arr2 = List.of(-1, 4, 7, 6, -2, 7, 8, 10);
		List<Integer> arr3 = new ArrayList<>(arr2);
		k = 1;
		// printSubArrayLen(arr3, k);
		// System.out.println(arr3);
		// System.out.println(maxSubArraySumLen1(arr3, k));
		// System.out.println(maxSubArraySumLen2(arr3, k));

		List<Integer> arr4 = List.of(-3, 4, 2, 8, 7, 9, 6, 2, 10);
		List<Integer> arr5 = new ArrayList<>(arr4);
		k = 6;
		printSubArrayLen(arr5, k);
		System.out.println(arr5);
		System.out.println(maxSubArraySumLen1(arr5, k));
		System.out.println(maxSubArraySumLen2(arr5, k));

		List<Integer> arr6 = List.of(3, -2, 1, 4, 6, 9, 8);
		List<Integer> arr7 = new ArrayList<>(arr6);
		k = 4;
		printSubArrayLen(arr7, k);
		System.out.println(arr7);
		System.out.println(maxSubArraySumLen1(arr7, k));
		System.out.println(maxSubArraySumLen2(arr7, k));

	}

	private static void printSubArraySums2(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				int sum = 0;
				for (int l = i; l <= j; l++) {
					System.out.printf("%d ", arr.get(l));
					sum += arr.get(l);
				}
				System.out.println();
				System.out.println(sum);
			}
		}
	}

	private static void printSubArraySums1(List<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			int sum = 0;
			for (int j = i; j < arr.size(); j++) {
				sum += arr.get(j);
				System.out.println(sum);
			}
		}
	}

	private static int maxSubArraySumLen2(List<Integer> arr, int k) {
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += arr.get(i);
		}
		int max = sum;
		for (int i = 1; i <= arr.size() - k; i++) {
			sum += arr.get(i + k - 1) - arr.get(i - 1);
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	private static int maxSubArraySumLen1(List<Integer> arr, int k) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= arr.size() - k; i++) { // values of k ranges from 1 to N(inclusive)
			int sum = 0;
			for (int j = i; j <= i + k - 1; j++) {
				sum += arr.get(j);
			}
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	private static void printSubArrayLen(List<Integer> arr, int k) {
		for (int i = 0; i <= arr.size() - k; i++) {
			for (int j = i; j <= i + k - 1; j++) {
				System.out.printf("%d ", arr.get(j));
			}
			System.out.println();
		}

	}

}
