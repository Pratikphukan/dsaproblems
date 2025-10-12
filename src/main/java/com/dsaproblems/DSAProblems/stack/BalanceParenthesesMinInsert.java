package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BalanceParenthesesMinInsert {

    public static int minInsertions(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int insertions = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.addFirst('(');
            } else { // c == ')'
                if (!stack.isEmpty() && stack.peekFirst() == '(') {
                    stack.pollFirst(); // matched
                } else {
                    // no matching '(' available
                    insertions++; // insert '('
                }
            }
        }

        // any remaining '(' need a ')' each
        insertions += stack.size();

        return insertions;
    }

    public static void main(String[] args) {

        //"()))((", "))((", "())", "(((", "()()"
        System.out.println(minInsertions("))())("));
        System.out.println(minInsertionsv1("))())("));
    }

    public static int minInsertionsv1(String s) {
        int insertions = 0;
        int leftNeeded = 0; // number of '(' needed

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                leftNeeded++;
                i++;
            } else { // s.charAt(i) == ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    // Found a pair '))'
                    if (leftNeeded > 0) {
                        leftNeeded--;
                    } else {
                        insertions++; // need to insert '(' before '))'
                    }
                    i += 2;
                } else {
                    // Single ')', need to insert one more ')'
                    if (leftNeeded > 0) {
                        leftNeeded--;
                        insertions++; // need one more ')'
                    } else {
                        insertions += 2; // need to insert '(' and ')'
                    }
                    i++;
                }
            }
        }
        // Each remaining '(' needs '))'
        insertions += leftNeeded * 2;
        return insertions;
    }
}
