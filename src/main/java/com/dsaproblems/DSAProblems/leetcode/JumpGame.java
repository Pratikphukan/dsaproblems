package com.dsaproblems.DSAProblems.leetcode;

public class JumpGame {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(possibleToLastIdxv1(nums));
    }

    private static boolean possibleToLastIdxv1(int[] nums) {
        int len = nums.length;
        int lastIdx = len - 1;
        int maxReach = 0; //farthest idx we can reach so far
        for (int i = 0; i < len; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]); // Update maxReach: the maximum of its current value or the current index plus the jump we can make
            if (maxReach >= lastIdx)
                return true; // If maxReach is beyond or equal to the last index, we can reach the end.
        }
        return false;
    }
}
