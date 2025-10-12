package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsequenceSumProblem {

    public static void main(String[] args) {
        //-2,6,4->2
        //1, 20, 13, 4, 5->
        List<Integer> input = new ArrayList<>(List.of(-2, 6, 4));
        int target = 2;
        System.out.println(isSubsequenceSumExistsv1(input, target));
        System.out.println(isSubsequenceSumExistsv2(input, target));
        System.out.println(isSubsequenceSumExistsv3(input, target));
        System.out.println(isSubsequenceSumExistsv4(input, target));
    }

    private static boolean isSubsequenceSumExistsv4(List<Integer> input, int target) {
        Set<Integer> sums = new HashSet<>();
        sums.add(0);
        for (int num : input) {
            Set<Integer> newSums = new HashSet<>(sums);
            for (int s : sums) {
                newSums.add(s + num);
            }
            sums = newSums;
        }
        return sums.contains(target);
    }

    //working solution
    private static int isSubsequenceSumExistsv3(List<Integer> input, int target) {
        //return isSubsequenceSumExistsv3(input, target, 0, input.size()) ? 1 : 0;
        return isSubsequenceSumExistsv5(input, target, 0, input.size()) ? 1 : 0;
    }

    private static boolean isSubsequenceSumExistsv5(List<Integer> input, int target, int sum, int len) {
        if (len == 0) {
            return sum == target;
        }
        return isSubsequenceSumExistsv5(input, target, sum + input.get(len - 1), len - 1) ||
                isSubsequenceSumExistsv5(input, target, sum, len - 1);
    }

    private static boolean isSubsequenceSumExistsv3(List<Integer> input, int target, int sum, int len) {
        if (len == 0) {
            return sum == target;
        }
        // include the current element
        if (isSubsequenceSumExistsv3(input, target, sum + input.get(len - 1), len - 1)) {
            return true;
        }
        // exclude the current element
        return isSubsequenceSumExistsv3(input, target, sum, len - 1);
    }

    //working solution
    private static int isSubsequenceSumExistsv2(List<Integer> input, int target) {
        int n = input.size();
        int possibilities = 1 << n;
        for (int i = possibilities - 1; i >= 1; i--) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (checkBitv2(i, j)) {
                    sum += input.get(j);
                }
            }
            if (sum == target) {
                return 1;
            }
        }
        return 0;
    }

    //working solution
    private static int isSubsequenceSumExistsv1(List<Integer> input, int target) {
        int n = input.size();
        int possibilities = 1 << n;
        for (int i = 1; i < possibilities; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (checkBitv2(i, j)) {
                    sum += input.get(j);
                }
            }
            if (sum == target) {
                return 1;
            }
        }
        return 0;
    }

    public static boolean checkBitv1(int N, int j) {
        return ((N >> j) & 1) == 1;
    }

    public static boolean checkBitv2(int N, int j) {
        return (N & (1 << j)) != 0;
    }
}
