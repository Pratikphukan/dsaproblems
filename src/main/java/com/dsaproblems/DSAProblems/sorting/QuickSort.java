package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

	public static void main(String[] args) {

	}

	private static ArrayList<Integer> solve(ArrayList<Integer> A) {
		quickSort(A, 0, A.size() - 1);
		return A;
	}

	private static void quickSort(ArrayList<Integer> A, int s, int e) {
//		if(s>=e) { 
//			return;
//		}
//		int p = rearrange(A, s, e);
//		quickSort(A, s, p-1);
//		quickSort(A, p+1, e);
		if (s < e) {
			int p = rearrange(A, s, e); // position where element is kept
			quickSort(A, s, p - 1);
			quickSort(A, p + 1, e);
		}
	}

	private static int rearrange(ArrayList<Integer> A, int s, int e) {
		int l = s + 1, r = e;
		while (l <= r) {
			if (A.get(l) <= A.get(s)) {
				l++;
			} else if (A.get(r) > A.get(s)) {
				r--;
			} else {
				int tmp = A.get(l);
				A.set(l, A.get(r));
				A.set(r, tmp);
				l++;
				r--;
			}
		}
		int tmp = A.get(s);
		A.set(s, A.get(r));
		A.set(r, tmp);
		return r;
	}

}
