package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.List;

public class UnboundedKnapsack {

    public static void main(String[] args) {
        //(20, 13, 10, 40), (100, 66, 40, 150)
        ArrayList<Integer> A = new ArrayList<>(List.of(100, 66, 40, 150));//->values
        ArrayList<Integer> B = new ArrayList<>(List.of(20, 13, 10, 40));//->weights
        int capacity = 50;
        int items = B.size();
        System.out.println(getMaxValuev1(A, B, items, capacity));
        System.out.println(getMaxValuev2(A, B, items, capacity));
        System.out.println(getMaxValuev3(A, B, items, capacity));
        System.out.println(getMaxValuev4(A, B, items, capacity));
        System.out.println(getMaxValuev5(A, B, items, capacity));
        System.out.println(getMinValuev4(A, B, items, capacity));
    }

    //working code and optimal solution
    private static int getMaxValuev5(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        int[][] arr = new int[items + 1][capacity + 1];
        for (int i = 1; i <= items; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - B.get(i - 1) >= 0) {
                    arr[i][j] = Math.max(arr[i - 1][j], A.get(i - 1) + arr[i][j - B.get(i - 1)]);
                } else {
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }
        return arr[items][capacity];
    }

    //working code and optimal solution
    private static int getMaxValuev2(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        Integer[][] dp = new Integer[items + 1][capacity + 1];
        return getMaxValuev2(A, B, items, capacity, dp);
    }

    private static int getMaxValuev2(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity, Integer[][] dp) {
        if (dp[items][capacity] == null) {
            if (items == 0 || capacity == 0) {
                dp[items][capacity] = 0;
            } else {
                dp[items][capacity] = getMaxValuev2(A, B, items - 1, capacity, dp);
                if (capacity - B.get(items - 1) >= 0) {
                    dp[items][capacity] = Math.max(
                            dp[items][capacity],
                            getMaxValuev2(A, B, items, capacity - B.get(items - 1), dp) + A.get(items - 1));
                }
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
                    getMaxValuev1(A, B, items, capacity - B.get(items - 1)) + A.get(items - 1), //including the item and don't move to the next item
                    getMaxValuev1(A, B, items - 1, capacity));
        }
        return getMaxValuev1(A, B, items - 1, capacity); //skipping the item
    }

    //working code but throws TLE
    private static int getMaxValuev4(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        if (capacity == 0 || items == 0) {
            return 0;
        }
        int ans = getMaxValuev4(A, B, items - 1, capacity); // skipping the item
        if (capacity - B.get(items - 1) >= 0) {
            return Math.max(
                    getMaxValuev4(A, B, items, capacity - B.get(items - 1)) + A.get(items - 1), //including the item and don't move to the next item
                    ans);
        }
        return ans;
    }

    private static int getMinValuev4(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        if (capacity == 0) {
            return 0;
        }
        if (items == 0) {
            return Integer.MAX_VALUE;
        }
        int ans = getMinValuev4(A, B, items - 1, capacity); // skip the item
        if (capacity - B.get(items - 1) >= 0) {
            int include = getMinValuev4(A, B, items, capacity - B.get(items - 1));
            if (include != Integer.MAX_VALUE) {
                ans = Math.min(ans, include + A.get(items - 1));
            }
        }
        return ans;
    }

    //0-1 Knapsack solution, different solution to understand the problem
    private static int getMaxValuev3(ArrayList<Integer> A, ArrayList<Integer> B, int items, int capacity) {
        if (capacity == 0 || items == 0) {
            return 0;
        }
        int ans = getMaxValuev3(A, B, items - 1, capacity); // skipping the item
        if (capacity - B.get(items - 1) >= 0) {
            ans = Math.max(ans,
                    getMaxValuev3(A, B, items - 1, capacity - B.get(items - 1)) + A.get(items - 1)); // including the item and moving to the next item
        }
        return ans;
    }
}
