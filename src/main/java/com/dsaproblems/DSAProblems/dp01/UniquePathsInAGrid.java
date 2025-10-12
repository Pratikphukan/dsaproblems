package com.dsaproblems.DSAProblems.dp01;

public class UniquePathsInAGrid {

    public static void main(String[] args) {
        int rows = 5;
        int cols = 7;
        System.out.println(noOfUniquePathsv1(rows, cols));
        System.out.println(noOfUniquePathsv2(rows, cols));
        System.out.println(noOfUniquePathsv3(rows, cols));
        System.out.println(noOfUniquePathsv4(rows - 1, cols - 1));
    }

    private static int noOfUniquePathsv3(int rows, int cols) {
        int[][] uniquePaths = new int[rows + 1][cols + 1];
        uniquePaths[1][1] = 0; // Starting point
        int i, j;
        for (i = 2; i <= rows; i++)
            uniquePaths[i][1] = 1;
        for (i = 2; i <= cols; i++)
            uniquePaths[1][i] = 1;
        for (i = 2; i <= rows; i++) {
            for (j = 2; j <= cols; j++) {
                uniquePaths[i][j] = uniquePaths[i - 1][j] + uniquePaths[i][j - 1];
            }
        }
        return uniquePaths[rows][cols];
    }

    private static int noOfUniquePathsv2(int rows, int cols) {
        int[][] uniquePaths = new int[rows + 1][cols + 1];
        return noOfUniquePathsv2(rows, cols, uniquePaths);
    }

    private static int noOfUniquePathsv2(int rows, int cols, int[][] uniquePaths) {
        if (uniquePaths[rows][cols] == 0) {
            if (rows == 1 || cols == 1)
                uniquePaths[rows][cols] = 1;
            else
                uniquePaths[rows][cols] = noOfUniquePathsv2(rows - 1, cols, uniquePaths) + noOfUniquePathsv2(rows, cols - 1, uniquePaths);
        }
        return uniquePaths[rows][cols];
    }

    private static int noOfUniquePathsv1(int rows, int cols) {
        if (rows == 1 || cols == 1) {
            return 1;
        }
        return noOfUniquePathsv1(rows - 1, cols) + noOfUniquePathsv1(rows, cols - 1);
    }

    private static int noOfUniquePathsv4(int rows, int cols) {
        if (rows == 0 || cols == 0) {
            return 1;
        }
        return noOfUniquePathsv4(rows - 1, cols) + noOfUniquePathsv4(rows, cols - 1);
    }
}
