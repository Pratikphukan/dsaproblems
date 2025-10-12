package com.dsaproblems.DSAProblems.subarrays;

import java.util.*;

public class CountOfSubarrayWithGivenSum {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(3, 4, 7, 2, -3, 1, 4, 2));
        int targetSum = 7;
        // 10, 2, -2, -20, 10|-10
        // 3,4,7,2,-3,1,4,2|7
        System.out.println(findSubarrayWithSumKv1(input, targetSum));
        System.out.println(findSubarrayWithSumKv2(input, targetSum));
        System.out.println(findSubarrayWithSumKv3(input, targetSum));
        System.out.println(findSubarrayWithSumKv4(input, targetSum));
    }

    //not working code
    private static int findSubarrayWithSumKv4(List<Integer> input, int targetSum) {
        Map<Long, Integer> sumFrequency = new HashMap<>();
        long currSum = 0L;
        sumFrequency.put(0L, 1);
        int count = 0;
        for (int num : input) {
            currSum += num;
            count += sumFrequency.getOrDefault(currSum - targetSum, 0);
            sumFrequency.put(currSum, sumFrequency.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }

    //working code
    //prefixSum[j] - prefixSum[i-1] = targetSum or sum[i, j]
    private static int findSubarrayWithSumKv3(List<Integer> input, int targetSum) {
        Map<Integer, Integer> prevSum = new HashMap<>();
        int currSum = 0;
        int res = 0;
        for (Integer num : input) {
            currSum += num;
            if (currSum == targetSum) { //subarray from start
                res += 1;
            }
            if (prevSum.containsKey(currSum - targetSum)) { //check if any prefix sum(currSum - targetSum) exists in the map
                res += prevSum.get(currSum - targetSum); //there is a possible subarray with sum = 7
            }
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }
        return res;
    }

    // sum(i->j)=pf(j)-pf(i-1)
    // pf(j)->currSum, sum(i->j)->targetSum
    // targetSum = currSum - pf(i-1)=>pf(i-1)=currSum - targetSum
    private static int findSubarrayWithSumKv2(List<Integer> input, int targetSum) {
        Map<Integer, Integer> prevSum = new HashMap<>();
        prevSum.put(0, 1);
        int currSum = 0;
        int remainingSum = 0;
        int res = 0;
        for (Integer num : input) {
            currSum += num;
            remainingSum = currSum - targetSum;
            if (prevSum.containsKey(remainingSum)) {
                res += prevSum.get(remainingSum);
            }
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }
        return res;
    }

    private static int findSubarrayWithSumKv1(List<Integer> input, int targetSum) {
        int len = input.size();
        Integer sum = null;
        int count = 0;
        for (int i = 0; i < len; i++) {
            sum = 0;
            for (int j = i; j < len; j++) {
                sum += input.get(j);
                if (sum == targetSum) {
                    count++;
                }
            }
        }
        return count;
    }
}
