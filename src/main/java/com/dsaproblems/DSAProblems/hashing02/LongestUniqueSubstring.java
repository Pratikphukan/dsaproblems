package com.dsaproblems.DSAProblems.hashing02;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {

    public static void main(String[] args) {
        //bbbbb
        //abac
        //pwwkew
        //abba
        String input = "pwwkew";
        System.out.println(findLongestUniqueSubstringv1(input));
        System.out.println(findLongestUniqueSubstringv2(input));
    }

    private static int findLongestUniqueSubstringv2(String input) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0, start = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (map.containsKey(c) && map.get(c) >= start) {
                start = map.get(c) + 1;
            }
            map.put(c, i);
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    private static int findLongestUniqueSubstringv1(String input) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int ans = 0;
        int end = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!map.containsKey(c)) {
                end = i;
            } else {
                ans = Math.max(ans, end - start + 1);
                start = map.get(c) + 1; //update start to the next index after the last occurrence of c
            }
            map.put(input.charAt(i), i);
        }
        ans = Math.max(ans, end - start + 1);
        return ans;
    }
}
