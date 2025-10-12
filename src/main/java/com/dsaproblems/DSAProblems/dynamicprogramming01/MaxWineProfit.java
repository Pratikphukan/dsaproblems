package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxWineProfit {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(2, 4, 6, 2, 5));
		// 2 4 6 2 5
		// 1, 2, 4, 3, 1
		// 21,13,46,1,9,39,11,37,47,49,11,9,16,21,1,21,21,6,33,30,49,25,31,1,45,23,1,19,21,21,19,27,35,16,30,18,1,16,25,16,11,49,2,11,31,41,22,44,41,5,31,31,29,37,45,9
		System.out.println(findMaxWineProfitv1(input));
		System.out.println(findMaxWineProfitv2(input));

		System.out.println(findMaxWineProfitv3(input, 0, input.size() - 1, 1));

		int n = input.size();
		Integer[][] dp = new Integer[n][n];
		System.out.println(findMaxWineProfitv4(input, dp, 0, input.size() - 1, 1));
	}

	private static int findMaxWineProfitv4(List<Integer> input, Integer[][] dp, int low, int high, int year) {
		if (low > high) {
			return 0;
		}
		if (low == high) {
			return input.get(low) * year;
		}
		if (dp[low][high] == null) {
			int left = input.get(low) * year + findMaxWineProfitv4(input, dp, low + 1, high, year + 1);
			int right = input.get(high) * year + findMaxWineProfitv4(input, dp, low, high - 1, year + 1);
			dp[low][high] = Math.max(left, right);
		}
		return dp[low][high];
	}

	// working code
	private static int findMaxWineProfitv3(List<Integer> input, int low, int high, int year) {
		if (low > high) {
			return 0;
		}
		if (low == high) {
			return input.get(low) * year;
		}
		int left = input.get(low) * year + findMaxWineProfitv3(input, low + 1, high, year + 1);
		int right = input.get(high) * year + findMaxWineProfitv3(input, low, high - 1, year + 1);
		return Math.max(left, right);
	}

	private static int findMaxWineProfitv2(List<Integer> input) {
		int low = 0;
		int high = input.size() - 1;
		int currMin = -1;
		int year = 1;
		int maxProfit = 0;
		while (low <= high) {
			if (input.get(low) > input.get(high)) {
				currMin = year * input.get(high);
				// input.set(low, year * input.get(low));
				high--;
			} else {
				currMin = year * input.get(low);
				// input.set(high, year * input.get(high));
				low++;
			}
			maxProfit += currMin;
			year++;
		}
		return maxProfit;
	}

	private static int findMaxWineProfitv1(List<Integer> input) {
		int low = 0;
		int high = input.size() - 1;
		int currMin = -1;
		int year = 1;
		int maxProfit = 0;
		while (low <= high) {
			if (input.get(low) > input.get(high)) {
				currMin = input.get(high);
				high--;
			} else {
				currMin = input.get(low);
				low++;
			}
			maxProfit += currMin * year;
			year++;
		}
		return maxProfit;
	}

}
