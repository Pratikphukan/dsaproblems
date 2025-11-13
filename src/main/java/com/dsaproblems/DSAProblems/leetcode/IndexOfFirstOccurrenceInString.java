package com.dsaproblems.DSAProblems.leetcode;

public class IndexOfFirstOccurrenceInString {

    public static void main(String[] args) {
        //leetcode|leeto
        //sadbutsad|sad
        //hello|lo
        String haystack = "hello";
        String needle = "lo";
        System.out.println(findPatternInStringv1(haystack, needle));
        System.out.println(findPatternInStringv2(haystack, needle));
    }

    private static int findPatternInStringv2(String s, String p) {
        if (p.isEmpty()) return 0;
        int n = s.length(), m = p.length();
        int[] lps = buildLps(p);
        int i = 0, j = 0;
        while (i < n) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                if (j == m) return i - j; // match found
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    private static int[] buildLps(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            if (p.charAt(i) == p.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    private static int findPatternInStringv1(String s, String p) {
        int n = s.length(), m = p.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            for (; j < m; j++) {
                if (s.charAt(i + j) != p.charAt(j)) break;
            }
            if (j == m) return i;
        }
        return -1;
    }
}
