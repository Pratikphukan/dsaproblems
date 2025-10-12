package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class PassingGame {

	public static void main(String[] args) {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(5);
		stack.push(4);
		stack.push(7);
		stack.push(9);
		System.out.println(stack);
		System.out.println(stack.peek());
		System.out.println(stack.peekFirst());
		System.out.println(stack.poll());
		ArrayList<Integer> C = new ArrayList<>(Arrays.asList(86, 63, 60, 0, 47, 0, 99, 9, 0, 0));
		int A = 10;
		int B = 23;
		System.out.println(getCurrentPlayer(A, B, C));
	}

	private static Integer getCurrentPlayer(int A, int B, ArrayList<Integer> C) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (Integer id : C) {
			if (id > 0)
				stack.push(id);
			else
				stack.poll();
		}
		if (stack.isEmpty())
			return B;
		return stack.peek();
	}

}
