package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RepeatAndMissingNumber {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 1, 2, 5, 3));
//		System.out.println(findRepeatAndMissingNumbersv1(input));
//		System.out.println(findRepeatAndMissingNumbersv2(input));
//		System.out.println(findRepeatAndMissingNumbersv3(input));
		System.out.println(findRepeatAndMissingNumbersv4(input));
		System.out.println(findRepeatAndMissingNumbersv5(input));
	}

	private static List<Integer> findRepeatAndMissingNumbersv5(List<Integer> input) {
		List<Integer> ans = new ArrayList<>();
		int len = input.size();
		Collections.sort(input);
		long sum = 0l;
		for (int i = 1; i < len; i++) {
			if (input.get(i).equals(input.get(i - 1))) {
				ans.add(input.get(i));
			} else {
				sum += input.get(i);
			}
		}
		ans.add((int) (((len * (len + 1)) / 2) - sum));
		return ans;
	}

	private static List<Integer> findRepeatAndMissingNumbersv4(List<Integer> A) {
		int len = A.size();
		int[] bool = new int[len];
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			int item = A.get(i);
			if (item > 0 && item <= len) {
				bool[item - 1] += 1;
			}
		}
		int j = 0;
		for (; j < len; j++) {
			if (bool[j] > 1) {
				if (!ans.isEmpty())
					ans.add(0, j + 1);
				else
					ans.add(j + 1);
			}
			if (bool[j] == 0) {
				ans.add(j + 1);
			}
		}
		return ans;
	}

	private static List<Integer> findRepeatAndMissingNumbersv3(List<Integer> input) {
		int len = input.size();
		Integer element = null;
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			element = Math.abs(input.get(i));
			if (input.get(element - 1) < 0) {
				ans.add(i - 1);
			}
			input.set(element - 1, -1 * Math.abs(input.get(element - 1)));
		}
		int i = 0;
		for (; i < len; i++) {
			if (input.get(i) > 0) {
				ans.add(i + 1);
			}
		}
		return ans;
	}

	private static ArrayList<Integer> findRepeatAndMissingNumbersv2(List<Integer> input) {
		int len = input.size();
		long difference = 0;
		long squaredDifference = 0;
		for (int i = 0; i < len; i++) {
			difference += ((i + 1) - input.get(i));
			squaredDifference += ((i + 1) * (i + 1) - (input.get(i) * input.get(i)));
		}
		long sum = squaredDifference / difference;
		return new ArrayList<Integer>(Arrays.asList((int) (sum - difference) / 2, (int) (sum + difference) / 2));
	}

	private static ArrayList<Integer> findRepeatAndMissingNumbersv1(List<Integer> input) {
		int len = input.size();
		long sumOfElements = 0;
		long sumOfSquaresOfElements = 0;
		for (int i = 0; i < len; i++) {
			sumOfElements += input.get(i);
			sumOfSquaresOfElements += (input.get(i) * input.get(i));
		}
		long naturalSum = (len * (len + 1)) / 2;
		long naturalSquaresSum = (len * (len + 1) * (2 * len + 1)) / 6;
		long difference = sumOfElements - naturalSum;
		long sum = (sumOfSquaresOfElements - naturalSquaresSum) / difference;
		return new ArrayList<Integer>(Arrays.asList((int) (sum + difference) / 2, (int) (sum - difference) / 2));
	}

}
