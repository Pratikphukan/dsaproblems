package com.dsaproblems.DSAProblems.dp01;

import java.util.ArrayList;
import java.util.List;

/*
Given a rod of length n inches and an array price[], where price[i] denotes the value of a piece of length i.
Your task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.

Note: n = size of price, and price[] is 1-indexed array.

Example:

Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
Output: 22
Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.
 */
public class RodCutting {

    public static void main(String[] args) {
        ArrayList<Integer> prices = new ArrayList<>(List.of(1, 5, 8, 9, 10, 17, 17, 20));
        int length = prices.size();
        System.out.println(getMaxValuev1(prices, length));
    }

    private static int getMaxValuev1(ArrayList<Integer> prices, int n) {
        int[] dp = new int[n + 1]; // dp[i] = best value for length i, dp[i] stores the maximum value obtainable for a rod of length i
        //For each possible rod length from 1 to n, it tries every possible first cut (cut from 1 to len)
        for (int len = 1; len <= n; len++) {
            int best = Integer.MIN_VALUE;
            for (int cut = 1; cut <= len; cut++) {
                best = Math.max(best, prices.get(cut - 1) + dp[len - cut]); //For each cut, it adds the price of the cut piece (prices.get(cut - 1)) to the best value for the remaining rod (dp[len - cut])
            }
            dp[len] = best;
        }
        return dp[n];
    }
}
