package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumPathInTriangle {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(8, 0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(4, 4, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(2, 2, 6, 0)));
        A.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        int rows = A.size();
        int cols = A.get(0).size();
        System.out.println(maximumPathInTrianglev3(A, rows, cols));
    }

    //working code
    private static Integer maximumPathInTrianglev3(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[][] maxSumPathForTriangle = new Integer[rows + 1][cols + 1];
        int maxSumPath = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                if (i == 1 && j == 1) {
                    maxSumPathForTriangle[i][j] = A.get(0).get(0);
                } else if (j == 1) {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(0) + maxSumPathForTriangle[i - 1][j];
                } else if (i == j) {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(j - 1) + maxSumPathForTriangle[i - 1][j - 1];
                } else {
                    maxSumPathForTriangle[i][j] = A.get(i - 1).get(j - 1) + Math.max(maxSumPathForTriangle[i - 1][j - 1], maxSumPathForTriangle[i - 1][j]);

                }
                maxSumPath = Math.max(maxSumPath, maxSumPathForTriangle[i][j]);
            }
        }
        return maxSumPath;
    }
}
