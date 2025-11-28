package com.dsaproblems.DSAProblems.leetcode;

public class FindDuplicateNumber {

    public static void main(String[] args) {
        //1, 3, 4, 2, 2
        //3, 1, 3, 4, 2
        //3, 3, 3, 3, 3
        int[] nums = {3, 3, 3, 3, 3};
        System.out.println(findDuplicateNumberv1(nums));
        System.out.println(findDuplicateNumberv2(nums));
    }

    //working code
    private static int findDuplicateNumberv2(int[] nums) {
        int slow = nums[nums[0]];
        int fast = nums[nums[nums[0]]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    //not working
    private static int findDuplicateNumberv1(int[] nums) {
        int sum = 0, len = nums.length;
        for (int i = 1; i < len; i++) sum += i;
        for (int num : nums) sum -= num;
        return -sum;
    }
}
