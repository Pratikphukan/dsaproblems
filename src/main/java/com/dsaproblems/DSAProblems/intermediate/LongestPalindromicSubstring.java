package com.dsaproblems.DSAProblems.intermediate;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input = "aaaabaaa";
        System.out.println(getLongestPalindromicSubstringv1(input));
    }

    //working code, the method uses the "expand around center"
    private static String getLongestPalindromicSubstringv1(String input) {
        int len = input.length();
        int[] evenPalindromicSubstringLength = null;
        int[] oddPalindromicSubstringLength = null;
        int start = 0;
        int max = 1;
        for (int i = 1; i < len; i++) {
            evenPalindromicSubstringLength = getPalindromicSubstringLength(i - 1, i, input);
            if (evenPalindromicSubstringLength[1] > max) {
                max = evenPalindromicSubstringLength[1];
                start = evenPalindromicSubstringLength[0];
            }
            oddPalindromicSubstringLength = getPalindromicSubstringLength(i - 1, i + 1, input);
            if (oddPalindromicSubstringLength[1] > max) {
                max = oddPalindromicSubstringLength[1];
                start = oddPalindromicSubstringLength[0];
            }
        }
        return input.substring(start, start + max);
    }

    static int[] getPalindromicSubstringLength(int low, int high, String input) {
        while (low >= 0 && high < input.length() && input.charAt(low) == input.charAt(high)) {
            low--;
            high++;
        }
        return new int[]{low + 1, high - low - 1};
    }
}
