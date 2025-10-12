package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.Arrays;

public class LongestPalindromeSequence {

    public static void main(String[] args) {
        String A = "agbdba"; // "bebdeeedaddecebbbbbabebedc", "agbdba"
        StringBuilder sb = new StringBuilder(A);
        String B = sb.reverse().toString();
        // System.out.println(LCS(A, B, A.length(), B.length()));
        System.out.println(lps(A, 0, A.length() - 1));

        Integer[][] arr = new Integer[A.length()][A.length()];
        System.out.println(lpsTopDown(A, 0, A.length() - 1, arr));
        System.out.println(Arrays.deepToString(arr));

        System.out.println(lpsBottomUp(A));
    }

    private static int lpsBottomUp(String A) {
        int n = A.length();
        int[][] arr = new int[n][n];

        for (int i = 0; i < A.length(); i++) {
            arr[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            // for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                if (A.charAt(i) == A.charAt(j)) {
                    arr[i][j] = 2 + arr[i + 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(arr));
        return arr[0][n - 1];
    }

    //working solution
    private static int lpsTopDown(String A, int s, int e, Integer[][] arr) {
        if (s > e) {
            return 0;
        }
        if (s == e) {
            return 1;
        }
        if (arr[s][e] == null) { // arr[s][e] length of lps in sub string from s to e
            if (A.charAt(s) == A.charAt(e)) {
                arr[s][e] = 2 + lpsTopDown(A, s + 1, e - 1, arr);
            } else {
                arr[s][e] = Math.max(lpsTopDown(A, s + 1, e, arr), lpsTopDown(A, s, e - 1, arr));
            }
        }
        return arr[s][e];
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

    private static int lps(String A, int s, int e) {
        if (s > e) {
            return 0;
        }
        if (s == e) {
            return 1;
        }
        if (A.charAt(s) == A.charAt(e)) {
            return 2 + lps(A, s + 1, e - 1);
        }
        return Math.max(lps(A, s + 1, e), lps(A, s, e - 1));
    }

}
