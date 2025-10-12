package com.dsaproblems.DSAProblems.hashing02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCommonElementsInTwoArrays {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(2, 1, 4, 10));
		List<Integer> B = new ArrayList<>(Arrays.asList(3, 6, 2, 10, 10));

		findCommonElementsInTwoArrays(A, B);
	}

	private static List<Integer> findCommonElementsInTwoArrays(List<Integer> A, List<Integer> B) {
		List<Integer> ans = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		if (A.size() > B.size()) {
			List<Integer> temp = A;
			A = B;
			B = temp;
		}
		A.stream().forEach(item -> map.put(item, map.getOrDefault(item, 0) + 1));
		for (Integer item : B) {
			if (map.containsKey(item)) {
				ans.add(item);
				map.put(item, map.get(item) - 1);
				if (map.get(item) == 0) {
					map.remove(item);
				}
			}
		}
		return ans;
	}

}
