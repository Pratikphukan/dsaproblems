package com.dsaproblems.DSAProblems.advancedJava.revision;

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

    //TC:O(n), where n is the length of the input string
    //SC:O(n), for the stack and the output string builder in the worst case
    private static String removeConsecutiveDuplicatesv1(String A) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < A.length(); i++) {
            char currChar = A.charAt(i);
            if (!stack.isEmpty() && stack.peekFirst() == currChar) { //if the current character that we are processing, already has an entry at the top of the stack, remove it
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
