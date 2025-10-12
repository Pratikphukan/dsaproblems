package com.dsaproblems.DSAProblems.advancedJava.revision;

import java.util.*;

public class MajorityElement {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 2));
        System.out.println(findMajorityElementv1(input));
        System.out.println(findMajorityElementv2(input));
        System.out.println(findMajorityElementv3(input));
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
