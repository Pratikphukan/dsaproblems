package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMaximumDistanceBetweenSpecialPair {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(7, 1, 3, 6, 7, 9, 4, 1, 7));
		System.out.println(findMaximumDistanceBetweenSpecialPairv1(input));
		System.out.println(findMaximumDistanceBetweenSpecialPairv2(input));
	}

	private static int findMaximumDistanceBetweenSpecialPairv2(List<Integer> input) {
		Map<Integer, ListLimits> map = new HashMap<>();
		int maxLength = 0;
		Integer key = null;
		for (int i = 0; i < input.size(); i++) {
			key = input.get(i);
			if (map.containsKey(key)) {
				map.get(key).setEndIdx(i);
			} else {
				map.put(key, new ListLimits(i, null));
			}
		}
		for (Map.Entry<Integer, ListLimits> entry : map.entrySet()) {
			if (entry.getValue().getEndIdx() != null) {
				maxLength = Math.max(maxLength, entry.getValue().getEndIdx() - entry.getValue().getStartIdx());
			}
		}
		return maxLength;
	}

	private static int findMaximumDistanceBetweenSpecialPairv1(List<Integer> input) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxLength = 0;
		Integer key = null;
		for (int i = 0; i < input.size(); i++) {
			key = input.get(i);
			if (!map.containsKey(key)) {
				map.put(key, i);
			} else {
				maxLength = Math.max(maxLength, i - map.get(key));
			}
		}
		if (maxLength == Integer.MAX_VALUE) {
			return -1;
		}
		return maxLength;

	}

}
