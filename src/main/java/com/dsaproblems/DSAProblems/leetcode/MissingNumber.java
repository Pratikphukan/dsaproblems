package com.dsaproblems.DSAProblems.leetcode;

public class MissingNumber {

    public static void main(String[] args) {
        /// 9, 6, 4, 2, 3, 5, 7, 0, 1
        //2,0
        int[] nums = {2, 0};
        //System.out.println(findMissingNumberv1(nums));
        System.out.println(findMissingNumberv2(nums));
    }

    //working code
    private static int findMissingNumberv2(int[] nums) {
        int len = nums.length;
        int xor = 0;
        for (int i = 0; i <= len; i++) xor ^= i;
        for (int num : nums) xor ^= num;
        return xor;
    }

    //working code but failing for a few cases
    private static int findMissingNumberv1(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int ele = Math.abs(nums[i]);
            if (ele < len) nums[ele] = -1 * Math.abs(nums[ele]);
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) return i;
        }
        return len;
    }
}
