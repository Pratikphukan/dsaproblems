package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    public static void main(String[] args) {
        //1,2,3,4,5|4|3
        //1, 1, 2, 3, 4, 5|4|-1
        //-2,-1,1,2,3,4,5|7|3
        int[] A = {1, 2, 3, 4, 5};
        System.out.println(findClosestElementsv1(A, 4, 3));
    }

    private static List<Integer> findClosestElementsv1(int[] nums, int k, int x) {
        int len = nums.length;
        int r = lowerBound(nums, x);
        int l = r - 1;
        while (r - l - 1 < k) {
            if (l < 0) r++;
            else if (l >= len) l--;
            else {
                int ldist = x - nums[l];
                int rdist = nums[r] - x;
                if (ldist <= rdist) l--;
                else r++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = l + 1; i < r; i++) result.add(nums[i]);
        return result;
    }

    private static int lowerBound(int[] nums, int x) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int lowerBoundv1(int[] nums, int x) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = high - (high - low) / 2;
            if (nums[mid] > x) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return high;
    }
}
