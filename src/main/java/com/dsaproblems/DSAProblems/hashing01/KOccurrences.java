package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class KOccurrences {

	public static void main(String[] args) {
		// 1,2,3,4,5|5
		// 1, 2, 2, 3, 3|2
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3));
		int B = 2;
		System.out.println(findKOccurrencesv1(input, B));
		System.out.println(findKOccurrencesv2(input, B));
		System.out.println(findKOccurrencesv3(input, B));
	}

	private static int findKOccurrencesv3(List<Integer> input, int B) {
		Map<Integer, Integer> itemFrequency = new HashMap<>();
		for (int item : input) {
			itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
		}
		int flag = 0, cnt = 0;
		for (Map.Entry<Integer, Integer> x : itemFrequency.entrySet()) {
			if (flag == 0 && x.getValue() == B) {
				flag = 1;
			}
			if (x.getValue() == B) {
				cnt += x.getKey();
				cnt %= 1000000007;
			}
		}
		if (cnt == 0 && flag == 0) {
			cnt = -1;
		}
		return cnt;
	}

	private static int findKOccurrencesv2(List<Integer> input, int B) {
		int MOD = 1000000007;
		Map<Integer, Integer> itemFrequency = new HashMap<>();
		Consumer<Integer> addToMap = (item) -> {
			itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
		};
		input.stream().forEach(addToMap);
		return itemFrequency.entrySet().stream().filter(entry -> entry.getValue() == B)
				.mapToInt(entry -> entry.getKey()).reduce(0, (acc, element) -> {
					return (acc % MOD + element % MOD) % MOD;
				});
	}

	private static int findKOccurrencesv1(List<Integer> input, int B) {
		int MOD = 1000000007;
		Map<Integer, Integer> itemFrequency = new HashMap<>();
		for (int item : input) {
			itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
		}
		int sum = 0;
		boolean exists = false;
		for (Map.Entry<Integer, Integer> entry : itemFrequency.entrySet()) {
			if (entry.getValue() == B) {
				exists = true;
				sum = (sum % MOD + entry.getKey() % MOD) % MOD;
			}
		}
		return exists ? sum : -1;
	}

}
