package com.dsaproblems.DSAProblems.stack01;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class BalancedParenthesis {

	public static void main(String[] args) {
		String s = "{([])}";
		System.out.println(isBalancedParanthesis1(s));
		System.out.println(isBalancedParanthesis2("()"));
		System.out.println(isBalancedParanthesis3("()"));
		System.out.println(isBalancedParanthesis4(s));
	}

	private static int isBalancedParanthesis4(String A) {
		Deque<Character> stack = new LinkedList<>();
		for (int i = 0; i < A.length(); i++) {
			char c = A.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.addFirst(c);
			} else if (stack.isEmpty()) {
				return 0;
			} else if ((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{')
					|| (c == ']' && stack.peek() == '[')) {
				stack.pollFirst();
			} else {
				return 0;
			}
		}
		if (stack.isEmpty())
			return 1;
		return 0;
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
