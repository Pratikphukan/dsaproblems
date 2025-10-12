package com.dsaproblems.DSAProblems.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Sixlets {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 8));
        int B = 2;
        System.out.println(countSubsetsWithSumSizev1(input, B));
        System.out.println(countSubsetsWithSumSizev2(input, B));
    }

    //WORKING CODE
    //recursion with max depth n, so SC is O(n)
    //TC is O(2^n)
    private static int countSubsetsWithSumSizev2(ArrayList<Integer> input, int B) {
        return countSubsetsWithSumSizev2(input, B, 0, 0, 0);
    }

    private static int countSubsetsWithSumSizev2(ArrayList<Integer> A, int B, int currSize, int currSum, int idx) {
        if (idx == A.size()) {
            return (currSize == B && currSum <= 1000) ? 1 : 0;
        }
        int c1 = countSubsetsWithSumSizev2(A, B, currSize + 1, currSum + A.get(idx), idx + 1);
        int c2 = countSubsetsWithSumSizev2(A, B, currSize, currSum, idx + 1);
        return c1 + c2;
    }

    //WORKING CODE
    //TC is O(n * 2^n)
    private static int countSubsetsWithSumSizev1(ArrayList<Integer> input, int B) {
        return countSubsetsWithSumSizev1(input, B, new ArrayDeque<>(), 0);
    }

    private static int countSubsetsWithSumSizev1(ArrayList<Integer> A, int B, Deque<Integer> stack, int idx) {
        if (A.size() == idx) {
            if (stack.size() != B) return 0;
            int sum = 0;
            for (int num : stack) {
                sum += num;
            }
            return sum <= 1000 ? 1 : 0;
        }
        stack.addLast(A.get(idx));
        int c1 = countSubsetsWithSumSizev1(A, B, stack, idx + 1);
        stack.pollLast();// delete the last added element
        int c2 = countSubsetsWithSumSizev1(A, B, stack, idx + 1);
        return c1 + c2;
    }
}
