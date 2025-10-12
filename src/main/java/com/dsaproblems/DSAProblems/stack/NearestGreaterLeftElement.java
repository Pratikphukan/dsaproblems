package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NearestGreaterLeftElement {

	public static void main(String[] args) {
		List<Integer> a = List.of(39, 27, 11, 4, 24, 32, 32, 1);
		// (39, 27, 11, 4, 24, 32, 32, 1)
		// (4, 5, 2, 10, 8)
		// (6,10,11,12,7)
		ArrayList<Integer> A = new ArrayList<>(a);
		System.out.println(nearestGreaterElements(A));
	}

	// for values
	private static ArrayList<Integer> nearestGreaterElements(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int item = A.get(i);
			while (!stack.isEmpty() && item >= stack.peek()) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				ans.add(-1);
			} else {
				ans.add(stack.peek());
			}
			stack.push(item);
		}
		System.out.println(stack);
		return ans;
	}

	// for indices
	private static ArrayList<Integer> nearestGreaterIndices1(ArrayList<Integer> A) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.size(); i++) {
			int item = A.get(i);
			while (!stack.isEmpty() && item >= A.get(stack.peek())) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				ans.add(-1);
			} else {
				ans.add(stack.peek());
			}
			stack.push(i);
		}
		System.out.println(stack);
		return ans;
	}

}
