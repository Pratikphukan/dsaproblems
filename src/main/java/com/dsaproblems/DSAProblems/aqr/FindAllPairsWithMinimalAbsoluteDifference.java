package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindAllPairsWithMinimalAbsoluteDifference {

	public static void main(String[] args) {
		// 4, 2, 1, 3
		// 6, 2, 4, 10
		// 1, 3, 8, 10, 15
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 8, 10, 15));
		System.out.println(findAllPairsWithMinimalAbsoluteDifference(input));
	}

	private static List<List<Integer>> findAllPairsWithMinimalAbsoluteDifference(List<Integer> input) {
		Collections.sort(input);
		int minDifference = Integer.MAX_VALUE;
		for (int i = 1; i < input.size(); i++) {
			minDifference = Math.min(minDifference, input.get(i) - input.get(i - 1));
		}
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 1; i < input.size(); i++) {
			if ((input.get(i) - input.get(i - 1)) == minDifference) {
				ans.add(new ArrayList<>(Arrays.asList(input.get(i - 1), input.get(i))));
			}
		}
		return ans;
	}

}
