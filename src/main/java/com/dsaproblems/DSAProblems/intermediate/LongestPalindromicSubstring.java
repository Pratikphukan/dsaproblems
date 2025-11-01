package com.dsaproblems.DSAProblems.intermediate;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String input = "aaaabaaa";
        System.out.println(getLongestPalindromicSubstringv1(input));
        System.out.println(getLongestPalindromicSubstringv2(input));
        System.out.println(getLongestPalindromicSubstringv3(input));
        System.out.println(getLongestPalindromicLengthv1(input));
        System.out.println(getLongestPalindromicLengthv2(input));
    }

    private static int getLongestPalindromicLengthv1(String input) {
        if (input == null) return 0;
        int n = input.length();
        if (n < 2) return n;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenterLen(input, i, i);     // odd
            int len2 = expandAroundCenterLen(input, i, i + 1); // even
            maxLen = Math.max(maxLen, Math.max(len1, len2));
        }
        return maxLen;
    }

    private static int expandAroundCenterLen(String s, int left, int right) {
        if (s == null) return 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    //working code
    private static int getLongestPalindromicLengthv2(String s) {
        if (s == null) return 0;
        if (s.length() < 2) return s.length();

        StringBuilder t = new StringBuilder();
        t.append('^');
        for (int i = 0; i < s.length(); i++) {
            t.append('#');
            t.append(s.charAt(i));
        }
        t.append("#$");
        String transformed = t.toString();

        int n = transformed.length();
        int[] p = new int[n];
        int center = 0, right = 0;
        int maxLen = 0;

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            if (right > i) {
                p[i] = Math.min(right - i, p[mirror]);
            } else {
                p[i] = 0;
            }
            while (transformed.charAt(i + 1 + p[i]) == transformed.charAt(i - 1 - p[i])) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            // p[i] corresponds to length in original string
            if (p[i] > maxLen) maxLen = p[i];
        }
        return maxLen;
    }

    //working code
    private static String getLongestPalindromicSubstringv3(String input) {
        if (input == null || input.length() < 2) return input;
        int start = 0, maxLen = 1, n = input.length();

        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(input, i, i);     // odd-length
            int len2 = expandAroundCenter(input, i, i + 1); // even-length
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return input.substring(start, start + maxLen);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // length of palindrome
    }

    private static String getLongestPalindromicSubstringv2(String s) {
        if (s == null || s.length() < 2) return s;

        // Transform: ^ # a # b # ... # $
        StringBuilder t = new StringBuilder();
        t.append('^');
        for (int i = 0; i < s.length(); i++) {
            t.append('#');
            t.append(s.charAt(i));
        }
        t.append("#$");
        String transformed = t.toString();

        int n = transformed.length();
        int[] p = new int[n]; // radius array
        int center = 0, right = 0;
        int maxLen = 0, centerIndex = 0;

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            if (right > i) {
                p[i] = Math.min(right - i, p[mirror]);
            } else {
                p[i] = 0;
            }
            // expand around i
            while (transformed.charAt(i + 1 + p[i]) == transformed.charAt(i - 1 - p[i])) {
                p[i]++;
            }
            // update center/right
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            // track max
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2; // map back to original string
        return s.substring(start, start + maxLen);
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
