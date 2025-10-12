package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedKnapsack {

	public static void main(String[] args) {
//		List<Integer> values = new ArrayList<>(Arrays.asList(6, 7));
//		List<Integer> weights = new ArrayList<>(Arrays.asList(5, 5));
//		int totalPossibleWeight = 10;
		List<Integer> values = new ArrayList<>(Arrays.asList(3, 2, 8, 3, 7, 10, 5));
		List<Integer> weights = new ArrayList<>(Arrays.asList(4, 1, 5, 4, 3, 7, 4));
		int totalPossibleWeight = 15;
		System.out.println(findMaximumAmountPossiblev1(totalPossibleWeight, values, weights));
		int items = values.size();
		System.out.println(findMaximumAmountPossiblev2(values, weights, items, totalPossibleWeight));
		System.out.println(findMaximumAmountPossiblev3(values, weights, items, totalPossibleWeight));
	}

	private static int findMaximumAmountPossiblev3(List<Integer> values, List<Integer> weights, int items,
			int totalPossibleWeight) {
		if (items == 0 || totalPossibleWeight <= 0) {
			return 0;
		}
		int amount = findMaximumAmountPossiblev3(values, weights, items - 1, totalPossibleWeight);
		if (totalPossibleWeight <= weights.get(items - 1)) {
			amount = Math.max(amount, findMaximumAmountPossiblev3(values, weights, items - 1,
					totalPossibleWeight - weights.get(items - 1)) + values.get(items - 1));
		}
		return amount;
	}

	private static int findMaximumAmountPossiblev2(List<Integer> values, List<Integer> weights, int items,
			int totalPossibleWeight) {
		if (items == 0) {
			return 0;
		}
		int amountIncludingCurrentWeight = 0;
		if ((totalPossibleWeight - weights.get(items - 1)) <= 0) {
			amountIncludingCurrentWeight = findMaximumAmountPossiblev2(values, weights, items - 1,
					totalPossibleWeight - weights.get(items - 1)) + values.get(items - 1);
		}
		int amountExcludingCurrentWeight = findMaximumAmountPossiblev2(values, weights, items - 1, totalPossibleWeight);
		return Math.max(amountExcludingCurrentWeight, amountIncludingCurrentWeight);
	}

	private static int findMaximumAmountPossiblev1(int totalPossibleWeight, List<Integer> values,
			List<Integer> weights) {
		int[][] arr = new int[weights.size()][totalPossibleWeight + 1];
		Integer includingCurrentWeightProfit = null;
		Integer excludingCurrentWeightProfit = null;
		for (int i = 0; i < weights.size(); i++) {
			for (int j = 1; j <= totalPossibleWeight; j++) {
				includingCurrentWeightProfit = 0;
				excludingCurrentWeightProfit = 0;
				if (weights.get(i) <= j) {
					includingCurrentWeightProfit = values.get(i) + arr[i][j - weights.get(i)];
				}
				if (i > 0) {
					excludingCurrentWeightProfit = arr[i - 1][j];
				}
				arr[i][j] = Math.max(includingCurrentWeightProfit, excludingCurrentWeightProfit);
			}
		}
		return arr[weights.size() - 1][totalPossibleWeight];
	}

	public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
		int[][] arr = new int[C.size()][A + 1];
		for (int i = 0; i < C.size(); i++) {
			for (int j = 1; j <= A; j++) {

				int includingCurrentWeightProfit = 0;
				int excludingCurrentWeightProfit = 0;

				if (C.get(i) <= j) {
					includingCurrentWeightProfit = B.get(i) + arr[i][j - C.get(i)];
				}

				if (i > 0) {
					excludingCurrentWeightProfit = arr[i - 1][j];
				}

				arr[i][j] = Math.max(includingCurrentWeightProfit, excludingCurrentWeightProfit);
			}
		}
		return arr[C.size() - 1][A];
	}

}
