package com.dsaproblems.DSAProblems.leetcode;

import java.util.Arrays;

public class PartitionArrayAccToGivenPivot {

    public static void main(String[] args) {
        int[] nums = {9, 12, 5, 10, 14, 3, 10};
        int pivot = 10;
        System.out.println(Arrays.toString(pivotArrayv1(nums, pivot)));
        System.out.println(Arrays.toString(pivotArrayv2(nums, pivot)));
    }


    //working code
    public static int[] pivotArrayv2(int[] nums, int pivot) {
        int len = nums.length;
        int k = 0, z = len - 1;
        int[] res = new int[len];
        for (int i = 0, j = len - 1; i < len; i++, j--) {
            if (nums[i] < pivot) res[k++] = nums[i];
            if (nums[j] > pivot) res[z--] = nums[j];
        }
        while (k <= z) res[k++] = pivot;
        return res;
    }

    //approach is already O(n) time and O(n) space
    public static int[] pivotArrayv1(int[] nums, int pivot) {
        int len = nums.length;
        int[] ans = new int[len];
        int i = 0;
        for (int num : nums) {
            if (num < pivot) {
                ans[i] = num;
                i++;
            }
        }
        for (int num : nums) {
            if (num == pivot) {
                ans[i] = num;
                i++;
            }
        }
        for (int num : nums) {
            if (num > pivot) {
                ans[i] = num;
                i++;
            }
        }
        return ans;
    }
}
