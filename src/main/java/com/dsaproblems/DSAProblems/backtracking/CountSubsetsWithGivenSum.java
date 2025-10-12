package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CountSubsetsWithGivenSum {

    //(1,2) or (2,1) are considered same in a subset
    //we need to find if there exists a subset with given sum
    //Subset: Order does not matter.
    //Subsequence: Order matters and elements must appear in the same relative order as the original.
    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(List.of(5, 2, 7));
        int B = 5;
        System.out.println(countSubsetsWithGivenSumv1(input, B));
        System.out.println(countSubsetsWithGivenSumv2(input, B));
        System.out.println(countSubsetsWithGivenSumv3(input, B));
        System.out.println(countSubsetsWithGivenSumv4(input, B));
    }

    private static int countSubsetsWithGivenSumv4(ArrayList<Integer> input, int B) {
        int n = input.size();
        Integer[][] dp = new Integer[n + 1][B + 1];
        return countSubsetsWithGivenSumv4(input, n, B, dp);
    }

    private static int countSubsetsWithGivenSumv4(ArrayList<Integer> input, int idx, int target, Integer[][] dp) {
        if (target == 0) return 1;// found a valid subset
        if (idx == 0) return 0; //no elements left
        if (dp[idx][target] == null) {
            int exclude = countSubsetsWithGivenSumv4(input, idx - 1, target, dp);
            int include = 0;
            if (target - input.get(idx - 1) >= 0) {
                include = countSubsetsWithGivenSumv4(input, idx - 1, target - input.get(idx - 1), dp);
            }
            dp[idx][target] = include + exclude;
        }
        return dp[idx][target];
    }

    private static int countSubsetsWithGivenSumv3(ArrayList<Integer> input, int B) {
        return countSubsetsWithGivenSumv3(input, B, 0, 0);
    }

    //working code
    private static int countSubsetsWithGivenSumv3(ArrayList<Integer> A, int B, int idx, int currSum) {
        if (A.size() == idx) {
            return currSum == B ? 1 : 0;
        }
        return countSubsetsWithGivenSumv3(A, B, idx + 1, currSum + A.get(idx)) +
                countSubsetsWithGivenSumv3(A, B, idx + 1, currSum);
    }

    private static int countSubsetsWithGivenSumv2(ArrayList<Integer> input, int B) {
        return countSubsetsWithGivenSumv2(input, B, 0, 0);
    }

    private static int countSubsetsWithGivenSumv2(ArrayList<Integer> A, int B, int idx, int currSum) {
        if (A.size() == idx) {
            return currSum == B ? 1 : 0;
        }
        currSum += A.get(idx);
        int s1 = countSubsetsWithGivenSumv2(A, B, idx + 1, currSum);
        currSum -= A.get(idx);
        int s2 = countSubsetsWithGivenSumv2(A, B, idx + 1, currSum);
        return s2 + s1;
    }

    private static int countSubsetsWithGivenSumv1(ArrayList<Integer> input, int B) {
        return countSubsetsWithGivenSumv1(input, B, new ArrayDeque<>(), 0);
    }

    //At each recursion step, it either includes or excludes the current element,
    // and counts subsets whose sum equals B
    //Total TC: O(n * 2^n), Total SC: O(n)
    private static int countSubsetsWithGivenSumv1(ArrayList<Integer> A, int B, Deque<Integer> stack, int idx) {
        if (A.size() == idx) {
            int sum = 0;
            for (int num : stack) {
                sum += num;
            }
            return sum == B ? 1 : 0;
        }
        stack.addLast(A.get(idx));
        int c1 = countSubsetsWithGivenSumv1(A, B, stack, idx + 1);
        stack.pollLast();// delete the last added element
        int c2 = countSubsetsWithGivenSumv1(A, B, stack, idx + 1);
        return c1 + c2;
    }
}
