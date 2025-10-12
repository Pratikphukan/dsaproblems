package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DoubleCharacterTrouble {

    public static void main(String[] args) {
        String s = "abckkcbam";
        // "acbbck", "abckkcbam", "acbbck"
        System.out.println(removeConsecutiveDuplicatesv1(s));
        System.out.println(removeConsecutiveDuplicatesv2(s));
    }

    private static String removeConsecutiveDuplicatesv1(String A) {
        Deque<Character> stack = new LinkedList<>();
        Character currChar = null;
        for (int i = 0; i < A.length(); i++) {
            currChar = A.charAt(i);
            if (!stack.isEmpty() && stack.peekFirst() == currChar) {
                stack.pollFirst();
            } else
                stack.offerFirst(currChar);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.peekFirst());
            stack.pollFirst();
        }
        return sb.reverse().toString();
    }

    private static String removeConsecutiveDuplicatesv2(String A) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : A.toCharArray()) {
            if (!stack.isEmpty() && stack.peekFirst() == c) {
                stack.pollFirst();
            } else {
                stack.addFirst(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
