package com.dsaproblems.DSAProblems.hashing01;

import java.util.HashMap;
import java.util.Map;

public class CheckPalindromeII {

    public static void main(String[] args) {
        String A = "vnpypznzpfxyivpppxfpp";
        System.out.println(checkPalidromeIfRearrangedv1(A));
        System.out.println(checkPalidromeIfRearrangedv2(A));
        System.out.println(checkPalidromeIfRearrangedv3(A));
    }

    //input consists of lowercase characters
    private static int checkPalidromeIfRearrangedv3(String A) {
        int[] freq = new int[26];
        for (int i = 0; i < A.length(); i++)
            freq[A.charAt(i) - 'a']++;
        int odd = 0;
        for (int a : freq)
            if (a % 2 == 1)
                odd++;
        return odd > 1 ? 0 : 1;
    }

    //Yes, checkPalidromeIfRearrangedv1 can be simplified by removing the separate even/odd length
    // handling. The palindrome rearrangement condition is: at most one character can have an odd
    // frequency.
    private static int checkPalidromeIfRearrangedv2(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character alphabet : input.toCharArray()) {
            map.put(alphabet, map.getOrDefault(alphabet, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                count++;
                if (count > 1) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private static int checkPalidromeIfRearrangedv1(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character alphabet : input.toCharArray()) {
            map.put(alphabet, map.getOrDefault(alphabet, 0) + 1);
        }
        if (input.length() % 2 == 0) {
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 != 0) {
                    return 0;
                }
            }
            return 1;
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                count++;
                if (count > 1) {
                    return 0;
                }
            }
        }
        return 1;
    }

}
