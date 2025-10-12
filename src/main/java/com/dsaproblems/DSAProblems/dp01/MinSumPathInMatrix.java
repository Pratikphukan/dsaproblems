package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class MinSumPathInMatrix {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(1, 3, 2)));
        A.add(new ArrayList<>(Arrays.asList(4, 3, 1)));
        A.add(new ArrayList<>(Arrays.asList(5, 6, 1)));
        int rows = A.size();
        int cols = A.get(0).size();
        System.out.println(minSumPathInMatrixv1(A, rows, cols));
        System.out.println(minSumPathInMatrixv2(A, rows, cols));
        System.out.println(minSumPathInMatrixv3(A, rows, cols));
        System.out.println(minSumPathInMatrixv4(A, rows, cols));
    }

    private static int minSumPathInMatrixv4(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[] minSumPathForMXNMatrix = new Integer[cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (i == 1 && j == 1) {
                    minSumPathForMXNMatrix[j] = A.get(0).get(0);
                } else if (i == 1 || j == 1) {
                    minSumPathForMXNMatrix[j] = i == 1 ?
                            A.get(0).get(j - 1) + minSumPathForMXNMatrix[j - 1] :
                            A.get(i - 1).get(0) + minSumPathForMXNMatrix[j];
                } else {
                    minSumPathForMXNMatrix[j] = A.get(i - 1).get(j - 1) + Math.min(minSumPathForMXNMatrix[j], minSumPathForMXNMatrix[j - 1]);
                }
            }
        }
        return minSumPathForMXNMatrix[cols];
    }

    //working code
    private static Integer minSumPathInMatrixv3(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[][] minSumPathForMXNMatrix = new Integer[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (i == 1 && j == 1) {
                    minSumPathForMXNMatrix[i][j] = A.get(0).get(0);
                } else if (i == 1 || j == 1) {
                    minSumPathForMXNMatrix[i][j] = i == 1 ?
                            A.get(0).get(j - 1) + minSumPathForMXNMatrix[i][j - 1] :
                            A.get(i - 1).get(0) + minSumPathForMXNMatrix[i - 1][j];
                } else {
                    minSumPathForMXNMatrix[i][j] = A.get(i - 1).get(j - 1) + Math.min(minSumPathForMXNMatrix[i - 1][j], minSumPathForMXNMatrix[i][j - 1]);
                }
            }
        }
        return minSumPathForMXNMatrix[rows][cols];
    }

    private static int minSumPathInMatrixv2(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[][] minSumPathForMXNMatrix = new Integer[rows + 1][cols + 1];
        return minSumPathInMatrixv2(A, rows, cols, minSumPathForMXNMatrix);
    }

    //working code
    private static int minSumPathInMatrixv2(ArrayList<ArrayList<Integer>> A, int rows, int cols, Integer[][] minSumPathForMXNMatrix) {
        if (minSumPathForMXNMatrix[rows][cols] == null) {
            if (rows == 1 && cols == 1) {
                minSumPathForMXNMatrix[rows][cols] = A.get(0).get(0);
            } else if (rows == 1 || cols == 1) {
                minSumPathForMXNMatrix[rows][cols] = A.get(rows - 1).get(cols - 1) +
                        (rows == 1 ?
                                minSumPathInMatrixv2(A, rows, cols - 1, minSumPathForMXNMatrix) :
                                minSumPathInMatrixv2(A, rows - 1, cols, minSumPathForMXNMatrix));
            } else {
                minSumPathForMXNMatrix[rows][cols] = A.get(rows - 1).get(cols - 1) +
                        Math.min(minSumPathInMatrixv2(A, rows - 1, cols, minSumPathForMXNMatrix),
                                minSumPathInMatrixv2(A, rows, cols - 1, minSumPathForMXNMatrix));
            }
        }
        return minSumPathForMXNMatrix[rows][cols];
    }

    //working code, but gives TLE for large inputs
    private static Integer minSumPathInMatrixv1(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        if (rows == 1 && cols == 1) {
            return A.get(0).get(0);
        } else if (rows == 1 || cols == 1) {
            return A.get(rows - 1).get(cols - 1) + minSumPathInMatrixv1(A, rows == 1 ? rows : rows - 1, cols == 1 ? cols : cols - 1);
        }
        return A.get(rows - 1).get(cols - 1) + Math.min(minSumPathInMatrixv1(A, rows - 1, cols), minSumPathInMatrixv1(A, rows, cols - 1));
    }
}
