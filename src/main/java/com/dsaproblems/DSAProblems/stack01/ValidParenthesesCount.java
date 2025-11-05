package com.dsaproblems.DSAProblems.stack01;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthesesCount {

    public static void main(String[] args) {

        //()())(()())
        //(()
        //(()())()
        //)()())
        String A = ")()())";

        System.out.println(findLengthOfValidParenthesesv1(A));
        System.out.println(findLengthOfValidParenthesesv2(A));
    }

    //working code, O(n) time and O(n) space
    private static int findLengthOfValidParenthesesv2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(-1);
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.addFirst(i);
            } else {
                stack.pollFirst(); //pop one index to try to match a previous '('
                if (stack.isEmpty()) {
                    stack.addFirst(i); //push the current index i as a new base boundary (no unmatched '(' to pair with)
                } else {
                    //a valid substring ends at i; its length is i - stack.peek() where stack.peek() is the index just before the current valid block
                    maxLen = Math.max(maxLen, i - stack.peekFirst());
                }
            }
        }
        return maxLen;
    }

    private static int findLengthOfValidParenthesesv1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.addFirst(c);
                count++;
            } else if (stack.isEmpty() || stack.peekFirst() != '(') {
                maxCount = Math.max(maxCount, count);
                count = 0;
            } else {
                stack.pollFirst();
                count++;
            }
        }
        return Math.max(maxCount, count);
    }
}
