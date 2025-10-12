package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumeriaAndSubsequence {

    public static void main(String[] args) {
        //9, -20, -11, -8, -4, 2, -12, 14, 1, -18
        //1, 2, 3
        //1,2,2
        //8, 10, 6, 11, 1, 16, 8
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(findSumOfWidthsv1(input));

    }

    private static long findSumOfWidthsv1(ArrayList<Integer> A) {

        ArrayList<Integer> result = new ArrayList<>();
        Collections.sort(A); // sort input for consistent subset order
        ArrayList<Integer> row = new ArrayList<>();
        backtrackingx(0, result, A, row, Integer.MAX_VALUE, Integer.MIN_VALUE);
        //backtracking(0, result, A, row);
        int MOD = 1_000_000_000;
        long sum = 0L;
        for (int num : result) {
            sum += num;
        }
        return sum % MOD;
    }

    private static void backtracking(int start, ArrayList<Integer> result, ArrayList<Integer> A, ArrayList<Integer> row) {
        if (start == A.size()) {
            if (row.size() > 1) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int num : row) {
                    min = Math.min(min, num);
                    max = Math.max(max, num);
                }
                result.add(max - min);
            }
            return;
        }

        backtracking(start + 1, result, A, row); // without current element
        row.add(A.get(start)); // include current element
        backtracking(start + 1, result, A, row);
        row.remove(row.size() - 1); // backtrack

    }

    private static void backtrackingx(int start, ArrayList<Integer> result, ArrayList<Integer> A, ArrayList<Integer> row, int currMin, int currMax) {

        if (start == A.size()) {
            if (row.size() > 1) {
                result.add(currMax - currMin);
            }
            return;
        }

        // Exclude current element
        backtrackingx(start + 1, result, A, row, currMin, currMax);

        // Include current element
        int val = A.get(start);
        row.add(val);
        int newMin = row.size() == 1 ? val : Math.min(currMin, val);
        int newMax = row.size() == 1 ? val : Math.max(currMax, val);
        backtrackingx(start + 1, result, A, row, newMin, newMax);
        row.remove(row.size() - 1); // backtrack
    }
}
