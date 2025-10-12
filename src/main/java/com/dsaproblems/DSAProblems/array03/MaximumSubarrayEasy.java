package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumSubarrayEasy {

	public static void main(String[] args) {
		// 2, 1, 3, 4, 5|12
		List<Integer> input = new ArrayList<Integer>(Arrays.asList(2, 1, 3, 4, 5));
		int targetSum = 12;
		System.out.println(findGivenSubarraySum(input, targetSum));
	}

	private static int findGivenSubarraySum(List<Integer> input, int targetSum) {
		int len = input.size();
		Integer sum = null;
		int maxSum = 0;
		for (int i = 0; i < len; i++) {
			sum = 0;
			for (int j = i; j < len; j++) {
				sum += input.get(j);
				if (sum <= targetSum) {
					maxSum = Math.max(maxSum, sum);
				} else {
					break;
				}
			}
		}
		return maxSum;
	}

}
