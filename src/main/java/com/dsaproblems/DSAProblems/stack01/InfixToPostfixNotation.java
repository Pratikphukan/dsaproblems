package com.dsaproblems.DSAProblems.stack01;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class InfixToPostfixNotation {

	public static void main(String[] args) {
		String input = "x^y/(a*z)+b";
		// "a+b*(c^d-e)^(f+g*h)-i", "a+b*c-d", "x^y/(a*z)+b"
		System.out.println(infixToPostfix1(input));
	}

	// wrong solution with brackets
	private static String infixToPostfix1(String A) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < A.length(); i++) {
			char c = A.charAt(i);
			if (isOperator(c)) {
				if (!stack.isEmpty()) {
					if (checkprecedence(c) > checkprecedence(stack.peek())) {
						stack.push(c);
					} else {
						while (!stack.isEmpty() && checkprecedence(c) <= checkprecedence(stack.peek())) {
							ans.append(stack.pop());
						}
						stack.push(c);
					}
				} else {
					stack.push(c);
				}
			} else {
				ans.append(c);
			}
		}
		while (!stack.isEmpty()) {
			ans.append(stack.pop());
		}
		return ans.toString();
	}

	// wrong solution with brackets
	private static String infixToPostfix(String input) {
		StringBuilder expression = new StringBuilder();
		Deque<Character> stack = new LinkedList<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (isOperator(c)) {
				while (!stack.isEmpty() && checkprecedence(c) <= checkprecedence(stack.peekFirst())) {
					Character poppedCharacter = stack.pollFirst();
					if (poppedCharacter != '(' && poppedCharacter != ')')
						expression.append(poppedCharacter);
				}
				stack.addFirst(c);
			} else {
				expression.append(c);
			}
		}
		while (!stack.isEmpty()) {
			expression.append(stack.pollFirst());
		}
		return expression.toString();
	}

	private static int checkprecedence(char c) {
		int precedence = 0;
		switch (c) {
		case '+', '-':
			precedence = 1;
			break;
		case '*', '/':
			precedence = 2;
			break;
		case '^':
			precedence = 3;
			break;
		default:
			precedence = 4;
			break;
		}
		return precedence;
	}

	private static boolean isOperator(char c) {
		return c == '^' || c == '/' || c == '*' || c == '+' || c == '-' || c == '(' || c == ')';
	}

}
