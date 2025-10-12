package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.List;

public class ZeroOneKnapsack {

    public static void main(String[] args) {
        // (1, 2, 3, 5), (1, 4, 7, 10), 8
        //v->(3, 2, 8, 3, 7, 10, 5), w->(4, 1, 5, 4, 3, 7, 4), 15

        //468,335,501,170,725,479,359,963,465,706,146,282,828,962,492,996,943,828,437,392,605,903,154,293,383,422,717,719,896,448,727,772,539,870,913,668,300,36,895,704,812,323
        //4,4,5,2,2,4,9,8,5,3,8,8,10,4,2,10,9,7,6,1,3,9,7,1,3,5,9,7,6,1,10,1,1,7,2,4,9,10,4,5,5,7
        //841

        //v->(12,20,15,6,10), wt->(3,6,5,2,4), 8
        //50, wt->(20, 10, 30, 40), val->(100, 60, 120, 150)
        ArrayList<Integer> A = new ArrayList<>(List.of(100, 60, 120, 150));//->values
        ArrayList<Integer> B = new ArrayList<>(List.of(20, 10, 30, 40));//->weights
        int capacity = 50;
        int items = B.size();
        System.out.println(getMaxValuev1(A, B, items, capacity));
        System.out.println(getMaxValuev2(A, B, items, capacity));
        System.out.println(getMaxValuev3(A, B, items, capacity));
        System.out.println(getMaxValuev4(A, B, items, capacity));
    }

    //working code and optimal solution
    private static int getMaxValuev4(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        int[][] arr = new int[items + 1][capacity + 1];
        for (int i = 1; i <= items; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - B.get(i - 1) >= 0) {
                    arr[i][j] = Math.max(arr[i - 1][j], A.get(i - 1) + arr[i - 1][j - B.get(i - 1)]);
                } else {
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }
        return arr[items][capacity];
    }

    private static int getMaxValuev2(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        Integer[][] dp = new Integer[items + 1][capacity + 1]; //store the maximum value that can be obtained with the ith items with jth capacity
        int x = getMaxValuev2x(A, B, items, capacity, dp);
        return x;
    }

    //working code, optimised with memoization
    private static int getMaxValuev2x(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity, Integer[][] dp) {
        if (dp[items][capacity] == null) {
            if (items == 0 || capacity == 0) {
                dp[items][capacity] = 0;
            } else {
                dp[items][capacity] = getMaxValuev2x(A, B, items - 1, capacity, dp); // skipping the item
                if (capacity - B.get(items - 1) >= 0) {
                    dp[items][capacity] = Math.max(dp[items][capacity],
                            getMaxValuev2x(A, B, items - 1, capacity - B.get(items - 1), dp) + A.get(items - 1));
                }
            }
        }
        return dp[items][capacity];
    }

    //working code, optimised with memoization
    private static int getMaxValuev2(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity, Integer[][] dp) {
        if (dp[items][capacity] == null) {
            if (items == 0 || capacity == 0) {
                dp[items][capacity] = 0;
            } else if (capacity - B.get(items - 1) >= 0) {
                dp[items][capacity] = Math.max(
                        getMaxValuev2(A, B, items - 1, capacity - B.get(items - 1), dp) + A.get(items - 1),
                        getMaxValuev2(A, B, items - 1, capacity, dp));
            } else {
                dp[items][capacity] = getMaxValuev2(A, B, items - 1, capacity, dp);
            }
        }
        return dp[items][capacity];
    }

    //working code but throws TLE
    private static int getMaxValuev1(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        if (capacity == 0 || items == 0) {
            return 0;
        }
        if (capacity - B.get(items - 1) >= 0) {
            return Math.max(
                    getMaxValuev1(A, B, items - 1, capacity - B.get(items - 1)) + A.get(items - 1),
                    getMaxValuev1(A, B, items - 1, capacity));
        }
        return getMaxValuev1(A, B, items - 1, capacity);
    }

    //working code but throws TLE
    private static int getMaxValuev3(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        if (capacity == 0 || items == 0) {
            return 0;
        }
        int ans = getMaxValuev3(A, B, items - 1, capacity); // skipping the item
        if (capacity - B.get(items - 1) >= 0) {
            return Math.max(
                    getMaxValuev3(A, B, items - 1, capacity - B.get(items - 1)) + A.get(items - 1),
                    ans);
        }
        return ans;
    }
}
