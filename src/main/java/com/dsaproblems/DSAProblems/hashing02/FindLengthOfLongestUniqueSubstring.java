package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLengthOfLongestUniqueSubstring {

    public static void main(String[] args) {
        String A = "cbaabcbb";
        // String A = "dadbc";
        // String A = "kdadbc";
        // String A = "abcbkfgh";// abcb,abca
        // String A = "abcdd";
        System.out.println(findLengthOfLongestUniqueSubstring(A));
        System.out.println(findLengthOfLongestUniqueSubstringv1(A));
        System.out.println(findLengthOfLongestUniqueSubstringv2(A));
        System.out.println(findLengthOfLongestUniqueSubstringv3(A));
    }

    private static int findLengthOfLongestUniqueSubstringv3(String A) {
        int len = A.length();
        int maxlength = 0, start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < len; end++) {
            char item = A.charAt(end);
            if (map.containsKey(item)) {
                start = Math.max(map.get(item) + 1, start); //increment start index by 1 of the last occurrence of the item
            }
            map.put(item, end);
            maxlength = Math.max(maxlength, end - start + 1);
        }
        return maxlength;
    }

    // wrong code
    private static int findLengthOfLongestUniqueSubstringv1(String input) {
        Set<Character> set = new HashSet<>();
        Character c = null;
        int maxLength = 0;
        int j = 0;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                maxLength = Math.max(maxLength, set.size());
            } else {
                set.remove(input.charAt(j));
                j++;
            }
        }
        return maxLength;
    }

    // working code
    private static int findLengthOfLongestUniqueSubstringv2(String input) {
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int j = 0;
        int i = 0;
        Character c = null;
        while (i < input.length()) {
            c = input.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                maxLength = Math.max(maxLength, set.size());
                i++;
            } else {
                set.remove(input.charAt(j));
                j++;
            }
        }
        return maxLength;
    }

    private static int findLengthOfLongestUniqueSubstring(String A) {
        Map<Character, Integer> map = new HashMap<>();
        int startIdx = 0;
        int endIdx = 0;
        int maxLength = 0;
        for (; endIdx < A.length(); endIdx++) {
            char item = A.charAt(endIdx);
            if (map.containsKey(item))
                startIdx = Math.max(startIdx, map.get(item) + 1);
            map.put(item, endIdx);
            maxLength = Math.max(maxLength, endIdx - startIdx + 1);
        }
        return maxLength;
    }

}
