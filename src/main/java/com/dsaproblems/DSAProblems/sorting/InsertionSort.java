package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {

	public static void main(String[] args) {
		List<Integer> a = List.of(3, 1, 5, -2, 4, 7, 2);
		ArrayList<Integer> A = new ArrayList<Integer>(a);
		System.out.println(sortUsingInsertionSortv1(A));
		System.out.println(sortUsingInsertionSortv2(A));
	}

	private static ArrayList<Integer> sortUsingInsertionSortv2(ArrayList<Integer> A) {
		Integer temp = null;
		for (int i = 1; i < A.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (A.get(j) > A.get(i)) {
					temp = A.get(j);
					A.set(j, A.get(i));
					A.set(j + 1, temp);
				}
			}
		}
		return A;
	}

	private static ArrayList<Integer> sortUsingInsertionSortv1(ArrayList<Integer> A) {
		for (int i = 1; i < A.size(); i++) {
			int j = i - 1;
			// very important regarding the position of j>=0
			// while(A.get(j)>A.get(j+1) && j>=0) {
			while (j >= 0 && A.get(j) > A.get(j + 1)) {
				int temp = A.get(j);
				A.set(j, A.get(j + 1));
				A.set(j + 1, temp);
				j--;
			}
		}
		return A;
	}

}
