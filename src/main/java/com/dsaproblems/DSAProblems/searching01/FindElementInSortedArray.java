package com.dsaproblems.DSAProblems.searching01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindElementInSortedArray {

	public static void main(String[] args) {

		List<Integer> input = new ArrayList<>(Arrays.asList(3, 6, 9, 12, 14, 19, 20, 23, 25, 27));
		int key = 12;

		System.out.println(findIndexOfKey(input, key));

	}

	private static Integer findIndexOfKey(List<Integer> A, int key) {
		int idx = -1;
		int l = 0;
		int h = A.size() - 1;
		while (l <= h) { // if l and h are same, you should still check for an answer
			int mid = (l + h) / 2;
			if (A.get(mid) > key) {
				h = mid - 1;
			} else if (A.get(mid) < key) {
				l = mid + 1;
			} else {
				idx = mid;
			}
		}
		return idx;

	}

}
