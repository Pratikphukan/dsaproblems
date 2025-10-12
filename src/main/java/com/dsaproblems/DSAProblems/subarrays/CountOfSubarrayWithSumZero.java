package com.dsaproblems.DSAProblems.subarrays;

import java.util.*;

public class CountOfSubarrayWithSumZero {

    public static void main(String[] args) {
        // 1,2,-3,2,2,-2,-2,6,-1,-3
        // 2, 2, 1, -3, 4, 3, 1, -2, -3, 2
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, -3, 2, 2, -2, -2, 6, -1, -3, -2, 2));

        System.out.println(findCountOfSubarrayWithSumZero(input));
        System.out.println(findCountOfSubarrayWithSumZero1(input));
        System.out.println(findCountOfSubarrayWithSumZero2(input));
        System.out.println(findCountOfSubarrayWithSumZerov1(input));
        System.out.println(findCountOfSubarrayWithSumZerov2(input));
    }

    //TC: O(n), where n is the size of the input list
    //SC: O(n), for storing prefix sums in the HashMap
    private static int findCountOfSubarrayWithSumZerov2(List<Integer> input) {
        Map<Long, Integer> sumFrequency = new HashMap<>();
        long currSum = 0L;
        sumFrequency.put(0L, 1); // To handle subarrays starting from index 0
        int count = 0;
        for (int num : input) {
            currSum += num;
            sumFrequency.put(currSum, sumFrequency.getOrDefault(currSum, 0) + 1);
        }
        for (int freq : sumFrequency.values()) {
            if (freq > 1) {
                count += (freq * (freq - 1)) / 2;
            }
        }
        return count;
    }

    private static int findCountOfSubarrayWithSumZerov1(List<Integer> input) {
        List<Long> prefix = new ArrayList<>();
        prefix.add(0L);
        for (int i = 0; i < input.size(); i++) {
            prefix.add(input.get(i) + prefix.get(i));
        }
        Map<Long, Integer> sumFrequency = new HashMap<>();
        for (Long currSum : prefix) {
            sumFrequency.put(currSum, sumFrequency.getOrDefault(currSum, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Long, Integer> entry : sumFrequency.entrySet()) {
            if (entry.getValue() > 1) {
                int frequency = entry.getValue();
                count += (frequency * (frequency - 1)) / 2;
            }
        }
        return count;
    }

    private static int findCountOfSubarrayWithSumZero2(List<Integer> input) {
        Map<Integer, Integer> prevSum = new HashMap<>();
        int currSum = 0;
        int res = 0;
        for (Integer num : input) {
            currSum += num;
            if (currSum == 0) {
                res += 1;
            }
            if (prevSum.containsKey(currSum)) {
                res += prevSum.get(currSum);
            }
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }
        return res;
    }

    // not working as the ans is 6 for 1, 2, -3, 2, 2, -2, -2, 6, -1, -3
    private static int findCountOfSubarrayWithSumZero1(List<Integer> A) {
        int count = 0;
        List<Long> prefix = new ArrayList<>();
        prefix.add((long) A.get(0));
        for (int i = 1; i < A.size(); i++) {
            prefix.add(A.get(i) + prefix.get(i - 1));
        }
        Set<Long> set = new HashSet<>();
        for (Long item : prefix) {
            if (item == 0) {
                count++;
            }
            set.add(item);
        }
        count += prefix.size() - set.size();
        return count;
    }

    //incorrect solution, as it is returning 4 for 1, 2, -3, 2, 2, -2, -2, 6, -1, -3
    private static int findCountOfSubarrayWithSumZero(List<Integer> A) {
        Set<Long> set = new HashSet<>();
        long sum = 0L;
        int count = 0;
        for (Integer item : A) {
            sum += item;
            if (set.contains(sum) || sum == 0L) {
                count++;
            }
            set.add(sum);
        }
        return count;
    }

}
