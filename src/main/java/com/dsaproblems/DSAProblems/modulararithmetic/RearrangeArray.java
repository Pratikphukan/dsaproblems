package com.dsaproblems.DSAProblems.modulararithmetic;

import java.util.ArrayList;
import java.util.List;

public class RearrangeArray {

	public static void main(String[] args) {
		// All positive numbers
		List<Integer> a = List.of(3, 2, 4, 1, 0);// (3,-2,1,4,2,6,2,-4,2,8)
		ArrayList<Integer> A = new ArrayList<>(a);
		rearrange(A);
		System.out.println(A);
	}

	private static void rearrange(ArrayList<Integer> A) {
		for (int i = 0; i < A.size(); i++) {
			A.set(i, A.get(i) * A.size());
		}
		for (int i = 0; i < A.size(); i++) {
			int index = A.get(i) / A.size();
			int val = A.get(index) / A.size();
			A.set(i, A.get(i) + val);
		}
		for (int i = 0; i < A.size(); i++) {
			A.set(i, A.get(i) % A.size());
		}
	}

}
