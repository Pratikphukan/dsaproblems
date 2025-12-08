package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(List.of(7, 1, 4, 8, 11, 2, 14, 3));
        System.out.println(lengthOfLongestInSubsequencev1(A));
        int[] nums = {7, 1, 4, 8, 11, 2, 14, 3};
        System.out.println(lengthOfLongestInSubsequencev2(nums));
    }

    private static int lengthOfLongestInSubsequencev2(int[] nums) {
        int len = nums.length, size = 0;
        int[] tails = new int[len];
        for (int num : nums) {
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;
            if (left == size) {
                size++;
            }
        }
        return size;
    }

    private static int lengthOfLongestInSubsequencev1(ArrayList<Integer> A) {
        int len = A.size();
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        int maxlength = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (A.get(i) > A.get(j) && ans[j] + 1 > ans[i]) {
                    ans[i] = ans[j] + 1;
                    maxlength = Math.max(maxlength, ans[i]);
                }
            }
        }
        return maxlength;
    }

}
