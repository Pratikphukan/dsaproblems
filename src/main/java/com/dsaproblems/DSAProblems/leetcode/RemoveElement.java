package com.dsaproblems.DSAProblems.leetcode;

public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeGivenElementv1(nums, 2));
    }

    //working code
    private static int removeGivenElementv1(int[] nums, int val) {
        int len = nums.length;
        int left = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[left] = nums[i];// If not equal, assign it to the current position of left
                left++; // Increment left to the next position.
            }
        }
        return left;
    }
}
