package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class InfixToPostfix {

    public static void main(String[] args) {
        String a = "(o*h+r)";
        // "a+b*(c^d-e)^(f+g*h)-i", "a+b*c-d", "x^y/(a*z)+b"
        //'a*(r+o*h)'
        //'a*((r+o)*h)'
        //a*(o*h+r)
        System.out.println(infixToPostfixv1(a));
        System.out.println(infixToPostfixv2(a));
    }

    //working code
    private static String infixToPostfixv2(String A) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        for (char c : A.toCharArray()) {
            if (!isOperator(c)) {
                ans.append(c);
            } else if (c == '(') {
                stack.addFirst(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peekFirst() != '(') {
                    ans.append(stack.removeFirst());
                }
                stack.removeFirst(); // remove '('
            } else {
                while (!stack.isEmpty() && stack.peekFirst() != '(' &&
                        checkprecedence(c) <= checkprecedence(stack.peekFirst())) {
                    ans.append(stack.removeFirst());
                }
                stack.addFirst(c);
            }
        }
        while (!stack.isEmpty()) {
            ans.append(stack.removeFirst());
        }
        return ans.toString();
    }

    //working code
    private static String infixToPostfixv1(String A) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (isOperator(c)) {
                if (c == '(') {
                    stack.addFirst(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peekFirst() != '(') {
                        ans.append(stack.removeFirst());
                    }
                    if (!stack.isEmpty() && stack.peekFirst() == '(') {
                        stack.removeFirst(); // remove '('
                    }
                } else {
                    while (!stack.isEmpty() && stack.peekFirst() != '(' &&
                            checkprecedence(c) <= checkprecedence(stack.peekFirst())) {
                        ans.append(stack.removeFirst());
                    }
                    stack.addFirst(c);
                }
            } else {
                ans.append(c);
            }
        }
        while (!stack.isEmpty()) {
            ans.append(stack.removeFirst());
        }
        return ans.toString();
    }

    private static int checkprecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        }
        return 4;
    }

    private static boolean isOperator(char c) {
        return c == '^' || c == '/' || c == '*' || c == '+' || c == '-' || c == '(' || c == ')';
    }

}
