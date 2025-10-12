package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSumPathInMatrix {

	public static void main(String[] args) {
		List<List<Integer>> A = new ArrayList<>();
		A.add(new ArrayList<>(Arrays.asList(-2, -3, 3)));
		A.add(new ArrayList<>(Arrays.asList(-5, -10, 1)));
		A.add(new ArrayList<>(Arrays.asList(10, 30, -5)));
		// ---------//
//		A.add(new ArrayList<>(Arrays.asList(1, 3, 2)));
//		A.add(new ArrayList<>(Arrays.asList(4, 3, 1)));
//		A.add(new ArrayList<>(Arrays.asList(5, 6, 1)));
//		System.out.println(minSumPathv1(A, A.size() - 1, A.get(0).size() - 1));
//		System.out.println(minSumPathv2(A));
//		System.out.println(minSumPathv3(A));

		System.out.println(maxSumPathv1(A, A.size() - 1, A.get(0).size() - 1));
		System.out.println(maxSumPathv3(A));
	}

	private static int maxSumPathv3(List<List<Integer>> A) {
		int row = A.size(), col = A.get(0).size();
		int[] ans = new int[col];
		ans[0] = A.get(0).get(0);
		for (int i = 1; i < col; i++) {
			ans[i] = A.get(0).get(i) + ans[i - 1];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (j == 0) {
					ans[j] = A.get(i).get(j) + ans[j];
				} else {
					ans[j] = A.get(i).get(j) + Math.max(ans[j - 1], ans[j]);
				}
			}
		}
		return ans[col - 1];
	}

	private static int minSumPathv3(List<List<Integer>> A) {
		int row = A.size(), col = A.get(0).size();
		int[] ans = new int[col];
		ans[0] = A.get(0).get(0);
		for (int i = 1; i < col; i++) {
			ans[i] = A.get(0).get(i) + ans[i - 1];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (j == 0) {
					ans[j] = A.get(i).get(j) + ans[j];
				} else {
					ans[j] = A.get(i).get(j) + Math.min(ans[j - 1], ans[j]);
				}
			}
		}
		return ans[col - 1];
	}

	private static int minSumPathv2(List<List<Integer>> input) {
		int row = input.size();
		int col = input.get(0).size();
		int[][] ans = new int[row][col];
		return minSumPathv2Impl(ans, input, row - 1, col - 1);
	}

	private static int minSumPathv2Impl(int[][] ans, List<List<Integer>> input, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (ans[i][j] == 0) {
			if (i == 0 || j == 0) {
				if (j > 0) {
					ans[i][j] = input.get(i).get(j) + minSumPathv2Impl(ans, input, i, j - 1);
				} else {
					ans[i][j] = input.get(i).get(j) + minSumPathv2Impl(ans, input, i - 1, j);
				}
			} else {
				ans[i][j] = input.get(i).get(j)
						+ Math.min(minSumPathv2Impl(ans, input, i - 1, j), minSumPathv2Impl(ans, input, i, j - 1));
			}
		}
		return ans[i][j];
	}

	private static int minSumPathv1(List<List<Integer>> input, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (i == 0 || j == 0) {
			if (j > 0) {
				return input.get(i).get(j) + minSumPathv1(input, i, j - 1);
			}
			return input.get(i).get(j) + minSumPathv1(input, i - 1, j);
		}
		return input.get(i).get(j) + Math.min(minSumPathv1(input, i - 1, j), minSumPathv1(input, i, j - 1));
	}

	private static int maxSumPathv1(List<List<Integer>> input, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (i == 0 || j == 0) {
			if (j > 0) {
				return input.get(i).get(j) + maxSumPathv1(input, i, j - 1);
			}
			return input.get(i).get(j) + maxSumPathv1(input, i - 1, j);
		}
		return input.get(i).get(j) + Math.max(maxSumPathv1(input, i - 1, j), maxSumPathv1(input, i, j - 1));
	}

}
