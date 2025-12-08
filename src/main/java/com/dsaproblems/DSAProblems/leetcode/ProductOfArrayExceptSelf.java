package com.dsaproblems.DSAProblems.leetcode;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(findProductOfArrayExceptSelfv1(nums)));
        System.out.println(Arrays.toString(findProductOfArrayExceptSelfv2(nums)));
    }

    //working code
    private static int[] findProductOfArrayExceptSelfv2(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;
        for (int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right *= nums[i];
        }
        return ans;
    }

    private static int[] findProductOfArrayExceptSelfv1(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = 1;
        right[len - 1] = 1;
        //Compute the cumulative product of elements to the left of each index
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        //Compute the cumulative product of elements to the right of each index
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < len; i++) {
            left[i] = left[i] * right[i];
        }
        return left;
    }
}
