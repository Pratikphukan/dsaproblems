package com.dsaproblems.DSAProblems.intermediate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AmazingSubarrays {

    public static void main(String[] args) {
        String input = "ABEC";
        System.out.println(getNoOfAmazingSubarrays(input));
    }

    //working code
    private static int getNoOfAmazingSubarrays(String input) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int sum = 0;
        int len = input.length();
        for (int i = 0; i < len; i++) {
            if (vowels.contains(input.charAt(i))) {
                sum += len - i;
                sum %= 10003;
            }
        }
        return sum;
    }
}
