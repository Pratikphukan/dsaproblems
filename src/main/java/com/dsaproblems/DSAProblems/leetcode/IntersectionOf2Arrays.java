package com.dsaproblems.DSAProblems.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOf2Arrays {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersectionOf2Arraysv1(nums1, nums2)));
    }

    private static int[] intersectionOf2Arraysv1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return intersectionOf2Arraysv1(nums2, nums1);
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) set.add(num);
        Set<Integer> intersection = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) intersection.add(num);
        }
        int[] result = new int[intersection.size()];
        int idx = 0;
        for (int num : intersection) {
            result[idx++] = num;
        }
        return result;
    }
}
