package com.dsaproblems.DSAProblems.greedy;

import java.util.ArrayList;
import java.util.List;

public class MinimumNumberOfCoins {

	public static void main(String[] args) {
		int input = 9;
		System.out.println(minNoOfCoinsRequiredv1(input));
		System.out.println(minNoOfCoinsRequiredv2(input));
	}

	private static int minNoOfCoinsRequiredv2(int input) {
		List<Integer> denominations = new ArrayList<>();
		long val = 1;
		while (val <= 2000000000) {
			denominations.add((int) val);
			val = val * 5;
		}

		int ans = 0;
		int len = denominations.size();
		for (int i = len - 1; i >= 0; i--) {
			ans += input / denominations.get(i);
			input = input % denominations.get(i);
		}
		return ans;
	}

	private static int minNoOfCoinsRequiredv1(int input) {
		List<Integer> denominations = new ArrayList<>();
		int idx = 1;
		long value = 1;
		while (value <= Integer.MAX_VALUE) {
			denominations.add((int) value);
			value = (long) Math.pow(5, idx);
			idx++;
		}
		// Collections.sort(denominations, Collections.reverseOrder());
		int len = denominations.size();
		int count = 0;
		for (int i = len - 1; i >= 0; i--) {
			while (input >= denominations.get(i)) {
				// System.out.println(denominations.get(i));
				input -= denominations.get(i);
				count++;
			}
		}
		return count;
	}

}
