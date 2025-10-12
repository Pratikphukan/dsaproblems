package com.dsaproblems.DSAProblems.array01;

public class KthGrammar {

	public static void main(String[] args) {
		int row = 4;
		int idx = 5;
		System.out.println(findKthGrammarv1(row, idx));
		System.out.println(findKthGrammarv2(row, idx) ? 1 : 0);

	}

	private static boolean findKthGrammarv2(int row, int idx) {
		if (row == 1 && idx == 1) {
			return false;
		}
		int mid = (int) (Math.pow(2, row - 1)) / 2;
		if (idx <= mid) {
			return findKthGrammarv2(row - 1, idx);
		}
		return !findKthGrammarv2(row - 1, idx - mid);
	}

	private static int findKthGrammarv1(int row, int idx) {
		if (row == 1 && idx == 1) {
			return 0;
		}
		if (idx % 2 == 0) {
			return findKthGrammarv1(row - 1, idx / 2) == 0 ? 1 : 0;
		}
		return findKthGrammarv1(row - 1, (idx + 1) / 2) == 0 ? 0 : 1;
	}

}
