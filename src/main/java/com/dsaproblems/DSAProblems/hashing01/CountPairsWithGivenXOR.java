package com.dsaproblems.DSAProblems.hashing01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountPairsWithGivenXOR {

	public static void main(String[] args) {
		// numbers in the array are distinct
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 6, 8, 10, 15, 50));
		int value = 5;
		System.out.println(countPairsWithGivenXORv1(input, value));
		System.out.println(countPairsWithGivenXORv2(input, value));
		System.out.println(countPairsWithGivenXORv3(input, value));
	}

	// working solution
	private static int countPairsWithGivenXORv3(List<Integer> input, int value) {
		Set<Integer> set = new HashSet<>();
		int count = 0;
		for (int num : input) {
			count += set.contains(value ^ num) ? 1 : 0;
			set.add(num);
		}
		return count;
	}

	private static int countPairsWithGivenXORv2(List<Integer> input, int value) {
		Map<Integer, Integer> mp = new HashMap<>();
		int ans = 0;
		for (int x : input) {
			if (mp.containsKey(x ^ value)) {
				++ans;
			}
			mp.put(x, 1);
		}
		return ans;
	}

	// a^b = value->a^b^b = value^b->a=value^b
	private static int countPairsWithGivenXORv1(List<Integer> input, int value) {
		int result = 0;
		int num1 = 0;
		int num2 = 0;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < input.size(); i++) {
			num2 = input.get(i);
			num1 = value ^ num2;
			if (set.contains(num1)) {
				result += 1;
			}
			set.add(num2);
		}
		return result;
	}

}
