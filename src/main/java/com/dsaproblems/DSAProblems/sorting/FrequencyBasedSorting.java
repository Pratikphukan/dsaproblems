package com.dsaproblems.DSAProblems.sorting;

import java.util.*;

public class FrequencyBasedSorting {

    static class Element implements Comparable<Element> {
        char character;
        int frequency;

        Element(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Element element) {
            if (this.frequency == element.frequency) {
                return Character.compare(this.character, element.character);
            }
            return Integer.compare(this.frequency, element.frequency); // Sort by frequency in ascending order
        }
    }

    public static void main(String[] args) {
        String A = "book";
        System.out.println(sortedStringv1(A));
    }

    //working code
    private static String sortedStringv1(String A) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : A.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        List<Element> elements = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            elements.add(new Element(entry.getKey(), entry.getValue()));
        }
        Collections.sort(elements);
        StringBuilder result = new StringBuilder();
        for (Element e : elements) {
            for (int i = 0; i < e.frequency; i++) {
                result.append(e.character);
            }
        }
        return result.toString();
    }
}
