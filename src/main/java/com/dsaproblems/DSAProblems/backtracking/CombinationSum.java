package com.dsaproblems.DSAProblems.backtracking;

import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        //8, 10, 6, 11, 1, 16, 8|28
        //2, 3, 6, 7|7
        //2, 2, 4, 4|6
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 2, 4, 4));
        int B = 6;
        System.out.println(combinationSumv1(A, B));

        System.out.println(combinationSumv2(A, B));
    }

    //working code
    private static ArrayList<ArrayList<Integer>> combinationSumv2(ArrayList<Integer> A, int B) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        backtrack(B, result, new ArrayDeque<>(), 0, A);
        return result;
    }

    private static void backtrack(int remain,
                                  ArrayList<ArrayList<Integer>> result,
                                  Deque<Integer> stack,
                                  int start,
                                  ArrayList<Integer> A) {
        if (remain < 0) return;
        if (remain == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < A.size(); i++) {
            //Skip duplicates during the loop by checking if the current element is the same as the previous one (and i > start)
            if (i > start && A.get(i).equals(A.get(i - 1))) continue; // Skip duplicates
            stack.addLast(A.get(i));
            backtrack(remain - A.get(i), result, stack, i, A);
            stack.pollLast();
        }
    }

    public static ArrayList<ArrayList<Integer>> combinationSumv1(ArrayList<Integer> A, int B) {
// Sort the array first it reduces the soritng required before returning the list.
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (A.isEmpty())
            return list;
        combinationSum(list, A, new ArrayList<>(), B, 0, 0);
        return list;
    }

    public static void combinationSum(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> input, ArrayList<Integer> out, int B, int index, int sum) {
        // Returns if sum exceeds the target value
        if (sum > B) {
            return;
        }
        if (sum == B) {
            // Check if the list already present dont add it again, duplicates lists can come because input list can have duplicate elements
            if (!list.contains(out))
                list.add(new ArrayList<>(out));
            return;
        }

        for (int i = index; i < input.size(); i++) {
            out.add(input.get(i));
            // Dont need to increase the index here as we need all possible sum even if the value is counted multiple times.
            combinationSum(list, input, out, B, i, sum + input.get(i));
            out.remove(out.size() - 1);
        }
    }
}
