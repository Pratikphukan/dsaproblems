package com.dsaproblems.DSAProblems.intermediate;

import java.util.*;

public class NBy3RepeatNumber {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 1, 2));
        System.out.println(getNBy3RepeatedNumberv1(input));
    }

    //working solution, similar to MajorityElement
    private static int getNBy3RepeatedNumberv1(List<Integer> input) {
        int currFrequency = 0;
        int minOccurrences = input.size() / 3;
        Map<Integer, Integer> elementToFrequency = new HashMap<>();
        for (int item : input) {
            currFrequency = elementToFrequency.getOrDefault(item, 0) + 1;
            if (currFrequency > minOccurrences) {
                return item;
            }
            elementToFrequency.put(item, currFrequency);
        }
        return -1;
    }
}
