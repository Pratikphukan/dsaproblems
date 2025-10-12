package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class DungeonPrincess {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(-2, -3, 3)));
        A.add(new ArrayList<>(Arrays.asList(-5, -10, 1)));
        A.add(new ArrayList<>(Arrays.asList(10, 30, -5)));

//        A.add(new ArrayList<>(Arrays.asList(-100, -100, -100)));
//        A.add(new ArrayList<>(Arrays.asList(-100, -100, -100)));
//        A.add(new ArrayList<>(Arrays.asList(-100, -100, -100)));
        int rows = A.size();
        int cols = A.get(0).size();
        System.out.println(Math.abs(minInitialHealthRequiredv1(A, rows, cols)) + 1);
        System.out.println(Math.abs(minInitialHealthRequiredv2(A, rows, cols)) + 1);

        System.out.println(minInitialHealthRequiredv3(A));

        System.out.println(minInitialHealthRequiredv4(A));
    }

    private static int minInitialHealthRequiredv4(ArrayList<ArrayList<Integer>> A) {
        int n = A.size(), m = A.get(0).size();
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = Math.max(1, 1 - A.get(n - 1).get(m - 1)); //x+A[n][m] >= 1 => x >= 1-A[n][m]
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = Math.max(1, dp[i + 1][m - 1] - A.get(i).get(m - 1));
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = Math.max(1, dp[n - 1][i + 1] - A.get(n - 1).get(i));
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int res = Math.min(dp[i][j + 1], dp[i + 1][j]);
                dp[i][j] = Math.max(1, res - A.get(i).get(j));
            }
        }
        return dp[0][0];
    }

    //working code
    private static int minInitialHealthRequiredv3(ArrayList<ArrayList<Integer>> A) {
        int n = A.size();
        int m = A.get(0).size();

        int[][] t = new int[n][m];

        t[n - 1][m - 1] = Math.max(1, 1 - A.get(n - 1).get(m - 1));

        for (int i = n - 2; i >= 0; i--) {
            t[i][m - 1] = Math.max(1, t[i + 1][m - 1] - A.get(i).get(m - 1));
        }

        for (int i = m - 2; i >= 0; i--) {
            t[n - 1][i] = Math.max(1, t[n - 1][i + 1] - A.get(n - 1).get(i));
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {

                int res = Math.min(t[i][j + 1], t[i + 1][j]);

                t[i][j] = Math.max(1, res - A.get(i).get(j));
            }
        }
        return t[0][0];
    }

    private static int minInitialHealthRequiredv2(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        Integer[][] minHealthRequiredMatrix = new Integer[rows + 1][cols + 1];
        return minInitialHealthRequiredv2(A, rows, cols, minHealthRequiredMatrix);
    }

    private static int minInitialHealthRequiredv2(ArrayList<ArrayList<Integer>> A, int rows, int cols, Integer[][] minHealthRequiredMatrix) {
        if (minHealthRequiredMatrix[rows][cols] == null) {
            if (rows == 1 && cols == 1) {
                minHealthRequiredMatrix[rows][cols] = A.get(0).get(0);
            } else if (rows == 1 || cols == 1) {
                minHealthRequiredMatrix[rows][cols] = A.get(rows - 1).get(cols - 1) +
                        (rows == 1 ?
                                minInitialHealthRequiredv2(A, rows, cols - 1, minHealthRequiredMatrix) :
                                minInitialHealthRequiredv2(A, rows - 1, cols, minHealthRequiredMatrix));
            } else if (A.get(rows - 2).get(cols - 1) >= 0 && A.get(rows - 1).get(cols - 2) >= 0) {
                minHealthRequiredMatrix[rows][cols] = A.get(rows - 1).get(cols - 1) +
                        Math.min(minInitialHealthRequiredv2(A, rows - 1, cols, minHealthRequiredMatrix),
                                minInitialHealthRequiredv2(A, rows, cols - 1, minHealthRequiredMatrix));
            } else {
                minHealthRequiredMatrix[rows][cols] = A.get(rows - 1).get(cols - 1) +
                        Math.max(minInitialHealthRequiredv2(A, rows - 1, cols, minHealthRequiredMatrix),
                                minInitialHealthRequiredv2(A, rows, cols - 1, minHealthRequiredMatrix));
            }
        }
        return minHealthRequiredMatrix[rows][cols];
    }

    private static int minInitialHealthRequiredv1(ArrayList<ArrayList<Integer>> A, int rows, int cols) {
        if (rows == 1 && cols == 1) {
            return A.get(0).get(0);
        } else if (rows == 1 || cols == 1) {
            return A.get(rows - 1).get(cols - 1) +
                    minInitialHealthRequiredv1(A, rows == 1 ? rows : rows - 1, cols == 1 ? cols : cols - 1);
        } else if (A.get(rows - 2).get(cols - 1) >= 0 && A.get(rows - 1).get(cols - 2) >= 0) {
            return A.get(rows - 1).get(cols - 1) +
                    Math.min(
                            minInitialHealthRequiredv1(A, rows - 1, cols),
                            minInitialHealthRequiredv1(A, rows, cols - 1));
        }
        return A.get(rows - 1).get(cols - 1) +
                Math.max(
                        minInitialHealthRequiredv1(A, rows - 1, cols),
                        minInitialHealthRequiredv1(A, rows, cols - 1));
    }
}
