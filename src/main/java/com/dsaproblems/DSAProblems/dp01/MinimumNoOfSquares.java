package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumNoOfSquares {

    public static void main(String[] args) {
        System.out.println(solveRecursive(6));  // Output: 3 (4+1+1)
        System.out.println(solve(6)); // Output: 3 (4+4+4)
        System.out.println(solve(13)); // Output: 2 (9+4)


        System.out.println(getMinCount(6L, 6L, 1L));
    }

    public static int solve(int A) {
        int[] dp = new int[A + 1]; //dp[i] represents the minimum number of perfect squares needed to sum to i.
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= A; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        return dp[A];
    }

    public static int solveRecursive(int A) {
        if (A == 0) return 0;
        int min = Integer.MAX_VALUE;
        for (int j = 1; j * j <= A; j++) {
            min = Math.min(min, 1 + solveRecursive(A - j * j));
        }
        return min;
    }

//    private static int getMinCount(int size, int A, int x) {
//        if (x == size) {
//            return 0;
//        }
//        int ans = getMinCount(size, A, x + 1); // skipping the item
//        if ((A - x * x) >= 0) {
//            return Math.min(
//                    1 + getMinCount(size, A - x * x, x), //including the item and don't move to the next item
//                    ans);
//        }
//        return ans;
//    }

    public static long getMinCount(long size, long A, long x) {
        if (A == 0) return 0;
        if (A < 0 || x == size) return 10001L;
        long ans = getMinCount(size, A, x + 1); // skip current square
        if ((A - x * x) >= 0) {
            ans = Math.min(ans, 1 + getMinCount(size, A - x * x, x)); // include current square
        }
        return ans;
    }
}
