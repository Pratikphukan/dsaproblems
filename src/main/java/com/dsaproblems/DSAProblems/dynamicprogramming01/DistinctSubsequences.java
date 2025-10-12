package com.dsaproblems.DSAProblems.dynamicprogramming01;

public class DistinctSubsequences {

	public static void main(String[] args) {
		String toFind = "ban";
		String input = "banana";
		System.out.println(findNoOfDistinctSubsequencesv1(0, 0, input, toFind));
		System.out.println(findNoOfDistinctSubsequencesv2(input.length() - 1, toFind.length() - 1, input, toFind));
		System.out.println(findNoOfDistinctSubsequencesTDv2(input.length() - 1, toFind.length() - 1, input, toFind));
		System.out.println(findNoOfDistinctSubsequencesBUv2(input, toFind));
	}

	private static int findNoOfDistinctSubsequencesBUv2(String input, String toFind) {
		int n = input.length();
		int m = toFind.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (input.charAt(i - 1) == toFind.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[n][m];
	}

	private static int findNoOfDistinctSubsequencesTDv2(int i, int j, String input, String toFind) {
		int n = input.length();
		int m = toFind.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return findNoOfDistinctSubsequencesTD(input, toFind, n, m, dp);
	}

	private static int findNoOfDistinctSubsequencesTD(String input, String toFind, int n, int m, Integer[][] dp) {
		if (m == 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		if (dp[n][m] == null) {
			if (input.charAt(n - 1) == toFind.charAt(m - 1)) {
				dp[n][m] = findNoOfDistinctSubsequencesTD(input, toFind, n - 1, m - 1, dp)
						+ findNoOfDistinctSubsequencesTD(input, toFind, n - 1, m, dp); // either you match them and move
																						// forward or ignore the one
																						// matched and find the next
																						// matched
			} else {
				dp[n][m] = findNoOfDistinctSubsequencesTD(input, toFind, n - 1, m, dp);
			}
		}
		return dp[n][m];
	}

	private static int findNoOfDistinctSubsequencesv21(int n, int m, String input, String toFind) {
		if (m == 0) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		if (input.charAt(n - 1) == input.charAt(m - 1)) {
			return findNoOfDistinctSubsequencesv2(n - 1, m - 1, input, toFind)
					+ findNoOfDistinctSubsequencesv2(n - 1, m, input, toFind);
		}
		return findNoOfDistinctSubsequencesv2(n - 1, m, input, toFind);
	}

	private static int findNoOfDistinctSubsequencesv2(int n, int m, String input, String toFind) {
		if (m < 0) {
			return 1;
		}
		if (n < 0) {
			return 0;
		}
		if (input.charAt(n) == input.charAt(m)) {
			return findNoOfDistinctSubsequencesv2(n - 1, m - 1, input, toFind)
					+ findNoOfDistinctSubsequencesv2(n - 1, m, input, toFind);
		}
		return findNoOfDistinctSubsequencesv2(n - 1, m, input, toFind);
	}

	private static int findNoOfDistinctSubsequencesv1(int i, int j, String input, String toFind) {
		if (j >= toFind.length()) {
			return 1;
		}
		if (i >= input.length()) {
			return 0;
		}
		if (input.charAt(i) == input.charAt(j)) {
			return findNoOfDistinctSubsequencesv1(i + 1, j + 1, input, toFind)
					+ findNoOfDistinctSubsequencesv1(i + 1, j, input, toFind);
		}
		return findNoOfDistinctSubsequencesv1(i + 1, j, input, toFind);
	}

}
