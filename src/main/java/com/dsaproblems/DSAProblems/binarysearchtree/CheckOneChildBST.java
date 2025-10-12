package com.dsaproblems.DSAProblems.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckOneChildBST {

    public static void main(String[] args) {
        //12,1,9,6,2
        //4, 10, 5, 8
        List<Integer> A = new ArrayList<>(Arrays.asList(12, 1, 9, 6, 2));
        System.out.println(isOneChildBSTv1(A));
        System.out.println(isOneChildBSTv2(A));
    }

    private static String isOneChildBSTv2(List<Integer> A) {
        int n = A.size();
        if (n <= 2) {
            return "YES";
        }
        int left = Math.min(A.get(n - 1), A.get(n - 2));
        int right = Math.max(A.get(n - 1), A.get(n - 2));

        for (int i = n - 3; i >= 0; i--) {
            // Each node must be either smaller than the min node or larger than the max node
            if (A.get(i) > left && A.get(i) < right) {
                return "NO";
            }
            left = Math.min(left, A.get(i));
            right = Math.max(right, A.get(i));
        }
        return "YES";
    }

    private static String isOneChildBSTv1(List<Integer> input) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < input.size(); i++) {
            int previous = input.get(i - 1), current = input.get(i);
            if (current > previous)
                min = Math.max(min, previous);
            else
                max = Math.min(max, previous);
            if (min >= current || current >= max)
                return "NO";
        }
        return "YES";
    }
}
