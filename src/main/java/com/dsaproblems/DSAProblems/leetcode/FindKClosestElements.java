package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FindKClosestElements {

    public static void main(String[] args) {
        //1,2,3,4,5|4|3
        //1, 1, 2, 3, 4, 5|4|-1
        //-2,-1,1,2,3,4,5|7|3
        Map<String, Number> map1 = new HashMap<>();
        map1.put("vbnm", 45);
        Number number = map1.get("vbnm");
        map1.put("vbnm", number.longValue() + 36);
        System.out.println(number);
        System.out.println(map1);


        int[] A = {-2, -1, 1, 2, 3, 4, 5};
        System.out.println(findClosestElementsv1(A, 7, 3));
        System.out.println(findClosestElementsv2(A, 7, 3));

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("vdcvd", 12);
        map.compute("vdcvd", (k, v) -> v != null ? v + 20 : 1000);
        map.compute("gvjhdb", (k, v) -> v != null ? v + 20 : 67890);

        AtomicBoolean flag = new AtomicBoolean(true);
        boolean success = flag.compareAndSet(true, false);
        System.out.println(success);
        System.out.println(flag.get());

        AtomicBoolean flag1 = new AtomicBoolean(false);
        boolean success1 = flag.compareAndSet(true, false);
        System.out.println(success1);
        System.out.println(flag1.get());


        Integer x = map.get("vbnm");
        map.put("vfbnm", null);
        map.put(null, 45);

        System.out.println("ghbjnk");

    }

    private static List<Integer> findClosestElementsv2(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    //O(log n + k) time complexity (binary search + two-pointer expansion)
    //working code
    private static List<Integer> findClosestElementsv1(int[] nums, int k, int x) {
        int len = nums.length;
        List<Integer> result = new ArrayList<>();
        if (len == k) {
            for (int num : nums) result.add(num);
            return result;
        }
        int r = lowerBound(nums, x); //lower_bound: first index i where arr[i] >= x
        int l = r - 1;
        while (r - l - 1 < k) {
            if (l < 0) r++;
            else if (r >= len) l--;
            else {
                if (x - nums[l] <= nums[r] - x) l--;
                else r++;
            }
        }
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
