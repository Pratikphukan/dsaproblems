package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumSwaps {

	public static void main(String[] args) {
		// 52,7,93,47,68,26,51,44,5,41,88,19,78,38,17,13,24,74,92,5,84,27,48,49,37,59,3,56,79,26,55,60,16,83,63,40,55,9,96,29,7,22,27,74,78,38,11,65,29,52,36,21,94,46,52,47,87,33,87,70
		// 42, 5, 17, 12, 100, 11, 45, 8, 34->19
		// 5,17,100,11
		Integer[] digits = { 5, 17, 100, 11 };
		int B = 20;
		System.out.println(findMinSwapsv1(new ArrayList<>(Arrays.asList(digits)), B));
		System.out.println(findMinSwapsv2(new ArrayList<>(Arrays.asList(digits)), B));
		System.out.println(findMinSwapsv3(new ArrayList<>(Arrays.asList(digits)), B));
		System.out.println(findMinSwapsv4(new ArrayList<>(Arrays.asList(digits)), B));
	}

	private static int findMinSwapsv4(ArrayList<Integer> input, int B) {
		int len = input.size();
		int cnt = 0;
		for (int x : input)
			if (x <= B)
				cnt++;
		if (cnt <= 1)
			return 0;
		int left = 0, right = 0, x = 0;
		// Find the count of elements <= B in every window of length cnt using two
		// pointer approach
		while (right < cnt) {
			if (input.get(right) > B)
				x++;
			right++;
		}
		int ans = x;
		while (right < len) {
			if (input.get(right) > B)
				x++;
			if (input.get(left) > B)
				x--;
			ans = Math.min(ans, x);
			right++;
			left++;
		}
		return ans;
	}

	// working code
	private static int findMinSwapsv2(ArrayList<Integer> input, int B) {
		int len = input.size();
		if (len == 1) {
			return 0;
		}
		int count = 0;
		for (Integer item : input) {
			if (item <= B) {
				count++;
			}
		}
		if (count <= 1) {
			return 0;
		}
		int windowCount = 0;
		for (int i = 0; i < count; i++) {
			if (input.get(i) <= B) {
				windowCount++;
			}
		}
		int maxCountInWindow = windowCount;
		for (int i = 1; i <= (len - count); i++) {
			if (input.get(i - 1) <= B) {
				windowCount--;
			}
			if (input.get(i + count - 1) <= B) {
				windowCount++;
			}
			maxCountInWindow = Math.max(maxCountInWindow, windowCount);
		}
		return count - maxCountInWindow;
	}

	// working code
	private static int findMinSwapsv1(ArrayList<Integer> A, int B) {
		int len = A.size();
		int high_count = 0;
		for (int i = 0; i < len; i++) {
			if (A.get(i) > B) {
				high_count++;
			}
		}
		if (high_count == 0) { // all elements <= B are together
			return 0;
		}
		int windowCount = 0;
		int low_count = len - high_count;
		for (int i = 0; i < low_count; i++) {
			if (A.get(i) > B) {
				windowCount++;
			}
		}
		int minSwaps = windowCount;
		for (int i = 1; i <= len - low_count; i++) {
			if (A.get(i - 1) > B) {
				windowCount--;
			}
			if (A.get(i + low_count - 1) > B) {
				windowCount++;
			}
			minSwaps = Math.min(minSwaps, windowCount);
		}
		return minSwaps;
	}

	// not working
	private static int findMinSwapsv3(ArrayList<Integer> A, int B) {
		int ans = 0;
		int n = A.size();
		boolean[] bool = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (A.get(i) <= B) {
				bool[i] = true;
			}
		}
		for (int i = 0; i < n; i++) {
			if (bool[i]) {
				if (i == 0) {
					ans++;
				} else {
					if (!bool[i - 1]) {
						ans++;
					}

				}
			}
//			if ((i == 0 && bool[i]) || (i > 0 && bool[i] && !bool[i - 1])) {
//				ans++;
//			}
		}
		return ans - 1;
	}

}
