package com.dsaproblems.DSAProblems.intermediate;

import java.util.*;

public class ChangeCharacter {

    public static void main(String[] args) {
        //abcabbccd, 3
        //a, 1
        String input = "abcabbccd";
        int B = 3;
        System.out.println(getMinimumDistinctCharactersv1(input, B));
        System.out.println(getMinimumDistinctCharactersv2(input, B));
        System.out.println(getMinimumDistinctCharactersv3(input, B));
    }

    //failing because of an edge case
    private static int getMinimumDistinctCharactersv3(String input, int B) {
        int[] count = new int[26];
        for (char c : input.toCharArray()) {
            count[c - 'a'] += 1;
        }
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0)
                minHeap.offer(count[i]);
        }
        while (!minHeap.isEmpty() && B >= minHeap.peek()) {
            B -= minHeap.poll();
        }
        return minHeap.size();
    }

    //working solution
    private static int getMinimumDistinctCharactersv2(String input, int B) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : input.toCharArray()) {
            charFreq.merge(c, 1, Integer::sum);
        }
        // If we can change all characters except one
        if (B >= input.length() - 1) {
            return 1;
        }
        // Use PriorityQueue to keep track of frequencies in ascending order
        PriorityQueue<Integer> frequencies = new PriorityQueue<>(charFreq.values());

        // Remove characters starting from lowest frequency
        while (!frequencies.isEmpty() && B > 0) {
            int freq = frequencies.poll();
            if (B >= freq) {
                B -= freq;
            } else {
                // If we can't remove this frequency completely, put it back
                frequencies.offer(freq);
                break;
            }
        }
        return frequencies.size();
    }

    //working solution
    private static int getMinimumDistinctCharactersv1(String input, int B) {
        Map<Character, Integer> charFreq = new HashMap<>();
        int currFrequency = 0;
        for (char c : input.toCharArray()) {
            currFrequency = charFreq.getOrDefault(c, 0) + 1;
            charFreq.put(c, currFrequency);
        }
        int remainingCharactersToChange = B;
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(charFreq.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            remainingCharactersToChange -= entry.getValue();
            if (remainingCharactersToChange >= 0 && charFreq.size() > 1) {
                charFreq.remove(entry.getKey());
            }
        }
        return charFreq.size();
    }
}
