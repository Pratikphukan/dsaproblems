package com.dsaproblems.DSAProblems.stack;

import java.util.Stack;

public class SortStack {

	public static void main(String[] args) {
		Stack<Integer> stack1 = new Stack<Integer>();
		stack1.push(10);
		stack1.push(7);
		stack1.push(11);
		stack1.push(9);
		stack1.push(6);
		System.out.println(stack1);
		System.out.println(sortStack(stack1));
	}

	private static Stack<Integer> sortStack(Stack<Integer> stack1) {
		if (stack1.size() <= 1) {
			return stack1;
		}
		Stack<Integer> stack2 = new Stack<>();
		int len = stack1.size();// very important
		for (int i = 0; i < len / 2; i++) {
			stack2.push(stack1.pop());
		}
		// System.out.println(st);
		stack1 = sortStack(stack1);
		stack2 = sortStack(stack2);
		return merge(stack1, stack2);
	}

	private static Stack<Integer> merge(Stack<Integer> stack1, Stack<Integer> stack2) {
		Stack<Integer> ans = new Stack<Integer>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			int a = stack1.peek(), b = stack2.peek();
			if (a > b) {
				ans.push(stack1.pop());
			} else {
				ans.push(stack2.pop());
			}
		}
		while (!stack1.isEmpty()) {
			ans.push(stack1.pop());
		}
		while (!stack2.isEmpty()) {
			ans.push(stack2.pop());
		}
		ans = reverseStack(ans);
		return ans;
	}

	private static Stack<Integer> reverseStack(Stack<Integer> stack) {
		Stack<Integer> ans = new Stack<Integer>();
		while (!stack.isEmpty()) {
			ans.push(stack.pop());
		}
		System.out.println(ans);
		return ans;
	}

}
