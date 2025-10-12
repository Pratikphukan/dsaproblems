package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		List<Integer> a = List.of(7, 1, 4, 8, 11, 2, 14, 3);
		ArrayList<Integer> A = new ArrayList<>(a);
		System.out.println(lengthOfLongestInSubsequence(A));
	}

	private static int lengthOfLongestInSubsequence(ArrayList<Integer> A) {
		int len = A.size();
		int[] ans = new int[len];
		for (int i = 0; i < len; i++) {
			ans[i] = 1;
		}
		int maxlength = 1;
		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++) {
				if (A.get(i) > A.get(j) && ans[j] + 1 > ans[i]) {
					ans[i] = ans[j] + 1;
					maxlength = Math.max(maxlength, ans[i]);
				}
			}
		}
		return maxlength;
	}

}
