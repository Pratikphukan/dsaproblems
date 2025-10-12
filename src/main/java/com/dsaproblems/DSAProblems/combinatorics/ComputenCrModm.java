package com.dsaproblems.DSAProblems.combinatorics;

public class ComputenCrModm {

    public static void main(String[] args) {
        int A = 5;
        int B = 2;
        int C = 13;
        System.out.println(compute_nCr_mod_mv1(A, B, C));
        System.out.println(compute_nCr_mod_mv2(A, B, C));
    }

    private static int compute_nCr_mod_mv2(int A, int B, int C) {
        B = Math.min(B, A - B);
        int[] dp = new int[B + 1];
        if (B == 0 || A == 0 || A == 1) return 1;
        if (B == 1) return A % C;
        if (C == 1) return 0;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= B; i++) {
            dp[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % C;
            }
        }
        for (int i = B + 1; i <= A; i++) {
            for (int j = B; j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % C;
            }
        }
        return dp[B];
    }

    private static int compute_nCr_mod_mv1(int A, int B, int C) {
        int[][] x = new int[A + 1][B + 1];
        for (int i = 0; i <= A; i++) {
            for (int j = 0; j <= Math.min(i, B); j++) {
                if (j == 0 || j == i) {
                    x[i][j] = 1;
                } else {
                    x[i][j] = (x[i - 1][j - 1] + x[i - 1][j]) % C;
                }
            }
        }
        return (x[A][B]) % C;
    }
}
