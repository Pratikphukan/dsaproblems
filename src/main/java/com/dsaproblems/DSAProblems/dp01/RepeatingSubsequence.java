package com.dsaproblems.DSAProblems.dp01;

public class RepeatingSubsequence {

    public static void main(String[] args) {
        String A = "abab";
        System.out.println(checkRepeatingSubsequencev2(A));
        System.out.println(checkRepeatingSubsequencev3(A) > 1);
    }

    private static int checkRepeatingSubsequencev3(String A) {
        int n = A.length();
        if (n == 0) {
            return 0;
        }
        int[][] arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == A.charAt(j - 1) && i != j) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        return arr[n][n];
    }

    private static boolean checkRepeatingSubsequencev2(String A) {
        int n = A.length();
        Integer[][] dp = new Integer[n + 1][n + 1];
        return checkRepeatingSubsequencev2(A, n, n, dp) > 1;
    }

    private static Integer checkRepeatingSubsequencev2(String A, int i, int j, Integer[][] dp) {
        if (dp[i][j] == null) {
            if (i == 0 || j == 0) {
                dp[i][j] = 0;
            } else if (A.charAt(i - 1) == A.charAt(j - 1) && i != j) {
                dp[i][j] = 1 + checkRepeatingSubsequencev2(A, i - 1, j - 1, dp);
            } else {
                dp[i][j] = Math.max(
                        checkRepeatingSubsequencev2(A, i - 1, j, dp),
                        checkRepeatingSubsequencev2(A, i, j - 1, dp));
            }
        }
        return dp[i][j];
    }
}
