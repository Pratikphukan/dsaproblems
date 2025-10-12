package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpecialMedian {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(12, 3, 5, 7, 4, 26));
		// 12, 3, 5, 7, 4, 26
		// 12, 3, 5, 7, 4, 19, 26
		System.out.println(findMedianOfInput(input));
		System.out.println(findMedianOfInputv1(input));
	}

	private static int findMedianOfInputv1(List<Integer> input) {
		int len = input.size();
		int a = -1;
		int b = -1;
		int ans = 0;
		if (len % 2 == 1) {
			// medianUtility(input, 0, len - 1, len / 2, a, b);
			ans = b;
		} else {
			// medianUtility(input, 0, len - 1, len / 2, a, b);
			ans = (a + b) / 2;
		}
		return ans;
	}

	private static Integer findMedianOfInput(List<Integer> input) {
		Collections.sort(input);
		int len = input.size();
		if (len % 2 == 1) {
			return input.get(len / 2);
		}
		return (input.get(len / 2) + input.get(len / 2 - 1)) / 2;
	}

}
