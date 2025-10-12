package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.List;

public class SubarrayWithGivenSum {

	public static void main(String[] args) {
		List<Integer> a = List.of(1, 1000000000);
		// (1, 2, 3, 4, 5), 5, (5, 10, 20, 100, 105), 110
		// (12, 1, 50, 39, 32, 23, 22, 44, 17, 5, 9, 12, 10, 50, 26, 5, 23, 38, 31, 5,
		// 34, 8, 21, 11, 24, 44, 18, 19, 6, 31, 3, 47, 5, 2, 33, 44, 14, 9), 58
		// (1, 1000000000), 1000000000
		ArrayList<Integer> A = new ArrayList<>(a);
		int B = 1000000000; // 5, 9259332
		System.out.println(solve(A, B));
		System.out.println(solve1(A, B));
		System.out.println(solve2(A, B));
	}

	private static ArrayList<Integer> solve2(ArrayList<Integer> A, int B) {
		int l = 0, r = 0;
		long sum = A.get(0);
		while (r < A.size()) {
			if (sum == B) {
				ArrayList<Integer> ans = new ArrayList<Integer>();
				for (int i = l; i <= r; i++) {
					ans.add(A.get(i));
				}
				return ans;
			} else if (sum < B) {
				r++;
				if (r < A.size()) {
					sum += A.get(r);
				}
			} else {
				sum -= A.get(l);
				l++;
				if (l > r && l < A.size() - 1) {
					r++;
					sum += A.get(r);
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		ans.add(-1);
		return ans;
	}

	private static ArrayList<Integer> solve1(ArrayList<Integer> A, int B) {
		int l = 0;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int sum = A.get(0);
		if (sum == B) {
			ans.add(A.get(0));
			return ans;
		}
		for (int r = 1; r < A.size(); r++) {
			sum += A.get(r);
			while (sum > B && l <= r) {
				sum -= A.get(l);
				l++;
			}
			if (sum == B) {
				for (int j = l; j <= r; j++) {
					ans.add(A.get(j));
				}
				break;
			}
		}
		if (ans.isEmpty()) {
			ans.add(-1);
			return ans;
		}
		return ans;
	}

	private static ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
		int l = 0, h = 1;
		int sum = A.get(l) + A.get(h);
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while (l < h && h < A.size()) {
			if (sum < B) {
				h++;
				if (h < A.size()) {
					sum += A.get(h);
				}
			} else if (sum > B) {
				sum -= A.get(l);
				l++;
				if (l > h && h < A.size() - 1) {
					h++;
				}
			} else {
				for (int i = l; i <= h; i++) {
					ans.add(A.get(i));
				}
				break;
			}
		}
		if (ans.isEmpty()) {
			ans.add(-1);
			return ans;
		}
		return ans;
	}

}
