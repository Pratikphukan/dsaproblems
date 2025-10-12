package com.dsaproblems.DSAProblems.array02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindInArray {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(3, -2, 1, 4, 2, 6, 2, -4, 2, 8));

		List<List<Integer>> queries = new ArrayList<>();
		queries.add(new ArrayList<>(Arrays.asList(4, 8)));
		queries.add(new ArrayList<>(Arrays.asList(3, 9)));
		queries.add(new ArrayList<>(Arrays.asList(5, 7)));
		queries.add(new ArrayList<>(Arrays.asList(0, 4)));
		queries.add(new ArrayList<>(Arrays.asList(7, 7)));

		System.out.println(getGivenSubarraySums(input, queries));

		System.out.println(checkGivenSubarrayIncreasing(input, queries));

	}

	private static List<Boolean> checkGivenSubarrayIncreasing(List<Integer> A, List<List<Integer>> queries) {
		int len = A.size();
		List<Integer> prefix = new ArrayList<>();
		List<Boolean> queryAns = new ArrayList<>();
		for (int i = 0; i < len - 1; i++) {
			if (A.get(i + 1) > A.get(i)) {
				prefix.add(1);
			} else {
				prefix.add(0);
			}
		}
		prefix.add(1);
		for (int i = 1; i < prefix.size(); i++) {
			prefix.set(i, prefix.get(i - 1) + prefix.get(i));
		}
		for (int j = 0; j < queries.size(); j++) {
			int start = queries.get(j).get(0);
			int end = queries.get(j).get(1);
			boolean flag = false;
			if (start == 0) {
				if (prefix.get(end - 1) == end) {
					flag = true;
				}
			} else {
				if ((prefix.get(end - 1) - prefix.get(start - 1)) == end - start) {
					flag = true;
				}
			}
			queryAns.add(flag);
		}
		return queryAns;
	}

	private static List<Integer> getGivenSubarraySums(List<Integer> A, List<List<Integer>> queries) {
		int len = A.size();
		List<Integer> prefix = new ArrayList<>();
		List<Integer> queryAns = new ArrayList<>();
		prefix.add(A.get(0));
		for (int i = 1; i < len; i++) {
			prefix.add(prefix.get(i - 1) + A.get(i));
		}
		for (int j = 0; j < queries.size(); j++) {
			int start = queries.get(j).get(0);
			int end = queries.get(j).get(1);
			if (start == 0) {
				queryAns.add(prefix.get(end));
			} else {
				queryAns.add(prefix.get(end) - prefix.get(start - 1));
			}
		}
		return queryAns;
	}

}
