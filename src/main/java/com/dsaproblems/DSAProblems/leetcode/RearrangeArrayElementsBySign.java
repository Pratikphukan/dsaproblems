package com.dsaproblems.DSAProblems.leetcode;

import java.util.Arrays;

public class RearrangeArrayElementsBySign {

    public static void main(String[] args) {
        int[] nums = {3, 1, -2, -5, 2, -4};
        System.out.println(Arrays.toString(rearrangeArrayElementsBySignv1(nums)));
    }

    //working code
    private static int[] rearrangeArrayElementsBySignv1(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int i = 0, j = 1; //The rearranged array begins with a positive integer
        for (int num : nums) {
            if (num > 0) {
                res[i] = num;
                i = i + 2;
            } else {
                res[j] = num;
                j = j + 2;
            }
        }
        return res;
    }
}
