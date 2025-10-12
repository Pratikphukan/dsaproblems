package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

public class BalancedParanthesis {

    public static void main(String[] args) {
        String s = "{([])}";
        System.out.println(isBalancedParanthesis(s));
        System.out.println(isBalancedParanthesis1(s));
        System.out.println(isBalancedParanthesis2(s));
    }

    // WORKING CODE
    private static int isBalancedParanthesis2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else if (!stack.isEmpty() && (
                    (c == ')' && stack.peekFirst() == '(') ||
                            (c == '}' && stack.peekFirst() == '{') ||
                            (c == ']' && stack.peekFirst() == '['))) {
                stack.pollFirst();
            } else {
                return 0;
            }
        }
        if (stack.isEmpty())
            return 1;
        return 0;
    }

    // working code
    private static int isBalancedParanthesis1(String A) {
        HashMap<Character, Character> mp = new HashMap<Character, Character>();
        Stack<Character> st = new Stack<Character>();
        mp.put(')', '(');
        mp.put('}', '{');
        mp.put(']', '[');
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else if (st.empty() || st.peek() != mp.get(c)) {
                return 0;
            } else {
                st.pop();
            }
        }
        if (st.empty())
            return 1;
        return 0;
    }

    // working code
    private static boolean isBalancedParanthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c == ')' && stack.peek() == '(') {
                        stack.pop();
                    } else if (c == '}' && stack.peek() == '{') {
                        stack.pop();
                    } else if (c == ']' && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

}
