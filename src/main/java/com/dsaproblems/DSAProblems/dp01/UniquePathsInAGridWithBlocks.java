package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class UniquePathsInAGridWithBlocks {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
//        A.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
//        A.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
//        A.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
        A.add(new ArrayList<>(Arrays.asList(1, 0)));
        int rows = A.size();
        int cols = A.get(0).size();
        System.out.println(noOfUniquePathsv1(A, rows, cols));
        System.out.println(noOfUniquePathsv2(A, rows, cols));
        System.out.println(noOfUniquePathsv3(A, rows, cols));
    }

    //working code
    private static int noOfUniquePathsv3(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        int[][] uniquePathsForMXNMatrix = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (A.get(i - 1).get(j - 1) == 0) {
                    if (i == 1 && j == 1) {
                        uniquePathsForMXNMatrix[i][j] = 1;
                    } else if (i == 1 || j == 1) {
                        uniquePathsForMXNMatrix[i][j] = i == 1 ? uniquePathsForMXNMatrix[i][j - 1] : uniquePathsForMXNMatrix[i - 1][j];
                    } else {
                        uniquePathsForMXNMatrix[i][j] = uniquePathsForMXNMatrix[i - 1][j] + uniquePathsForMXNMatrix[i][j - 1];
                    }
                } else {
                    uniquePathsForMXNMatrix[i][j] = 0;
                }
            }
        }
        return uniquePathsForMXNMatrix[rows][cols];
    }

    private static int noOfUniquePathsv2(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        int[][] uniquePaths = new int[rows + 1][cols + 1];
        return noOfUniquePathsv2(A, rows, cols, uniquePaths);
    }

    //working code
    private static int noOfUniquePathsv2(ArrayList<ArrayList<Integer>> A, int rows, int cols, int[][] uniquePaths) {
        if (A.get(rows - 1).get(cols - 1) == 0) {
            if (rows == 1 && cols == 1) {
                uniquePaths[rows][cols] = 1;
            } else if (rows == 1 || cols == 1) {
                uniquePaths[rows][cols] = noOfUniquePathsv2(A, rows == 1 ? rows : rows - 1, cols == 1 ? cols : cols - 1, uniquePaths);
            } else {
                uniquePaths[rows][cols] = noOfUniquePathsv2(A, rows - 1, cols, uniquePaths) + noOfUniquePathsv2(A, rows, cols - 1, uniquePaths);
            }
        } else {
            uniquePaths[rows][cols] = 0;
        }
        return uniquePaths[rows][cols];
    }

    //working code
    private static int noOfUniquePathsv1(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        if (A.get(rows - 1).get(cols - 1) == 0) {
            if (rows == 1 && cols == 1) {
                return 1;
            } else if (rows == 1 || cols == 1) {
                return noOfUniquePathsv1(A, rows == 1 ? rows : rows - 1, cols == 1 ? cols : cols - 1);
            }
            return noOfUniquePathsv1(A, rows - 1, cols) + noOfUniquePathsv1(A, rows, cols - 1);
        }
        return 0;
    }
}
