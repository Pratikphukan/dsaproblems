package com.dsaproblems.DSAProblems.TwoPointers01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountDistinctPairsWithDifferenceB {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(8, 5, 1, 10, 5, 9, 9, 3, 5, 6, 6, 2, 8, 2, 2, 6, 3, 8, 7, 2, 5,
				3, 4, 3, 3, 2, 7, 9, 6, 8, 7, 2, 9, 10, 3, 8, 10, 6, 5, 4, 2, 3));
		int B = 3;

		// [8,5,1,10,5,9,9,3,5,6,6,2,8,2,2,6,3,8,7,2,5,3,4,3,3,2,7,9,6,8,7,2,9,10,3,8,10,6,5,4,2,3]

		System.out.println(findDistinctPairsWithDifferenceB1(A, B));
		System.out.println(findDistinctPairsWithDifferenceB2(A, B));
		System.out.println(findDistinctPairsWithDifferenceB3(A, B));
	}

	private static int findDistinctPairsWithDifferenceB3(List<Integer> input, int targetdifference) {
		Collections.sort(input);
		int count = 0;
		int left = 0;
		int right = 0;
		while (right < input.size() && left <= right) {
			if (left == right) {
				right++;
			} else {
				if ((input.get(right) - input.get(left)) <= targetdifference) {
					if ((input.get(right) - input.get(left)) == targetdifference) {
						count++;
					}
					right++;
					while (right < input.size() && input.get(right) - input.get(right - 1) == 0) {
						right++;
					}
				} else {
					left++;
					while (left <= right && input.get(left) - input.get(left - 1) == 0) {
						left++;
					}
				}
			}
		}
		return count;
	}

	// wrong code
	private static int findDistinctPairsWithDifferenceB2(List<Integer> A, int B) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer item : A) {
			if (map.containsKey(item)) {
				if (map.get(item) < 2) {
					map.put(item, map.get(item) + 1);
				}
			} else {
				map.put(item, 1);
			}
		}
		for (Integer item : A) {
			if (map.containsKey(item - B) && map.containsKey(item)) {
				count++;
				map.put(item, map.get(item) - 1);
				if (map.get(item) == 0) {
					map.remove(item);
				}
				if (map.get(item - B) <= 0) {
					map.remove(item - B);
				} else {
					map.put(item - B, map.get(item - B) - 1);
				}
			}
			if (map.containsKey(item + B) && map.containsKey(item)) {
				count++;
				map.put(item, map.get(item) - 1);
				if (map.get(item) == 0) {
					map.remove(item);
				}
				if (map.get(item + B) <= 0) {
					map.remove(item + B);
				} else {
					map.put(item + B, map.get(item + B) - 1);
				}
			}
		}
		return count;
	}

	// will not work for repeated values
	private static int findDistinctPairsWithDifferenceB1(List<Integer> A, int B) {
		int count = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = i + 1; j < A.size(); j++) {
				if (A.get(i) - A.get(j) == B || A.get(j) - A.get(i) == B) {
					count++;
				}
			}
		}
		return count;
	}

	private static int findDistinctPairsWithDifferenceB(List<Integer> A, int B) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		A.stream().forEach(item -> map.put(item, map.getOrDefault(item, 0) + 1));
		for (Integer item : A) {
			if (map.containsKey(item - B)) {
				if (map.get(item) < map.get(item - B)) {
					map.remove(item);
					map.put(item - B, map.get(item - B) - map.get(item));
				} else {
					map.remove(item - B);
					map.put(item, map.get(item) - map.get(item - B));
				}
			}
//			if (set.contains(item + B)) {
//				set.remove(item + B);
//				count++;
//			}
//			set.remove(item);
		}
		return count;
	}

}
