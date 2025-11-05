package com.dsaproblems.DSAProblems.leetcode;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesfromSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        //System.out.println(removeDuplicatesFromSortedArrayv1(nums));
        //System.out.println(removeDuplicatesFromSortedArrayv2(nums));
        System.out.println(removeDuplicatesFromSortedArrayv3(nums));
    }

    //working code
    private static int removeDuplicatesFromSortedArrayv3(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;
        int left = 0, right = 1;
        while (right < len) {
            int curr = nums[left];
            while (right < len && nums[right] == curr) {
                right++;
            }
            left++;
            if (right < len) nums[left] = nums[right];
        }
        return left;
    }

    //working code
    private static int removeDuplicatesFromSortedArrayv2(int[] nums) {
        if (nums.length == 0) return 0;
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            // If current element pointed to by fast is not equal to nums[left],
            // it means we found a new unique element.
            if (nums[right] != nums[left]) {
                left++; // Increment left to the next position for unique element.
                nums[left] = nums[right]; // Update nums[left] with the new unique element found at nums[fast]
            }
        }
        return left + 1;
    }

    private static int removeDuplicatesFromSortedArrayv1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        return set.size();
    }
}
