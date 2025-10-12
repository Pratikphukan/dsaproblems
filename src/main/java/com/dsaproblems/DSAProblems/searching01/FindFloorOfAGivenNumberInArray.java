package com.dsaproblems.DSAProblems.searching01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindFloorOfAGivenNumberInArray {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(-5, 2, 3, 6, 9, 10, 11, 14, 18));
		int key = 12;

		System.out.println(findFloorOfKey(input, key));
	}

	private static int findFloorOfKey(List<Integer> A, int key) {
		int val = Integer.MIN_VALUE;
		int l = 0;
		int h = A.size() - 1;
		while (l <= h) {
			int mid = (l + h) / 2;
			if (A.get(mid) > key) {
				h = mid - 1;
			} else if (A.get(mid) < key) {
				val = A.get(mid); // maybe an answer
				l = mid + 1;
			} else {
				val = A.get(mid);
			}
		}
		return val;
	}

}
