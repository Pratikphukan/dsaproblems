package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class FindMaxSubarraySum {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(-3, 4, 5, -6, 8, 9, 10, -10, 8));

        System.out.println(findMaxSubarraySum(input));
        System.out.println(findMaxSubarraySum1(input));
        System.out.println(findMaxSubarraySum2(input));
        System.out.println(findMaxSubarraySum3(input));
    }

    // working solution
    private static int findMaxSubarraySum3(List<Integer> input) {
        int sum = input.stream().reduce(0, (acc, num) -> acc + num);
        Comparator<Integer> integerCompare = (num1, num2) -> num1 - num2;
        int maxElement = input.stream().max(integerCompare).orElse(Integer.MIN_VALUE);
        int minElement = input.stream().min(integerCompare).orElse(Integer.MAX_VALUE);
        if (maxElement < 0) {
            return maxElement; // if max is negative, then all elements are negative
        }
        if (minElement > 0) { // if min is positive, then all elements are positive
            return sum;
        }
        sum = 0;
        int maxSum = maxElement;
        for (Integer num : input) {
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

    private static int findMaxSubarraySum2(List<Integer> input) {
        int maxElement = Integer.MIN_VALUE;
        int minElement = Integer.MAX_VALUE;
        int sum = 0;

        for (Integer num : input) {
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
        for (Integer num : input) {
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

    private static int findMaxSubarraySum1(List<Integer> A) {
        int len = A.size();
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += A.get(i);
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }

    //working solution
    private static int findMaxSubarraySum(List<Integer> A) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (Integer num : A) {
            sum += num;
            maxSum = Math.max(sum, maxSum);
            sum = Math.max(sum, 0);
        }
        return maxSum;
    }

}
