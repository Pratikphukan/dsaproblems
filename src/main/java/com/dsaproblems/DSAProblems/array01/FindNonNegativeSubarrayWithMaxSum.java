package com.dsaproblems.DSAProblems.array01;

import java.util.*;

public class FindNonNegativeSubarrayWithMaxSum {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 5, -7, 2, 5));
        // 1,2,5,-7,2,5
        // -3, 4, 5, -6, 8, 9, 10, -10, 8
        System.out.println(findNonNegativeSubarrayWithMaxSum(input));
        System.out.println(findNonNegativeSubarrayWithMaxSumv1(input));
        System.out.println(findNonNegativeSubarrayWithMaxSumv2(input));
    }

    //working code
    private static List<Integer> findNonNegativeSubarrayWithMaxSumv2(List<Integer> input) {
        int len = input.size();
        int maxStart = -1, maxEnd = -1, currStart = 0;
        long currSum = 0L, maxSum = -1L;
        for (int i = 0; i < len; i++) {
            if (input.get(i) >= 0) {
                currSum += input.get(i);
                if (currSum > maxSum ||
                        (currSum == maxSum &&
                                (i - currStart + 1) > maxEnd - maxStart)) {
                    maxSum = currSum;
                    maxStart = currStart;
                    maxEnd = i + 1;
                }
            } else {
                currSum = 0L;
                currStart = i + 1;
            }
        }
        return (maxStart != -1) ? new ArrayList<>(input.subList(maxStart, maxEnd)) : new ArrayList<>();
    }

    private static List<Integer> findNonNegativeSubarrayWithMaxSumv1(List<Integer> input) {
        List<Integer> ans = new ArrayList<>();
        long sum = 0l;
        int count = 0;
        int maxCount = 0;
        long maxSum = 0l; // since only non negative numbers are included
        int start = -1;
        int end = -1;
        int tempStart = 0;
        int tempEnd = 0;
        for (Integer num : input) {
            sum += num;
            count++;
            tempEnd++;
            if (sum > maxSum) {
                maxSum = sum;
                start = tempStart;
                end = tempEnd;
            }
            if (sum == maxSum && count > maxCount) {
                maxCount = count;
                start = tempStart;
                end = tempEnd;
            }
        }
        if (start != -1 && end != -1) {
            for (int i = start; i < end; i++) {
                ans.add(input.get(i));
            }
        }
        return ans;
    }

    private static List<Integer> findNonNegativeSubarrayWithMaxSum(List<Integer> input) {
        List<Integer> ans = new ArrayList<>();
        long sum = 0L;
        int count = 0;
        int maxCount = 0;
        long maxSum = 0l; // since only non negative numbers are included
        int start = -1;
        int end = -1;
        int tempStart = 0;
        int tempEnd = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) >= 0) {
                sum += input.get(i);
                count++;
                tempEnd++;
            } else {
                sum = 0;
                count = 0;
                tempStart = i + 1;
                tempEnd = i + 1;
            }
            if (sum > maxSum) {
                maxSum = sum;
                start = tempStart;
                end = tempEnd;
            }
            if (sum == maxSum && count > maxCount) {
                maxCount = count;
                start = tempStart;
                end = tempEnd;
            }
        }
        if (start != -1 && end != -1) {
            for (int i = start; i < end; i++) {
                ans.add(input.get(i));
            }
        }
        return ans;
    }

}
