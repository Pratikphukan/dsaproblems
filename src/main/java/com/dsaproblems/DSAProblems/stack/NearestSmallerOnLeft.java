package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class NearestSmallerOnLeft {

	public static void main(String[] args) {
		// 6,10,11,12,7
		List<Integer> A = new ArrayList<>(Arrays.asList(4, 2, 5, 10, 8, 2));
		System.out.println(nearestSmallerOnLeft(A));
		System.out.println(nearestSmallerOnLeft1(A));
	}

	private static List<Integer> nearestSmallerOnLeft1(List<Integer> A) {
		List<Integer> ans = new ArrayList<>();
		Deque<Integer> possibleAnswers = new ArrayDeque<>();
		for (int i = 0; i < A.size(); i++) {
			if (possibleAnswers.peek() < A.get(i)) {
				ans.add(possibleAnswers.peek());
			} else {
				while (!possibleAnswers.isEmpty() && possibleAnswers.peek() >= A.get(i)) {
					possibleAnswers.poll();
				}
				if (possibleAnswers.isEmpty()) {
					ans.add(-1);
				} else {
					ans.add(possibleAnswers.peek());
				}
			}
			possibleAnswers.push(A.get(i));
		}
		return ans;
	}

	private static List<Integer> nearestSmallerOnLeft(List<Integer> A) { // working code, but it will give TLE
		List<Integer> ans = new ArrayList<>();
		ans.add(-1);
		for (int i = 1; i < A.size(); i++) {
			boolean statusForCurrent = false;
			for (int j = i - 1; j >= 0; j--) {
				if (A.get(j) < A.get(i)) {
					ans.add(A.get(j));
					statusForCurrent = true;
					break;
				}
			}
			if (!statusForCurrent)
				ans.add(-1);
		}
		return ans;
	}

}
