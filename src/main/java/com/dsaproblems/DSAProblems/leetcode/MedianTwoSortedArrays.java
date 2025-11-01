package com.dsaproblems.DSAProblems.leetcode;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
//        int[] nums1 = {1, 3};
//        int[] nums2 = {2};

        int[] nums1 = {3, 4};
        int[] nums2 = {1, 2};
        System.out.println(findMedianSortedArraysv1(nums1, nums2));
        System.out.println(findMedianSortedArraysv2(nums1, nums2));
    }

    //working code
    //Time O(n + m) worst case, auxiliary space O(1).
    private static double findMedianSortedArraysv2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int total = len1 + len2;
        if (total == 0) return 0.0D;
        int target = total / 2; // we need to advance this many steps (0-based) to reach median position
        int i = 0, j = 0;
        int curr = 0, prev = 0;
        for (int t = 0; t <= target; t++) {
            prev = curr;
            if (i < len1 && (j >= len2 || nums1[i] <= nums2[j])) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
        }
        return (total & 1) == 1 ? (double) curr :
                ((double) prev + (double) curr) / 2.0;
    }

    //working code
    //Time complexity: O(n + m) in the worst case (n = nums1.length, m = nums2.length). The method merges both arrays once, so work is proportional to the total number of elements.
    //Space complexity: O(n + m) worst case because it allocates a merged array of size n + m.
    public static double findMedianSortedArraysv1(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0 && len2 == 0) return 0.0D;
        if (len1 == 0) {
            if (len2 % 2 == 0) return (double) (nums2[len2 / 2] + nums2[(len2 / 2) - 1]) / 2;
            return (double) nums2[len2 / 2];
        }
        if (len2 == 0) {
            if (len1 % 2 == 0) return (double) (nums1[len1 / 2] + nums1[(len1 / 2) - 1]) / 2;
            return (double) nums1[len1 / 2];
        }
        int[] nums = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        while (i < len1) nums[k++] = nums1[i++];
        while (j < len2) nums[k++] = nums2[j++];
        if (k % 2 == 0) return (double) (nums[k / 2] + nums[(k / 2) - 1]) / 2;
        return (double) nums[k / 2];
    }
}
