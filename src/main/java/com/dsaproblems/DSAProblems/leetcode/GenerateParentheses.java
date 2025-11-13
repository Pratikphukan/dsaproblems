package com.dsaproblems.DSAProblems.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesisv1(3));
        System.out.println(generateParenthesisv2(3));
    }

    private static List<String> generateParenthesisv2(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        char[] buf = new char[2 * n];
        backtrack(result, buf, 0, 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, char[] buf, int pos, int open, int close, int n) {
        if (pos == buf.length) {
            result.add(new String(buf));
            return;
        }
        if (open < n) {
            buf[pos] = '(';
            backtrack(result, buf, pos + 1, open + 1, close, n);
        }
        // Only add a closing parenthesis if there is an unmatched opening one.
        if (close < open) {
            buf[pos] = ')';
            backtrack(result, buf, pos + 1, open, close + 1, n);
        }
    }

    private static List<String> generateParenthesisv1(int n) {
        List<String> result = new ArrayList<>();

        // Check if n is 0. Although constraints guarantee n>=1, it's good to have a check.
        if (n <= 0) {
            return result; // Return empty list if input is invalid.
        }

        // Call the backtracking helper function starting with an empty string.
        backtrack(result, "", 0, 0, n);

        // Return the list containing all valid parentheses combinations.
        return result;
    }

    private static void backtrack(List<String> result, String current, int openCount, int closeCount, int n) {
        if (current.length() == n * 2) {
            result.add(current); // Add the valid combination to the result list.
            return; // End recursion once a valid combination is formed.
        }
        if (openCount < n) {
            // Recursively call backtrack with one more open parenthesis added.
            backtrack(result, current + "(", openCount + 1, closeCount, n);
        }
        if (closeCount < openCount) {
            // Recursively call backtrack with one more close parenthesis added.
            backtrack(result, current + ")", openCount, closeCount + 1, n);
        }
    }
}
