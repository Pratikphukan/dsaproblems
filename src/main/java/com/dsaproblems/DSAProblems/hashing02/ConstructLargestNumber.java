package com.dsaproblems.DSAProblems.hashing02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructLargestNumber {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(8, 6, 0, 4, 6, 4, 2, 7));
		System.out.println(formLargestNumber(input));
		System.out.println(formLargestNumberv1(input));
	}

	private static int formLargestNumberv1(List<Integer> input) {
		Map<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		input.stream().forEach(item -> map.put(item, map.getOrDefault(item, 0) + 1));
		for (int i = 9; i >= 0; i--) {
			if (map.containsKey(i)) {
				for (int j = 0; j < map.get(i); j++) {
					ans = ans * 10 + i;
					map.put(i, map.get(i) - 1);
					if (map.get(i) == 0)
						map.remove(i);
				}
			}
		}
		return ans;
	}

	private static int formLargestNumber(List<Integer> A) {
		Map<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		A.stream().forEach(item -> map.put(item, map.getOrDefault(item, 0) + 1));
		for (int i = 9; i >= 0; i--) {
			while (map.containsKey(i)) {
				ans = ans * 10 + i;
				map.put(i, map.get(i) - 1);
				if (map.get(i) == 0)
					map.remove(i);
			}
		}
		return ans;
	}

}
