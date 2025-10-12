package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZeroOneKnapsack {

    public static void main(String[] args) {
        // wt->(1, 2, 3, 5), (1, 4, 7, 10), 8
        // wt->(20, 10, 30, 40), (100, 60, 120, 150), 50
        // wt->(4, 1, 5, 4, 3, 7, 4), (3, 2, 8, 3, 7, 10, 5), 15
        ArrayList<Integer> wt = new ArrayList<>(List.of(4, 1, 5, 4, 3, 7, 4));
        ArrayList<Integer> pr = new ArrayList<>(List.of(3, 2, 8, 3, 7, 10, 5));
        int capacity = 15;
        int items = wt.size();

        System.out.println(getMaxProfit1(pr, wt, items, capacity));
        Integer[][] arr = new Integer[items + 1][capacity + 1];
        System.out.println(getMaxProfit(pr, wt, capacity));
        System.out.println(getMaxProfitInUnBoundedKnapsack(pr, wt, capacity));
    }

    private static int getMaxProfit1(ArrayList<Integer> p, ArrayList<Integer> w, int N, int C) {
        if (N == 0 || C == 0) {
            return 0;
        }
        int ans = getMaxProfit1(p, w, N - 1, C); // for skipping
        if (w.get(N - 1) <= C) {
            ans = Math.max(ans, getMaxProfit1(p, w, N - 1, C - w.get(N - 1)) + p.get(N - 1));
        }
        return ans;
    }

    private static int getMaxProfitInUnBoundedKnapsack(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int[][] arr = new int[B.size()][C + 1];
        for (int i = 0; i < B.size(); i++) {
            for (int j = 1; j <= C; j++) {

                int includingCurrentWeightProfit = 0;
                int excludingCurrentWeightProfit = 0;

                if (B.get(i) <= j) {
                    includingCurrentWeightProfit = A.get(i) + arr[i][j - B.get(i)];
                }

                if (i > 0) {
                    excludingCurrentWeightProfit = arr[i - 1][j];
                }

                arr[i][j] = Math.max(includingCurrentWeightProfit, excludingCurrentWeightProfit);
            }
        }
        System.out.println(Arrays.deepToString(arr));
        return arr[B.size() - 1][C];
    }

    // A->pr, B->wt
    private static int getMaxProfit(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int[][] arr = new int[B.size()][C + 1];
        for (int i = 0; i <= C; i++) {
            if (B.get(0) <= i) {
                arr[0][i] = A.get(0);
            }
        }
        System.out.println(Arrays.deepToString(arr));
        for (int i = 1; i < B.size(); i++) {
            for (int j = 1; j <= C; j++) {
                int includingProfit = 0;
                if (B.get(i) <= j) {
                    includingProfit = A.get(i) + arr[i - 1][j - B.get(i)];
                }
                arr[i][j] = Math.max(arr[i - 1][j], includingProfit);
            }
        }
        System.out.println(Arrays.deepToString(arr));
        return arr[B.size() - 1][C];
    }

}
