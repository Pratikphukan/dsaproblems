package com.dsaproblems.DSAProblems.leetcode;

import java.util.*;

public class SortCharactersByFrequency {

    public static void main(String[] args) {
        //tree
        //cccaaa
        String s = "tree";
        System.out.println(frequencySortv1(s));

    }

    //the bucket-sort approach used is asymptotically optimal for this problem: O(n) time and O(n) extra space
    //working code
    private static String frequencySortv1(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxFreq = 1; //input will atleast have one character
        for (char c : s.toCharArray()) {
            Integer freq = freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            if (freq != null && freq + 1 > maxFreq) maxFreq = freq + 1;
        }
        List<Character>[] buckets = new List[maxFreq + 1];
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            int freq = entry.getValue();
            char c = entry.getKey();
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(c);
        }
        StringBuilder result = new StringBuilder();
        for (int freq = maxFreq; freq > 0; freq--) {
            if (buckets[freq] != null) {
                for (char c : buckets[freq]) {
                    char[] repeated = new char[freq];
                    Arrays.fill(repeated, c);
                    result.append(repeated);
                }
            }
        }
        return result.toString();
    }
}
