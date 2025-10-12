package com.dsaproblems.DSAProblems.searching01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FindTheUniqueElement {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 3, 1, 1, 8, 8, 10, 10, 19, 6, 6, 2, 2, 4, 4));
		System.out.println(findUniqueElement(input));
	}

	private static Integer findUniqueElement(List<Integer> A) {
		if (A.size() == 1) {
			return A.get(0);
		}
		int len = A.size();
		if (!Objects.equals(A.get(0), A.get(1))) {
			return A.get(0);
		}
		if (!Objects.equals(A.get(len - 2), A.get(len - 1))) {
			return A.get(len - 1);
		}
		int l = 0;
		int h = len - 1;
		while (l <= h) {
			int mid = (l + h) / 2;
			if (!(Objects.equals(A.get(mid - 1), A.get(mid)) || Objects.equals(A.get(mid), A.get(mid + 1)))) {
				return A.get(mid);
			}
			if (Objects.equals(A.get(mid), A.get(mid - 1))) {
				mid = mid - 1;
			}
			if (mid % 2 == 0) {
				l = mid + 2;
			} else {
				h = mid - 1;
			}
		}
		return Integer.MIN_VALUE;
	}

}
