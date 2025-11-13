package com.dsaproblems.DSAProblems.leetcode;

public class BestTimetToBuySellStock {

    public static void main(String[] args) {
        //7, 6, 4, 3, 1
        //7, 1, 5, 3, 6, 4
        //1, 2, 3, 4, 5
        //4, 2, 8, 1, 10, 6, 11
        int[] prices = {1, 6, 2, 3, 4, 7};
        System.out.println(maxProfitv1(prices));
    }

    //the method is optimal: it runs in O(n) time and O(1) extra space
    private static int maxProfitv1(int[] prices) {
        if (prices.length < 2) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                int profit = prices[i] - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
