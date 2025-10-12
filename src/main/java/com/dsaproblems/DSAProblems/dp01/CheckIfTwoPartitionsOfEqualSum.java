package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckIfTwoPartitionsOfEqualSum {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 5, 11, 5));
		System.out.println(findPartition(input));
		System.out.println(findPartition1(input));
	}

	private static int findPartition1(List<Integer> input) {
		int sum = 0;
		for (Integer item : input)
			sum += item;
		if (sum % 2 != 0)
			return 0;
		int[][] dp = new int[input.size() + 1][sum / 2 + 1];
		for (int row[] : dp)
			Arrays.fill(row, -1);
		return isSubsetSum(input, input.size(), sum / 2, dp);
	}

	private static int isSubsetSum(List<Integer> input, int idx, int targetSum, int[][] dp) {
		if (targetSum == 0) {
			return 1;
		}
		if (dp[idx][targetSum] != -1) {
			return dp[idx][targetSum];
		}
		if (isSubsetSum(input, idx - 1, targetSum, dp) != 0
				|| isSubsetSum(input, idx - 1, targetSum - input.get(idx - 1), dp) != 0) {
			return dp[idx][targetSum] = 1;
		}
		return dp[idx][targetSum] = 0;
	}

	private static boolean findPartition(List<Integer> input) {
		int sum = 0;
		for (Integer item : input) {
			sum += item;
		}
		if (sum % 2 != 0) {
			return false;
		}
		return isSubsetSum(input, input.size(), sum / 2);
	}

	private static boolean isSubsetSum(List<Integer> input, int idx, int targetSum) {
		if (targetSum == 0) {
			return true;
		}
		if (input.get(idx - 1) > targetSum) {
			return isSubsetSum(input, idx - 1, targetSum); // just neglect the value in this particular index
		}
		return isSubsetSum(input, idx - 1, targetSum - input.get(idx - 1)) || isSubsetSum(input, idx - 1, targetSum);
	}

}
