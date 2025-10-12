package com.dsaproblems.DSAProblems.intermediate;

import java.util.Arrays;

public class LittlePonyAnd2Subsequence {

    public static void main(String[] args) {

        String s = "abcdsfhjagj";
        System.out.println(findSmallestSubsequencev1(s));
        System.out.println(findSmallestSubsequencev2(s));
    }

    //working solution
    private static String findSmallestSubsequencev2(String A) {
        int n = A.length();
        char min1 = 'z' + 1;
        int idx1 = -1;
        // Find the first smallest character
        for (int i = 0; i < n - 1; i++) {
            if (A.charAt(i) < min1) {
                min1 = A.charAt(i);
                idx1 = i;
            }
        }
        // Find the next smallest character after idx1
        char min2 = 'z' + 1;
        for (int i = idx1 + 1; i < n; i++) {
            if (A.charAt(i) < min2) {
                min2 = A.charAt(i);
            }
        }
        return "" + min1 + min2;
    }

    //not working solution
    private static String findSmallestSubsequencev1(String A) {
        StringBuilder ans = new StringBuilder();
        getMinSeq(A, ans);
        return String.valueOf(ans);
    }

    private static void getMinSeq(String str, StringBuilder ans) {
        if (str.length() == 1) {
            ans.append(str.charAt(0));
            return;
        }
        int n = str.length();
        char min = str.charAt(0);
        int oldMinI = 0;
        for (int i = 1; i < n; i++) {
            char ch = str.charAt(i);
            if (ch <= min && oldMinI != i) {
                oldMinI = i;
                ans.append(ch);
            }
            min = min <= ch ? min : ch;

            if (ans.length() > 2) {
                ans.deleteCharAt(0);
            }
        }
        if (ans.length() < 2) {
            getMinSeq(str.substring(oldMinI + 1), ans);
        }
    }
}
