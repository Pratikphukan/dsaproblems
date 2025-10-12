package com.dsaproblems.DSAProblems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        //1, 2, 3->6
        //-1, 2, 1, -4->1
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        int targetSum = 6;
        System.out.println(threeSumClosestv1(input, targetSum));
        System.out.println(threeSumClosestv2(input, targetSum));
    }

    //working code
    private static int threeSumClosestv2(List<Integer> input, int targetSum) {
        Collections.sort(input);
        int len = input.size();
        int closest = input.get(0) + input.get(1) + input.get(2);
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                int sum = input.get(i) + input.get(left) + input.get(right);
                if (Math.abs(sum - targetSum) < Math.abs(closest - targetSum)) {
                    closest = sum;
                }
                if (sum < targetSum) {
                    left++;
                } else if (sum > targetSum) {
                    right--;
                } else {
                    return sum;
                }
            }
        }
        return closest;
    }

    //working code
    private static int threeSumClosestv1(List<Integer> input, int targetSum) {
        Collections.sort(input);
        int len = input.size();
        int minDiff = Integer.MAX_VALUE;
        int ans = input.get(0) + input.get(1) + input.get(2);
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && input.get(i).equals(input.get(i - 1))) continue; // skip duplicate i
            int left = i + 1, right = len - 1, curr = input.get(i);
            while (left < right) {
                int sum = curr + input.get(left) + input.get(right);
                if (Math.abs(sum - targetSum) < minDiff) {
                    minDiff = Math.abs(sum - targetSum);
                    ans = sum;
                }
                if (sum == targetSum) {
                    return sum;
                } else if (sum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }

}
