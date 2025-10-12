package com.dsaproblems.DSAProblems.dynamicprogramming01;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
//		String A = "bebdeeedaddecebbbbbabebedc";
//		String B = "abaaddaabbedeedeacbcdcaaed";
		// A : "bebdeeedaddecebbbbbabebedc", "abde"
		// B : "abaaddaabbedeedeacbcdcaaed", "acbef"
		// System.out.println(lengthOfLCS1(a, b));
//		System.out.println(lengthOfLCS2(a, b));
//		System.out.println(lengthOfLCS3(a, b));

//		String A = "abbcdgf";
//		String B = "bacdegf";

		String A = "abde";
		String B = "acbef";
		System.out.println(lengthOfLCS2(A, B));
		System.out.println(LCSv1(A, B, A.length() - 1, B.length() - 1));

		System.out.println(LCS(A, B, A.length(), B.length()));
		System.out.println(LCS1(A, B, 0, 0));
	}

	private static int LCSv1(String a, String b, int aLastIdx, int bLastIdx) {
		if (aLastIdx == -1 || bLastIdx == -1) {
			return 0;
		}
		if (a.charAt(aLastIdx) == b.charAt(bLastIdx)) {
			return 1 + LCSv1(a, b, aLastIdx - 1, bLastIdx - 1);
		}
		return Math.max(LCSv1(a, b, aLastIdx - 1, bLastIdx), LCSv1(a, b, aLastIdx, bLastIdx - 1));
	}

	private static int lengthOfLCS3(String A, String B) {
		int n = A.length(), m = B.length();
		if (n == 0 || m == 0) {
			return 0;
		}
		int[][] arr = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					arr[i][j] = 1 + arr[i - 1][j - 1];
				} else {
					arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
				}
			}
		}
		return arr[m][n];
	}

	private static Integer lengthOfLCS2(String A, String B) {
		int n = A.length(), m = B.length();
		Integer[][] arr = new Integer[n + 1][m + 1];
		return LCSTD(A, B, n, m, arr);
	}

	private static Integer LCSTD(String A, String B, int n, int m, Integer[][] arr) {
		if (n == 0 || m == 0) {
			return 0;
		}
		if (arr[n][m] == null) {
			if (A.charAt(n - 1) == B.charAt(m - 1)) {
				arr[n][m] = 1 + LCSTD(A, B, n - 1, m - 1, arr);
			} else {
				arr[n][m] = Math.max(LCSTD(A, B, n - 1, m, arr), LCSTD(A, B, n, m - 1, arr));
			}
		}
		return arr[n][m];
	}

	private static int lengthOfLCS1(String A, String B) {
		return LCS(A, B, A.length(), B.length());
	}

	private static int LCS(String A, String B, int n, int m) {
		if (n == 0 || m == 0) {
			return 0;
		}
		if (A.charAt(n - 1) == B.charAt(m - 1)) {
			return 1 + LCS(A, B, n - 1, m - 1);
		}
		return Math.max(LCS(A, B, n - 1, m), LCS(A, B, n, m - 1));
	}

	private static int LCS1(String A, String B, int n, int m) {
		if (n >= A.length() || m >= B.length()) {
			return 0;
		}
		if (A.charAt(n) == B.charAt(m)) {
			return 1 + LCS1(A, B, n + 1, m + 1);
		}
		return Math.max(LCS1(A, B, n + 1, m), LCS1(A, B, n, m + 1));
	}

}
