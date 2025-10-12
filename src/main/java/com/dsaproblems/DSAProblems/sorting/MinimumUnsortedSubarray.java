package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumUnsortedSubarray {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 10, 10, 15, 10, 15, 10, 10, 15, 10, 15));
        // 2, 3, 5, 7, 10, 6, 11, 15, 18, 20
        // 2, 3, 10, 11, 5, 6, 7, 15, 18, 20
        // 1,1,10,10,15,10,15,10,10,15,10,15
        System.out.println(findMinimumUnsortedSubarray(A));
        System.out.println(findMinimumUnsortedSubarrayv1(A));
        System.out.println(findMinimumUnsortedSubarrayv2(A));
    }

    private static List<Integer> findMinimumUnsortedSubarrayv1(List<Integer> input) {
        List<Integer> ans = null;
        int startBreakPoint = -1;
        int endBreakPoint = -1;
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) >= input.get(i + 1)) {
                startBreakPoint = i;
                break;
            }
        }
        for (int i = input.size() - 2; i >= 0; i--) {
            if (input.get(i) >= input.get(i + 1)) {
                endBreakPoint = i + 1;
                break;
            }
        }
        int max = -1;
        int min = Integer.MAX_VALUE;
        for (int i = startBreakPoint; i <= endBreakPoint; i++) {
            max = Math.max(max, input.get(i));
            min = Math.min(min, input.get(i));
        }
        int start = -1;
        int end = -1;
        for (int i = 0; i < input.size(); i++) {
            if (min <= input.get(i)) {
                start = i;
                break;
            }
        }
        for (int i = input.size() - 1; i >= 0; i--) {
            if (max >= input.get(i)) {
                end = i;
                break;
            }
        }
        ans = new ArrayList<>(Arrays.asList(start, end));
        return ans;
    }

    //working code, Time complexity is (O(n))
    private static ArrayList<Integer> findMinimumUnsortedSubarrayv2(List<Integer> input) {
        int n = input.size();
        int left = 0, right = n - 1;

        // Find the first out-of-order from the left
        while (left < n - 1 && input.get(left) <= input.get(left + 1)) left++;
        if (left == n - 1) return new ArrayList<>(List.of(-1));

        // Find the first out-of-order from the right
        while (right > 0 && input.get(right) >= input.get(right - 1)) right--;

        // Find min and max in the unsorted subarray
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            min = Math.min(min, input.get(i));
            max = Math.max(max, input.get(i));
        }

        // Extend left boundary if needed
        while (left > 0 && input.get(left - 1) > min) left--;
        // Extend right boundary if needed
        while (right < n - 1 && input.get(right + 1) < max) right++;

        return new ArrayList<>(List.of(left, right));
    }

    //working code, Time complexity is (O(n))
    private static List<Integer> findMinimumUnsortedSubarray(List<Integer> input) {
        int len = input.size();
        int left = 0, right = len - 1;
        while (left < len - 1 && input.get(left) <= input.get(left + 1)) {
            left++;
        }
        if (left == len - 1) {
            return new ArrayList<>(Arrays.asList(-1));
        }
        while (right > 0 && input.get(right) >= input.get(right - 1)) {
            right--;
        }
        int max = -1, min = Integer.MAX_VALUE;
        for (int j = left; j <= right; j++) {
            max = Math.max(max, input.get(j));
            min = Math.min(min, input.get(j));
        }
        int start = -1;
        int end = -1;
        for (int i = 0; i <= left; i++) {
            if (min < input.get(i)) {
                start = i;
                break;
            }
        }
        for (int i = len - 1; i >= right; i--) {
            if (max > input.get(i)) {
                end = i;
                break;
            }
        }
        return new ArrayList<>(Arrays.asList(start, end));
    }

}
