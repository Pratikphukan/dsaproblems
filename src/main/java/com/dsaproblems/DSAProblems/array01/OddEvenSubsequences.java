package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OddEvenSubsequences {

    public static void main(String[] args) {
        // 2, 2, 2, 2, 2, 2
        // 1, 2, 2, 5, 6
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 5, 6));
        System.out.println(lengthOfLongestOddEvenSubsequencev1(input));
        System.out.println(lengthOfLongestOddEvenSubsequencev2(input));

    }

    private static int lengthOfLongestOddEvenSubsequencev2(List<Integer> input) {
        int x = 0, y = 1;
        int ans1 = 0, ans2 = 0;
        for (int num : input) {
            int parity = num & 1;
            if (parity == x) {
                x = x ^ 1;
                ans1++;
            }
            if (parity == y) {
                y = y ^ 1;
                ans2++;
            }
        }
        return Math.max(ans1, ans2);
    }

    private static int lengthOfLongestOddEvenSubsequencev1(List<Integer> input) {
        int count = 1;
        int prev = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            if (prev % 2 != input.get(i) % 2) {
                count++;
                prev = input.get(i);
            }
        }
        return count;
    }

}
