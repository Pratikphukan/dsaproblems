package com.dsaproblems.DSAProblems.subarrays;

import java.util.*;

public class CheckSubarrayWithSum0Exists {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 1, -3, 4, 3, 1, -2, -3, 2));

        System.out.println(checkSubarrayWithSum0v1(input));
        System.out.println(checkSubarrayWithSum0v2(input));
    }

    //set operations are O(1) on average
    //working code
    //TC: O(n), where n is the size of the input list
    //SC: O(n), in the worst case (no subarray with sum 0), the set can grow to size n
    private static int checkSubarrayWithSum0v2(List<Integer> A) {
        Set<Long> set = new HashSet<>();
        long sum = 0L;
        for (Integer item : A) {
            sum += item;
            if (set.contains(sum) || sum == 0L) {
                return 1;
            }
            set.add(sum);
        }
        return 0;
    }

    //working
    private static int checkSubarrayWithSum0v1(List<Integer> A) {
        Set<Long> set = new HashSet<>();
        long sum = 0L;
        set.add(sum);
        for (int num : A) {
            sum += num;
            if (set.contains(sum)) {
                return 1;
            }
            set.add(sum);
        }
        return 0;
    }

}
