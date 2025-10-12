package com.dsaproblems.DSAProblems.searching01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindFrequencyOfElementInArray {
	public static void main(String[] args) {

		List<Integer> input = new ArrayList<>(
				Arrays.asList(-5, -5, -3, 0, 0, 1, 1, 5, 5, 5, 5, 5, 5, 5, 8, 10, 10, 15, 15));
		int k = 5;

		System.out.println(findFrequencyOfElement(input, k));

	}

	private static int findFrequencyOfElement(List<Integer> A, int k) {
		int l = 0;
		int h = A.size() - 1;
		int start = l;
		int end = h;
		while (l <= h) {
			int mid = (l + h) / 2;
			if (A.get(mid) > k) {
				h = mid - 1;
			} else if (A.get(mid) < k) {
				l = mid + 1;
			} else {
				start = mid; // trying to get the start index
				h = mid - 1;
			}
		}
		l = start; // small optimization
		h = A.size() - 1;
		while (l <= h) {
			int mid = (l + h) / 2;
			if (A.get(mid) > k) {
				h = mid - 1;
			} else if (A.get(mid) < k) {
				l = mid + 1;
			} else {
				end = mid; // trying to get the end index
				l = mid + 1;
			}
		}

		return end - start + 1;
	}
}
