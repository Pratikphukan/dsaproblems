package com.dsaproblems.DSAProblems.subarrays;

import java.util.*;

public class MaxLengthOfSubarrayWithSum0 {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(3, 3, 4, -5, -2, 2, 1, -3, 3, -1, 5, -4, -1));
        System.out.println(findLengthOfLongestSubarrayWithSum0v1(A));
        System.out.println(findLengthOfLongestSubarrayWithSum0v2(A));
    }

    private static int findLengthOfLongestSubarrayWithSum0v2(List<Integer> A) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        long sum = 0L;
        int maxLen = -1;
        for (int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                int prevOccurrence = map.get(sum);
                maxLen = Math.max(maxLen, i - prevOccurrence);
            }
        }
        return maxLen;
    }

    private static int findLengthOfLongestSubarrayWithSum0v1(List<Integer> A) {
        List<Integer> prefix = new ArrayList<>();
        int sum = 0;
        for (Integer item : A) {
            sum += item;
            prefix.add(sum);
        }
        int currentMaxNoOfElements = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < prefix.size(); i++) {
            int key = prefix.get(i);
            if (!map.containsKey(key)) {
                map.put(key, i);
            } else {
                int firstIdx = map.get(key);
                currentMaxNoOfElements = Math.max(currentMaxNoOfElements, i - firstIdx);
            }
        }
        return currentMaxNoOfElements;
    }
}
