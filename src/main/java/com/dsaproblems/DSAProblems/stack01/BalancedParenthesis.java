package com.dsaproblems.DSAProblems.stack01;

import java.util.*;

public class BalancedParenthesis {

    public static void main(String[] args) {
        String s = "{([])}";
        System.out.println(isBalancedParanthesis1(s));
        System.out.println(isBalancedParanthesis2("()"));
        System.out.println(isBalancedParanthesis3("()"));
        System.out.println(isBalancedParanthesis4(s));
        System.out.println(isBalancedParanthesisv5(s));
    }

    //working code
    private static int isBalancedParanthesisv5(String A) {
        int n = A.length();
        if ((n & 1) == 1) return 0;// odd length can't be balanced
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = A.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else {
                if (stack.isEmpty() || !matches(stack.pollFirst(), c)) return 0;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    //working code
    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    private static int isBalancedParanthesis4(String A) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : A.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else if (!stack.isEmpty() &&
                    ((c == ')' && stack.peekFirst() == '(') ||
                            (c == '}' && stack.peekFirst() == '{') ||
                            (c == ']' && stack.peekFirst() == '['))) {
                stack.pollFirst();
            } else {
                return 0;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    private static int isBalancedParanthesis3(String A) {
        Deque<Character> s = new LinkedList<>();
        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) == '(') {
                s.addFirst(A.charAt(i));
            } else if (s.isEmpty()) {
                return 0;
            } else {
                s.pollFirst();
            }
        }
        if (s.isEmpty()) {
            return 1;
        }
        return 0;
    }

    private static int isBalancedParanthesis1(String s) {
        Map<Character, Character> map = new HashMap<>();
        Deque<Character> stack = new LinkedList<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else if (stack.isEmpty() || !Objects.equals(stack.peekFirst(), map.get(c))) {
                return 0;
            } else {
                stack.pollFirst();
            }
        }
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }

    private static int isBalancedParanthesis2(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.addFirst(c);
            } else if (stack.isEmpty() || stack.peekFirst() != '(') {
                return 0;
            } else {
                stack.pollFirst();
            }
        }
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}
