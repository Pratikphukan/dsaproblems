package com.dsaproblems.DSAProblems.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println(findDuplicateWithGivenIdxDiffv1(nums, k));
    }

    //working code
    private static boolean findDuplicateWithGivenIdxDiffv1(int[] nums, int k) {
        if (nums.length == 1 || k == 0) return false;
        Map<Integer, Integer> lastIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer prevIdx = lastIdx.put(nums[i], i); //returns the previous value associated with key
            if (prevIdx != null && i - prevIdx <= k) return true;
        }
        return false;
    }
}
