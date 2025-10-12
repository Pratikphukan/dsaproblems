package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSumContiguousSubarray {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));
        System.out.println(findMaxSubarraySumv1(A));
    }

    //working code
    private static int findMaxSubarraySumv1(ArrayList<Integer> A) {
        int maxElement = Integer.MIN_VALUE;
        int minElement = Integer.MAX_VALUE;
        int sum = 0;

        for (int num : A) {
            maxElement = Math.max(maxElement, num);
            minElement = Math.min(minElement, num);
            sum += num;
        }

        if (maxElement < 0) {
            return maxElement; // if max is negative, then all elements are negative
        }

        if (minElement > 0) { // if min is positive, then all elements are positive
            return sum;
        }
        sum = 0;
        int maxSum = maxElement;
        for (int num : A) {
            sum += num;
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
