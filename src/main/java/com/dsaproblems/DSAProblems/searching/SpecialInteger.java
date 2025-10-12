package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.List;

public class SpecialInteger {

	public static void main(String[] args) {
		List<Integer> a = List.of(1, 2, 3, 4, 5);
		// (5, 17, 100, 11), 130, (3,2,5,4,6,3,7,2), 20
		// (1, 2, 3, 4, 5), 10
		ArrayList<Integer> A = new ArrayList<Integer>(a);
		int B = 10;
		System.out.println(findSpecialInteger(A, B));
	}

	private static int findSpecialInteger(ArrayList<Integer> A, int B) {
		int low = 1;
		int high = A.size();
		int mid = -1;
		int ans = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (maxSubarrraySumOfSizeMid(A, mid) <= B) {
				ans = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return ans;
	}

	private static long maxSubarrraySumOfSizeMid(ArrayList<Integer> A, int mid) {
		long sum = 0l;
		for (int i = 0; i < mid; i++) {
			sum += A.get(i);
		}
		long max = sum;
		for (int i = 1; i <= A.size() - mid; i++) {
			sum += (A.get(i + mid - 1) - A.get(i - 1));
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

}
