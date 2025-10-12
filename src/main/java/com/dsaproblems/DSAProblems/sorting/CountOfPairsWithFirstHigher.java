package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountOfPairsWithFirstHigher {

	public static void main(String[] args) {
		List<Integer> A = new ArrayList<>(Arrays.asList(7, 8, 2, 4));
		List<Integer> B = new ArrayList<>(Arrays.asList(3, 5, 1, 10));
		System.out.println(findCountv1(A, B));
		System.out.println(findCountv2(A, B));
	}

	private static int findCountv2(List<Integer> A, List<Integer> B) {
		int count = 0;
		Collections.sort(A);
		Collections.sort(B);
		int i = 0;
		int j = 0;
		while (i < A.size() && j < B.size()) {
			if (A.get(i) > B.get(j)) {
				count += A.size() - i;
				j++;
			} else {
				i++;
			}
		}
		while (i < A.size()) {
			i++;
		}
		while (j < B.size()) {
			count += A.size() - i;
			j++;
		}
		return count;
	}

	private static int findCountv1(List<Integer> A, List<Integer> B) {
		int count = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				if (A.get(i) > B.get(j)) {
					count++;
				}
			}
		}
		return count;
	}

}
