package com.dsaproblems.DSAProblems.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class RemoveConsecutiveDuplicates {

	public static void main(String[] args) {
		String s = "abckkcbam";
		// "acbbck", "abckkcbam", "acbbck"
		System.out.println(removeConsecutiveDuplicatesv1(s));
		System.out.println(removeConsecutiveDuplicatesv2(s));
	}

	// working code
	private static String removeConsecutiveDuplicatesv1(String s) {
		Deque<Character> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!stack.isEmpty() && stack.peekFirst() == c) { // comparing with the most recently pushed element
				stack.removeFirst();
				continue;
			}
			stack.addFirst(c);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		return sb.toString();
	}

	// working code-> faster
	private static String removeConsecutiveDuplicatesv2(String A) {
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
}
