package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinXORValue {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 5, 7)); // array will have only positive elements
		System.out.println(findMinimumXORValuev1(input));
		System.out.println(findMinimumXORValuev2(input));
	}

	private static int findMinimumXORValuev2(List<Integer> input) {
		Collections.sort(input);
		int minXORPair = Integer.MAX_VALUE;
		for (int i = 1; i < input.size(); i++) {
			minXORPair = Math.min(minXORPair, input.get(i) ^ input.get(i - 1));
		}
		return minXORPair;
	}

	private static int findMinimumXORValuev1(List<Integer> input) {
		Collections.sort(input);
		int ans = input.get(0) ^ input.get(1);
		for (int i = 1; i < input.size() - 1; i++) {
			ans = Math.min(ans, input.get(i) ^ input.get(i + 1));
		}
		return ans;
	}

}
