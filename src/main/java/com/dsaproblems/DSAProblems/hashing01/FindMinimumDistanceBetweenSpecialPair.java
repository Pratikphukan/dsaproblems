package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMinimumDistanceBetweenSpecialPair {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(7, 1, 3, 4, 1, 7));
		findMinimumDistanceBetweenSpecialPair1(input);
		findMinimumDistanceBetweenSpecialPair2(input);
		findMinimumDistanceBetweenSpecialPairv1(input);
		findMinimumDistanceBetweenSpecialPairv2(input);
	}

	private static int findMinimumDistanceBetweenSpecialPairv2(List<Integer> input) {
		Map<Integer, ListLimits> map = new HashMap<>();
		int minLength = Integer.MAX_VALUE;
		Integer key = null;
		for (int i = 0; i < input.size(); i++) {
			key = input.get(i);
			if (map.containsKey(key)) {
				if (map.get(key).getEndIdx() == null) {
					map.get(key).setEndIdx(i);
				}
			} else {
				map.put(key, new ListLimits(i, null));
			}
		}
		for (Map.Entry<Integer, ListLimits> entry : map.entrySet()) {
			if (entry.getValue().getEndIdx() != null) {
				minLength = Math.min(minLength, entry.getValue().getEndIdx() - entry.getValue().getStartIdx());
			}
		}
		if (minLength == Integer.MAX_VALUE) {
			return -1;
		}
		return minLength;
	}

	private static int findMinimumDistanceBetweenSpecialPairv1(List<Integer> input) {
		Map<Integer, Integer> map = new HashMap<>();
		int minLength = Integer.MAX_VALUE;
		Integer key = null;
		for (int i = 0; i < input.size(); i++) {
			key = input.get(i);
			if (map.containsKey(key)) {
				minLength = Math.min(minLength, i - map.get(key));
			}
			map.put(key, i);
		}
		if (minLength == Integer.MAX_VALUE) {
			return -1;
		}
		return minLength;
	}

	// working solution
	private static int findMinimumDistanceBetweenSpecialPair2(List<Integer> A) {
		if (A.size() == 1) {
			return -1;
		}
		int minLength = A.size();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++) { // don't use minLength as the original length, as it is getting updated
			int key = A.get(i);
			if (map.containsKey(key)) {
				minLength = Math.min(minLength, i - map.get(key));
			}
			map.put(key, i);
		}
		return (minLength == A.size()) ? -1 : minLength;
	}

	// indexOf() method takes the first index if multiple similar elements are there
	private static int findMinimumDistanceBetweenSpecialPair1(List<Integer> A) {
		if (A.size() == 1) {
			return -1;
		}
		int minLength = A.size();
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer item : A) {
			if (map.containsKey(item)) {
				minLength = Math.min(minLength, A.indexOf(item) - map.get(item));
			}
			map.put(item, A.indexOf(item));
		}
		return minLength;
	}

}
