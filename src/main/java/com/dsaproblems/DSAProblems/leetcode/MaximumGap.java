package com.dsaproblems.DSAProblems.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumGap {

    public static void main(String[] args) {
        int[] nums = {3, 6, 9, 1};
        System.out.println(maximumGapv1(nums));
        System.out.println(maximumGapv2(nums));
    }

    //working code, bad performance
    private static int maximumGapv2(int[] nums) {
        if (nums.length < 2) return 0;
        Queue<Integer> maxHeap = new PriorityQueue<>((num1, num2) -> Integer.compare(num2, num1));
        for (int num : nums) maxHeap.offer(num);
        int maxDiff = -1, max = maxHeap.poll();
        while (!maxHeap.isEmpty()) {
            maxDiff = Math.max(maxDiff, max - maxHeap.peek());
            max = maxHeap.poll();
        }
        return maxDiff;
    }

    private static int maximumGapv1(int[] nums) {
        if (nums.length < 2) return 0;
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int num : nums) {
            minVal = Math.min(num, minVal);
            maxVal = Math.max(num, maxVal);
        }
        if (minVal == maxVal) return 0;
        int n = nums.length;
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketCount = (maxVal - minVal) / bucketSize + 1;
        Integer[] bucketMin = new Integer[bucketCount];
        Integer[] bucketMax = new Integer[bucketCount];
        for (int num : nums) {
            int bucketIndex = (num - minVal) / bucketSize;
            bucketMin[bucketIndex] = (bucketMin[bucketIndex] == null) ? num : Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = (bucketMax[bucketIndex] == null) ? num : Math.max(bucketMax[bucketIndex], num);
        }
        int maxGap = 0;
        int previousBucketMax = minVal;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketMin[i] == null) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previousBucketMax);
            previousBucketMax = bucketMax[i];
        }
        return maxGap;
    }
}
