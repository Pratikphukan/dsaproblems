package com.dsaproblems.DSAProblems.stack;

import java.util.Deque;
import java.util.LinkedList;

public class SortStack1 {

	public static void main(String[] args) {
		Deque<Integer> stack1 = new LinkedList<>();
		stack1.addFirst(10);
		stack1.addFirst(7);
		stack1.addFirst(11);
		stack1.addFirst(9);
		stack1.addFirst(6);
		System.out.println(stack1);
		System.out.println(sortStack(stack1));
	}

	private static Deque<Integer> sortStack(Deque<Integer> stack) {
		if (stack.size() <= 1) {
			return stack;
		}
		Deque<Integer> stack1 = new LinkedList<>();
		Deque<Integer> stack2 = new LinkedList<>();
		int len = stack.size();
		for (int i = 0; i < len; i++) {
			if (i < len / 2) {
				stack1.addFirst(stack.removeFirst());
			} else {
				stack2.addFirst(stack.removeFirst());
			}
		}
		stack1 = sortStack(stack1);
		stack2 = sortStack(stack2);
		return merge(stack1, stack2);

	}

	private static Deque<Integer> merge(Deque<Integer> stack1, Deque<Integer> stack2) {
		Deque<Integer> ans = new LinkedList<>();
		while (!(stack1.isEmpty() || stack2.isEmpty())) {
			int a = stack1.peekFirst(), b = stack2.peekFirst();
			if (a < b) {
				ans.addFirst(stack1.removeFirst());
			} else {
				ans.addFirst(stack2.removeFirst());
			}
		}
		while (!stack1.isEmpty()) {
			ans.addFirst(stack1.removeFirst());
		}
		while (!stack2.isEmpty()) {
			ans.addFirst(stack2.removeFirst());
		}
		ans = reverseStack(ans);
		return ans;
	}

	private static Deque<Integer> reverseStack(Deque<Integer> stack) {
		Deque<Integer> ans = new LinkedList<>();
		while (!stack.isEmpty()) {
			ans.addFirst(stack.removeFirst());
		}
		System.out.println(ans);
		return ans;
	}

}
