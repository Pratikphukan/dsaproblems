package com.dsaproblems.DSAProblems.dynamicprogramming01;

public class MinimumOperations {

	public static void main(String[] args) {
		String s1 = "bat";
		String s2 = "bau";

		System.out.println(editOperations(s1, s2, s1.length(), s2.length()));
	}

	private static int editOperations(String s1, String s2, int n, int m) {
		if (n == 0) {
			return m;
		}

		if (m == 0) {
			return n;
		}

		if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
			return editOperations(s1, s2, n - 1, m - 1);
		}

		return 1 + Math.min(Math.min(editOperations(s1, s2, n - 1, m - 1), editOperations(s1, s2, n, m - 1)),
				editOperations(s1, s2, n - 1, m));
	}
}
