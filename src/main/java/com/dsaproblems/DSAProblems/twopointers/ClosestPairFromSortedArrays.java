package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestPairFromSortedArrays {

	public static void main(String[] args) {
		// 1, 4, 5, 7|10, 20, 30, 40|32
		// 5,10,20|1,2,30|13

		// 6,7,9,13,21,29,56,74,77,83,84,88,93,96,101,104,114,115,122,125,144,147,151,152,154,161,165,167,174,179,185,189,192,194,198,201,203,208,224,229,230,238,252,253,257,264,266,275,278,279,282,284,291,312,316,322,332,335,336,339,364,380,381,384,387,388,390,392,394,395,401,403,431,432,440,442,443,444,446,451,454,461,470,483,492
		// 2,8,13,16,19,21,22,23,37,48,63,66,68,71,72,95,102,116,123,127,131,132,146,148,153,171,173,179,187,194,215,220,227,229,240,245,254,255,257,259,271,276,277,286,296,312,314,318,321,333,339,341,343,355,356,359,363,364,380,390,393,398,401,409,413,417,427,442,444,446,454,455,459,460,468,471,473,475,476,478,479,482,483,488,489
		// 409

		// 1|2,4|4

		// 1,2,3,4,5|2,4,6,8|9

		// 1,3,5,7,9|2,4,6,8,10|10
		List<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
		List<Integer> B = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
		int target = 10;
		System.out.println(findClosestPair(A, B, target));
		System.out.println(findClosestPairv1(A, B, target));
	}

	// working code
	private static ArrayList<Integer> findClosestPairv1(List<Integer> A, List<Integer> B, int target) {
		int diff = Integer.MAX_VALUE;
		int left = 0;
		int right = B.size() - 1;
		int idxA = -1;
		int idxB = -1;
		while (left < A.size() && right >= 0) {
			if (Math.abs(A.get(left) + B.get(right) - target) < diff) {
				diff = Math.abs(A.get(left) + B.get(right) - target);
				idxA = left;
				idxB = right;
			}
			if (Math.abs(A.get(left) + B.get(right) - target) == diff) {
				if (idxA >= left) {
					idxA = left;
					idxB = right;
				}
			}
			if (A.get(left) + B.get(right) >= target) {
				right--;
			} else {
				left++;
			}
		}
		return new ArrayList<>(Arrays.asList(A.get(idxA), B.get(idxB)));
	}

	// working solution
	private static List<Integer> findClosestPair(List<Integer> A, List<Integer> B, int target) {
		List<Element> sortedArray = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) < B.get(j)) {
				sortedArray.add(new Element(A.get(i), true));
				i++;
			} else if (A.get(i) > B.get(j)) {
				sortedArray.add(new Element(B.get(j), false));
				j++;
			} else {
				sortedArray.add(new Element(A.get(i), true));
				sortedArray.add(new Element(B.get(j), false));
				i++;
				j++;
			}
		}
		while (i < A.size()) {
			sortedArray.add(new Element(A.get(i), true));
			i++;
		}
		while (j < B.size()) {
			sortedArray.add(new Element(B.get(j), false));
			j++;
		}
		int low = 0;
		int high = sortedArray.size() - 1;
		int modDiff = Integer.MAX_VALUE;
		Element start = null;
		Element end = null;
		List<Integer> indices = new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE));
		while (low < high) {
			start = sortedArray.get(low);
			end = sortedArray.get(high);
			if ((start.isIsfromFirstArray() && !end.isIsfromFirstArray())
					|| (!start.isIsfromFirstArray() && end.isIsfromFirstArray())) {
				if (modDiff > Math.abs(start.getValue() + end.getValue() - target)) {
					modDiff = Math.abs(start.getValue() + end.getValue() - target);
					if (start.isIsfromFirstArray()) {
						indices.set(0, start.getValue());
						indices.set(1, end.getValue());
					} else {
						indices.set(0, end.getValue());
						indices.set(1, start.getValue());
					}
				} else if (modDiff == Math.abs(start.getValue() + end.getValue() - target)) {
					if (start.isIsfromFirstArray()) {
						if ((indices.get(0) > start.getValue())
								|| (indices.get(0) == start.getValue() && indices.get(1) > end.getValue())) {
							indices.set(0, start.getValue());
							indices.set(1, end.getValue());
						}
					} else {
						if ((indices.get(0) > end.getValue())
								|| (indices.get(0) == end.getValue() && indices.get(1) > start.getValue())) {
							indices.set(0, end.getValue());
							indices.set(1, start.getValue());
						}
					}
				}
			}
			if (start.getValue() + end.getValue() >= target) {
				high--;
			} else {
				low++;
			}
		}
		return indices;
	}

}
