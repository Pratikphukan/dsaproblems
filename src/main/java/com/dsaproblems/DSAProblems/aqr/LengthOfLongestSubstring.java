package com.dsaproblems.DSAProblems.aqr;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        //bbbbb
        //abcabcbb
        String input = "bbbbb";
        System.out.println(findLengthOfLongestUniqueSubstringv1(input));
        System.out.println(findLengthOfLongestUniqueSubstringv2(input));
    }

    private static int findLengthOfLongestUniqueSubstringv2(String input) {
        int result = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        int left = 0, right = 0;
        while (right < input.length()) {
            char c = input.charAt(right);
            if (lastSeen.containsKey(c)) {
                left = Math.max(lastSeen.get(c), left);
            }
            result = Math.max(result, right - left + 1);
            lastSeen.put(c, right + 1);
            right++;
        }
        return result;
    }

    private static int findLengthOfLongestUniqueSubstringv1(String input) {
        Map<Character, Integer> charFreq = new HashMap<>();
        int result = 0;
        int left = 0;
        int right = 0;
        char right_char;
        char left_char;
        while (right < input.length()) {
            right_char = input.charAt(right);
            charFreq.put(right_char, charFreq.getOrDefault(right_char, 0) + 1);
            while (charFreq.get(right_char) > 1) {
                left_char = input.charAt(left);
                charFreq.put(left_char, charFreq.get(left_char) - 1);
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

}
