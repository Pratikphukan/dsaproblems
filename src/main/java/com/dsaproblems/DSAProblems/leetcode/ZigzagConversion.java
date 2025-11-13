package com.dsaproblems.DSAProblems.leetcode;

public class ZigzagConversion {

    public static void main(String[] args) {
        String input = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convertv1(input, numRows));
        System.out.println(convertv2(input, numRows));
    }

    private static String convertv2(String s, int r) {
        int n = s.length();
        if (r == 1 || r >= n) return s;
        StringBuilder[] rows = new StringBuilder[r];
        for (int i = 0; i < r; i++) rows[i] = new StringBuilder();
        int currRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows[currRow].append(c);
            if (currRow == 0 || currRow == r - 1) goingDown = !goingDown;
            currRow += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder(n);
        for (StringBuilder row : rows) result.append(row);
        return result.toString();
    }

    //asymptotically the method is optimal: it does a single pass over the input
    // (O(n) time) and uses O(n) extra space for the output
    // (plus O(r) for row builders), which is unavoidable since you must
    // produce the rearranged string
    private static String convertv1(String s, int r) {
        if (r == 1 || r >= s.length()) return s;
        StringBuilder[] rows = new StringBuilder[r];
        for (int i = 0; i < r; i++) {
            rows[i] = new StringBuilder();
        }
        int currRow = 0;
        boolean goingDown = false;
        for (int i = 0; i < s.length(); i++) {
            rows[currRow].append(s.charAt(i));
            if (currRow == 0 || currRow == r - 1) {
                goingDown = !goingDown;
            }
            currRow += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
