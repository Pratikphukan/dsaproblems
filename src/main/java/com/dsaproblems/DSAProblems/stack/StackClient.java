package com.dsaproblems.DSAProblems.stack;

public class StackClient {
	public static void main(String[] args) {
		Stack stack = new LinkedListStack();

		stack.push(12);
		stack.push(5);
		stack.push(34);
		stack.push(42);
		stack.push(91);
		stack.push(9);

		System.out.println(stack.pop());
		stack.push(39);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
