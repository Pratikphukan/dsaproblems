package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumPathInTriangle {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(8, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(4, 4, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(2, 2, 6, 0)));
        A.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        int rows = A.size();
        int cols = A.get(rows - 1).size();
        System.out.println(minimumPathInTrianglev3(A, rows, cols));
        System.out.println(minimumTotal(A));
    }

    public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();
        if (n == 0 || a.get(0).isEmpty()) return Integer.MAX_VALUE;
        int[] dp = new int[n];

        dp[0] = a.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + a.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + a.get(i).get(j);
            }
            dp[0] = dp[0] + a.get(i).get(0);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    private static int minimumPathInTrianglev3(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[][] maxSumPathForTriangle = new Integer[rows + 1][cols + 1];
        int minSumPath = Integer.MAX_VALUE;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == 1 && j == 1) {
                    maxSumPathForTriangle[i][j] = A.get(0).get(0);
                } else if (j == 1) {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(0) + maxSumPathForTriangle[i - 1][j];
                } else if (i == j) {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(j - 1) + maxSumPathForTriangle[i - 1][j - 1];
                } else {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(j - 1) + Math.min(maxSumPathForTriangle[i - 1][j - 1], maxSumPathForTriangle[i - 1][j]);
                }
                if (i == rows)
                    minSumPath = Math.min(minSumPath, maxSumPathForTriangle[i][j]);
            }
        }
        return minSumPath;
    }
}
