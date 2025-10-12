package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxMoneyRobbed {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(2, 7, 9, 3, 1));

        System.out.println(maxMoneyv1(A, A.size()));
        System.out.println(maxMoneyv2(A));
        System.out.println(maxMoneyv3(A));

        int n = 10;
        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);
        System.out.println(totalJumps1(arr, n));
        System.out.println(totalJumps2(n));
        System.out.println(totalJumps3(n));
    }

    private static int totalJumps3(int n) {
        int a = 0;
        int b = 1;
        int c = 1;
        for (int i = 3; i <= n; i++) {
            int d = 1 + Math.min(a, Math.min(b, c));
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    private static int totalJumps2(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = 1 + Math.min(arr[i - 1], Math.min(arr[i - 2], arr[i - 3]));
        }
        return arr[n];
    }

    private static int totalJumps1(int[] arr, int n) {
        if (arr[n] == -1) {
            if (n == 0) {
                return 0;
            }
            if (n == 1 || n == 2) {
                return 1;
            }
            arr[n] = 1
                    + Math.min(totalJumps1(arr, n - 1), Math.min(totalJumps1(arr, n - 2), totalJumps1(arr, n - 3)));
        }
        return arr[n];
    }

    private static int maxMoneyv1(List<Integer> A, int n) {
        if (n == 0) {
            return 0; //no money can be robbed from 0 houses
        }
        if (n == 1) {
            return A.get(0); //if only one house, money can be robbed from that house alone
        }
        return Math.max(
                maxMoneyv1(A, n - 1),
                maxMoneyv1(A, n - 2) + A.get(n - 1));
    }

    private static int maxMoneyv2(List<Integer> A) {
        int n = A.size();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return maxMoneyv2(A, n, dp);
    }

    // for every house, we have two choice->rob or not rob
    // maxMoneyv2()->the max amount of money that can be robbed from the first N houses
    private static int maxMoneyv2(List<Integer> A, int n, int[] dp) {
        if (dp[n] == -1) {
            if (n == 0) {
                dp[0] = 0;
            } else if (n == 1) {
                dp[1] = A.get(0);
            } else {
                dp[n] = Math.max(
                        maxMoneyv2(A, n - 1, dp),
                        maxMoneyv2(A, n - 2, dp) + A.get(n - 1));
            }
        }
        return dp[n];
    }

    private static int maxMoneyv3(List<Integer> A) {
        int n = A.size();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = A.get(0);
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A.get(i - 1));
        }
        return dp[n];
    }
}
