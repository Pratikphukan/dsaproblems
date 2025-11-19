package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static void main(String[] args) {
        int[] nums = {-3, -1, 0, 2, 4, 5};
        int target = 2;
        List<List<Integer>> result = getFourSumv1(nums, target);
        System.out.println(result);
    }

    private static List<List<Integer>> getFourSumv1(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long currentSum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (currentSum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (currentSum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}
