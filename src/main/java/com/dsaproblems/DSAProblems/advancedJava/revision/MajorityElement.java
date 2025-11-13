package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class MajorityElement {

    public static void main(String[] args) {
        //2,2,1,1,1,2,2
        //2, 1, 2
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 1, 1, 1, 2, 2));
        System.out.println(findMajorityElementv1(input));
        System.out.println(findMajorityElementv2(input));
        System.out.println(findMajorityElementv3(input));
        System.out.println(findMajorityElementv4(input));
    }

    //working
    private static int findMajorityElementv3(List<Integer> input) {
        int minOccurrences = input.size() / 2;
        int ans = input.get(0);
        Map<Integer, Integer> elementToFrequency = new HashMap<>();
        for (int item : input) {
            int currFrequency = elementToFrequency.getOrDefault(item, 0) + 1;
            if (currFrequency > minOccurrences) {
                ans = item;
            }
            elementToFrequency.put(item, currFrequency);
        }
        return ans;
    }

    //The method implements the Boyer-Moore Voting Algorithm in two phases:
    // (1) scan to pick a candidate that might be the majority by tracking a running count;
    // (2) verify that the candidate actually appears more than n/2 times.
    // Time O(n), extra space O(1)
    private static int findMajorityElementv4(List<Integer> input) {
        int n = input.size();
        // Phase 1: find candidate
        Integer candidate = null;
        int count = 0;
        for (int num : input) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // Phase 2: verify candidate
        if (candidate == null) return -1;
        count = 0;
        for (int num : input) {
            if (num == candidate) count++;
        }
        return (count > n / 2) ? candidate : -1;
    }

    //working
    private static int findMajorityElementv2(List<Integer> input) {
        int maj = input.get(0);
        int n = input.size();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (input.get(i) == maj) {
                count++;
            } else if (count == 1) {
                maj = input.get(i);
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : input) {
            if (num == maj)
                count++;
        }
        if (count > n / 2)
            return maj;
        return -1;
    }

    //working code, Time and space complexity remain O(n)
    private static Integer findMajorityElementv1(List<Integer> input) {
        int minOccurrences = input.size() / 2;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : input) {
            int count = freq.getOrDefault(num, 0) + 1;
            if (count > minOccurrences) return num;
            freq.put(num, count);
        }
        return -1;
    }

}
