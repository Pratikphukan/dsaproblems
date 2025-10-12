package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TusharBirthdayParty {

    public static void main(String[] args) {
//        ArrayList<Integer> A = new ArrayList<>(List.of(2, 4, 6));//->eating capacity
//        ArrayList<Integer> B = new ArrayList<>(List.of(2, 1, 3));//->filling capacity
//        ArrayList<Integer> C = new ArrayList<>(List.of(2, 5, 3));//->cost of each item
        ArrayList<Integer> A = new ArrayList<>(List.of(2, 3, 1, 5, 4));//->eating capacity
        ArrayList<Integer> B = new ArrayList<>(List.of(3, 2, 4, 1));//->filling capacity
        ArrayList<Integer> C = new ArrayList<>(List.of(1, 2, 5, 10));//->cost of each item
        int items = B.size();
        System.out.println(getMinCostv1(A, B, C));

        System.out.println(getMinCostv2(A, B, C));

        System.out.println(getMinCostv3(A, B, C));
        //System.out.println(getMaxValuev2(A, B, items, capacity));
    }

    //working code but still not optimal better than v1
    private static int getMinCostv2(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int items = B.size();
        int totalMinCost = 0;
        for (Integer capacity : A) {
            totalMinCost += getMinCostv2(C, B, items, capacity);
        }
        return totalMinCost;
    }

    private static int getMinCostv2(ArrayList<Integer> C, ArrayList<Integer> B, int items, Integer capacity) {
        Integer[][] dp = new Integer[items + 1][capacity + 1];
        return getMinCostv2(C, B, items, capacity, dp);
    }

    private static int getMinCostv2(ArrayList<Integer> C, ArrayList<Integer> B, int items, int capacity, Integer[][] dp) {
        if (dp[items][capacity] != null) return dp[items][capacity];

        if (capacity == 0) return dp[items][capacity] = 0;
        if (items == 0) return dp[items][capacity] = Integer.MAX_VALUE;

        int ans = getMinCostv2(C, B, items - 1, capacity, dp); // skip the item
        if (capacity >= B.get(items - 1)) {
            int include = getMinCostv2(C, B, items, capacity - B.get(items - 1), dp);
            if (include != Integer.MAX_VALUE) {
                ans = Math.min(ans, include + C.get(items - 1));
            }
        }
        return dp[items][capacity] = ans;
    }

    //working code but not optimal
    private static int getMinCostv1(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int items = B.size();
        int totalMinCost = 0;
        for (Integer capacity : A) {
            totalMinCost += getMinCostv1(C, B, items, capacity);
        }
        return totalMinCost;
    }

    private static int getMinCostv1(ArrayList<Integer> C, ArrayList<Integer> B, int items, Integer capacity) {
        if (capacity == 0) {
            return 0;
        }
        if (items == 0) {
            return Integer.MAX_VALUE;
        }
        int ans = getMinCostv1(C, B, items - 1, capacity); // skip the item
        if (capacity - B.get(items - 1) >= 0) {
            int include = getMinCostv1(C, B, items, capacity - B.get(items - 1));
            if (include != Integer.MAX_VALUE) {
                ans = Math.min(ans, include + C.get(items - 1));
            }
        }
        return ans;
    }

    public int solve(final int[] A, final int[] B, final int[] C) {
        int maxEatingCapacity = 0;
        for (int a : A) {
            maxEatingCapacity = Math.max(maxEatingCapacity, a);
        }

        int[] dp = new int[maxEatingCapacity + 1];
        for (int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;

        for (int i = 0; i < B.length; i++) {
            if (B[i] > maxEatingCapacity) continue;
            for (int j = 1; j <= maxEatingCapacity; j++) {
                if (j >= B[i] && dp[j - B[i]] < Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], C[i] + dp[j - B[i]]);
            }
        }
        int ans = 0;
        for (int a : A) ans += dp[a];

        return ans;
    }

    //working code and optimal solution
    public static int getMinCostv3(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n = Collections.max(A);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < B.size(); j++) {
                if (B.get(j) <= i) {
                    dp[i] = Math.min(dp[i], C.get(j) + dp[i - B.get(j)]);
                }
            }
        }

        int ans = 0;
        for (int capacity : A) {
            ans += dp[capacity];
        }
        return ans;
    }
}
