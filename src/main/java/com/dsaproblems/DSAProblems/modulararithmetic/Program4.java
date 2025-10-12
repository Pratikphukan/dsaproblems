package com.dsaproblems.DSAProblems.modulararithmetic;

import java.util.ArrayList;
import java.util.List;

public class Program4 {

	public static void main(String[] args) {
		List<Integer> a = List.of(1, 3, 1, 2, 3);
		ArrayList<Integer> A = new ArrayList<>(a);
		printFrequency(A);
	}

	private static void printFrequency(ArrayList<Integer> A) {
		int n = A.size();
		for (int i = 0; i < n; i++) {
			System.out.print(A.get(A.get(i) % n) + " ");
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			int idx = A.get(i) % n;
			int val = A.get(idx) + n;
			System.out.print(idx + "->" + val + "\n");
			A.set(idx, val);
		}
		System.out.println(A);
		for (int i = 0; i < n; i++)
			System.out.println(i + "->" + A.get(i) / n);
	}

}
