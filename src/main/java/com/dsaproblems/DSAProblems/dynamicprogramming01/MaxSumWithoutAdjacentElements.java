package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumWithoutAdjacentElements {

    public static void main(String[] args) {
        // 16,5,54,55,36,82,61,77,66,61|31,30,36,70,9,37,1,11,68,14
        // 4, 8, 9, 3, 6|3, 1, 10, 7, 4
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(16, 5, 54, 55, 36, 82, 61, 77, 66, 61)));
        A.add(new ArrayList<>(Arrays.asList(31, 30, 36, 70, 9, 37, 1, 11, 68, 14)));
        System.out.println(maxPossibleSumv2(A));
        System.out.println(maxPossibleSumv1(A));

        List<List<Integer>> B = new ArrayList<>();
        B.add(new ArrayList<>(Arrays.asList(4, 8, 9, 3, 6)));
        B.add(new ArrayList<>(Arrays.asList(3, 1, 10, 7, 4)));
        B.add(new ArrayList<>(Arrays.asList(8, 23, 16, 11, 32)));
        B.add(new ArrayList<>(Arrays.asList(16, 34, 45, 31, 68)));
        B.add(new ArrayList<>(Arrays.asList(1, 9, 40, 5, 65)));
        System.out.println(maxPossibleSum1(B));
    }

    //O(N) Time, O(1) Space
    private static int maxPossibleSumv2(ArrayList<ArrayList<Integer>> A) {
        int cols = A.get(0).size();
        int prev1 = Math.max(A.get(0).get(0), A.get(1).get(0)); // Max sum up to 1 column before
        int prev2 = 0; // Max sum up to 2 columns before
        for (int i = 1; i < cols; i++) {
            int colMax = Math.max(A.get(0).get(i), A.get(1).get(i));
            int current = Math.max(prev1, colMax + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    private static int maxPossibleSum1(List<List<Integer>> input) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> intermediateArray = null;
        for (int i = 0; i < input.size(); i++) {
            intermediateArray = new ArrayList<>();
            for (int j = 0; j < input.get(i).size(); j++) {
                if (i == 0) {
                    intermediateArray.add(Math.max(input.get(i).get(j), input.get(i + 1).get(j)));
                } else if (i == input.size() - 1) {
                    intermediateArray.add(Math.max(input.get(i).get(j), input.get(i - 1).get(j)));
                } else {
                    intermediateArray.add(
                            Math.max(input.get(i).get(j), Math.max(input.get(i + 1).get(j), input.get(i - 1).get(j))));
                }
            }
            ans.add(intermediateArray);
        }
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                if (j == 0) {
                    ans.get(i).set(j, Math.max(ans.get(i).get(j), ans.get(i).get(j + 1)));
                } else if (j == ans.get(i).size() - 1) {
                    ans.get(i).set(j, Math.max(ans.get(i).get(j), ans.get(i).get(j - 1)));
                } else {
                    ans.get(i).set(j,
                            Math.max(ans.get(i).get(j), Math.max(ans.get(i).get(j + 1), ans.get(i).get(j - 1))));
                }
            }
        }
        return 0;
    }

    private static Integer maxPossibleSum2(ArrayList<ArrayList<Integer>> input) {
        List<Integer> first = input.get(0);
        List<Integer> second = input.get(1);
        List<Integer> dp = new ArrayList<>(); // denotes the maximum sum till the ith index
        int len = first.size();
        dp.add(Math.max(first.get(0), second.get(0)));
        if (len == 1) {
            return dp.get(0);
        }
        dp.add(Math.max(dp.get(0), Math.max(first.get(1), second.get(1))));
        if (len == 2) {
            return dp.get(1);
        }
        for (int i = 2; i < len; i++) {
            dp.add(Math.max(first.get(i), second.get(i)) + dp.get(i - 2));
            dp.set(i, Math.max(dp.get(i), dp.get(i - 1)));
        }
        return dp.get(len - 1);
    }

    //working code
    //Time Complexity (TC): O(N) where N is the number of columns.
    //It traverses the columns twice (once to find column maximums, and once to compute DP values)
    //Space Complexity (SC): O(N) auxiliary space to store the column maximums/DP values in the ans list
    private static int maxPossibleSumv1(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < A.get(0).size(); i++) {
            ans.add(Math.max(A.get(0).get(i), A.get(1).get(i)));
        }
        for (int i = 1; i < ans.size(); i++) { // max sum we get till ith index
            if (i == 1) {
                ans.set(i, Math.max(ans.get(1), ans.get(0)));
            } else {
                ans.set(i, Math.max(ans.get(i - 1), ans.get(i - 2) + ans.get(i)));
            }
        }
        return ans.get(ans.size() - 1);
    }

    public int adjacent(int[][] A) {
        if (A == null || A.length == 0)
            return 0;
        int prevColOne = 0;
        int prevColTwo = 0;
        int colOne = 0;
        int colTwo = 0;
        for (int j = 0; j < A[0].length; j++) {
            if (j % 2 == 0) {
                prevColOne = colOne;
                colOne = Math.max(prevColTwo, colOne) + Math.max(A[0][j], A[1][j]);
            } else {
                prevColTwo = colTwo;
                colTwo = Math.max(prevColOne, colTwo) + Math.max(A[0][j], A[1][j]);
            }
        }
        return Math.max(colOne, colTwo);
    }
}
