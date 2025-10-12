package com.dsaproblems.DSAProblems.stack;

import java.util.Deque;
import java.util.LinkedList;

public class RedundantBraces {

	public static void main(String[] args) {
		String input = "((a+b))";
		// (a+(b)/c)
		// ((a+b))
		System.out.println(checkRedundantBracesv1(input));
		System.out.println(checkRedundantBracesv2(input));
		System.out.println(checkRedundantBracesv3(input));
	}

	// working code->nothing changed in performance
	private static int checkRedundantBracesv3(String input) {
		Deque<Character> stack = new LinkedList<>();
		char ch;
		boolean flag = false;
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(')
				stack.offerFirst(ch);
			else if (ch == ')') {
				if (stack.peekFirst() == '(') {
					flag = true;
				}
				while (stack.peekFirst() != '(') {
					stack.pollFirst();
				}
				stack.pollFirst();
			}
		}
		return flag ? 1 : 0;
	}

	// working code-> no change in performance
	private static int checkRedundantBracesv2(String input) {
		Deque<Character> stack = new LinkedList<>();
		Character peakChar = null;
		char ch;
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == ')') {
				peakChar = stack.pollFirst();
				boolean flag = true;
				while (peakChar != '(') {
					if (peakChar == '+' || peakChar == '-' || peakChar == '*' || peakChar == '/')
						flag = false;
					peakChar = stack.pollFirst();
				}
				if (flag) {
					return 1;
				}
			} else {
				stack.offerFirst(ch);
			}
		}
		return 0;
	}

	private static int checkRedundantBracesv1(String input) {
		Deque<Character> stack = new LinkedList<>();
		char[] inputArr = input.toCharArray();
		Character peakChar = null;
		for (char ch : inputArr) {
			if (ch == ')') {
				peakChar = stack.pollFirst();
				boolean flag = true;
				while (peakChar != '(') {
					if (peakChar == '+' || peakChar == '-' || peakChar == '*' || peakChar == '/')
						flag = false;
					peakChar = stack.pollFirst();
				}
				if (flag) {
					return 1;
				}
			} else {
				stack.offerFirst(ch);
			}
		}
		return 0;
	}

}
