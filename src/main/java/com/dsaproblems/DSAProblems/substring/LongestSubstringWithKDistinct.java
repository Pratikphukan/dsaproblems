package com.dsaproblems.DSAProblems.substring;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinct {

    //min length of string is 1 and max is 10^5
    //B->[0,26]
    //A is only lowercase characters
    public static void main(String[] args) {
        //acbaab|2
        //adbcaa|3
        //abcbbbbcccbdddadacb|2
        String A = "acbaab";
        int B = 2;
        System.out.println(findLongestSubstringWithKDistinctv1(A, B));
    }

    //standard and efficient solution with O(n) time complexity
    private static int findLongestSubstringWithKDistinctv1(String A, int B) {
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < A.length(); right++) {
            char end = A.charAt(right);
            freq.put(end, freq.getOrDefault(end, 0) + 1);
            while (freq.size() > B) {
                char start = A.charAt(left++);
                freq.put(start, freq.get(start) - 1);
                if (freq.get(start) == 0) freq.remove(start);
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
