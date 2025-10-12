package com.dsaproblems.DSAProblems.strings;

public class CyclicPermutations {

    public static void main(String[] args) {
        String A = "1001";
        String B = "0011";
        System.out.println(solve(A, B));
    }

    //working code
    public static int solve(String A, String B) {
        String doubledB = B + B;
        String pattern = A + "$" + doubledB;
        int[] lps = computeLPSArray(pattern);

        int count = 0;
        int patternLength = A.length();
        for (int i = 0; i < lps.length; i++) {
            if (lps[i] == patternLength && i > 2 * patternLength) {
                count++;
            }
        }
        return count;
    }

    private static int[] computeLPSArray(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
