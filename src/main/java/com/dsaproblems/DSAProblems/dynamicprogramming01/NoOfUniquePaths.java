package com.dsaproblems.DSAProblems.dynamicprogramming01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoOfUniquePaths {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> A = new ArrayList<>();
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		A.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		System.out.println(A);
		System.out.println(noOfUniquePaths(A, A.size() - 1, A.get(0).size() - 1));
		System.out.println(noOfUniquePaths1(A, A.size() - 1, A.get(0).size() - 1));
		System.out.println(noOfUniquePaths3(A, A.size() - 1, A.get(0).size() - 1));
		System.out.println(A);

		// [[0,0,0,0,0,1,0,0,0,0],[0,0,0,1,0,0,0,0,0,0],[0,0,0,0,0,0,1,0,1,0],[0,0,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0],[0,1,0,0,0,0,0,0,0,0]]
		ArrayList<ArrayList<Integer>> B = new ArrayList<>();
		B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 1, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
		B.add(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0, 0, 0, 0, 0)));
		System.out.println(noOfUniquePathsWithBlocks2(B, B.size() - 1, B.get(0).size() - 1));
		System.out.println(noOfUniquePaths2(B, B.size() - 1, B.get(0).size() - 1));
		System.out.println(noOfUniquePathsWithBlocks(B, B.size() - 1, B.get(0).size() - 1));
		System.out.println(noOfUniquePathsWithBlocks1(B, B.size() - 1, B.get(0).size() - 1));
		System.out.println(noOfUniquePaths(B, B.size() - 1, B.get(0).size() - 1));
	}

	private static int noOfUniquePathsWithBlocks(ArrayList<ArrayList<Integer>> A, int desti, int destj) {
		if (desti < 0 || destj < 0) {
			return 0;
		}
		if (desti == 0 && destj == 0 && A.get(desti).get(destj) == 0) {
			return 1;
		}
		if (A.get(desti).get(destj) == 1) {
			return 0;
		}
		return noOfUniquePathsWithBlocks(A, desti - 1, destj) + noOfUniquePathsWithBlocks(A, desti, destj - 1);
	}

	private static int noOfUniquePathsWithBlocks1(ArrayList<ArrayList<Integer>> A, int desti, int destj) { // WRONG CODE
		if (desti < 0 || destj < 0) {
			return 0;
		}
		if (A.get(desti).get(destj) == 1) {
			return 0;
		}
		if (A.get(desti).get(destj) == 0) {
			if (desti == 0 && destj == 0) {
				A.get(desti).set(destj, 1);
			} else {
				int item = noOfUniquePathsWithBlocks1(A, desti - 1, destj)
						+ noOfUniquePathsWithBlocks1(A, desti, destj - 1);
				A.get(desti).set(destj, item);
			}
		}
		return A.get(desti).get(destj);
	}

	private static Integer noOfUniquePathsWithBlocks2(ArrayList<ArrayList<Integer>> A, int desti, int destj) { // WORKING
																												// CODE
		int mod = 1000000007;
		List<Integer> dp = new ArrayList<>();
		int dplen = A.get(0).size();
		for (int i = 0; i < dplen; i++) {
			if (A.get(0).get(i) == 0)
				dp.add(1);
			else
				dp.add(0);
			if (i > 0 && dp.get(i - 1) == 0) {
				dp.set(i, 0);
			}
		}
		for (int i = 1; i < A.size(); i++) {
			for (int j = 0; j < dplen; j++) {
				if (A.get(i).get(j) == 1) {
					dp.set(j, 0);
				} else {
					if (j > 0) {
						int nextVal = (dp.get(j - 1) % mod + dp.get(j) % mod) % mod;
						dp.set(j, nextVal);
					}
				}
			}
		}
		return dp.get(destj) % mod;
	}

	private static Integer noOfUniquePaths3(ArrayList<ArrayList<Integer>> A, int desti, int destj) {
		List<Integer> dp = new ArrayList<>();
		int dplen = A.get(0).size();
		for (int i = 0; i < dplen; i++) {
			dp.add(1);
		}
		for (int i = 1; i < A.size(); i++) {
			for (int j = 1; j < dplen; j++) {
				dp.set(j, dp.get(j - 1) + dp.get(j));
			}
		}
		return dp.get(destj);
	}

	private static Integer noOfUniquePaths2(ArrayList<ArrayList<Integer>> A, int desti, int destj) {
		List<List<Integer>> dp = new ArrayList<>();
		int rows = A.size();
		int cols = A.get(0).size();
		for (int i = 0; i < rows; i++) {
			dp.add(new ArrayList<>());
			dp.get(i).add(1);
		}
		for (int i = 1; i < cols; i++) {
			dp.get(0).add(1);
		}
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				dp.get(i).add(dp.get(i - 1).get(j) + dp.get(i).get(j - 1));
			}
		}
		return dp.get(desti).get(destj);
	}

	private static Integer noOfUniquePaths1(ArrayList<ArrayList<Integer>> A, int i, int j) {
		if (A.get(i).get(j) == 0) {
			if (i == 0 || j == 0) {
				A.get(i).set(j, 1); // from 0,0 to any cell in the first row is 1 and first column is 1
			} else {
				int item = noOfUniquePaths1(A, i - 1, j) + noOfUniquePaths1(A, i, j - 1);
				A.get(i).set(j, item);
			}
		}
		return A.get(i).get(j);
	}

	// no of paths from (0,0) to (s,e)
	private static int noOfUniquePaths(ArrayList<ArrayList<Integer>> A, int endi, int endj) { // code works considering
																								// there are no blocks
//		if (endi < 0 || endj < 0) { //without adding this condition it will work
//			return 0;
//		}
		if (endi == 0 || endj == 0) {
			return 1;
		}
		return noOfUniquePaths(A, endi - 1, endj) + noOfUniquePaths(A, endi, endj - 1);
	}

}
