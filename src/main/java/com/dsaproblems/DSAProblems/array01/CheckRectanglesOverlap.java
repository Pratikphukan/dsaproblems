package com.dsaproblems.DSAProblems.array01;

public class CheckRectanglesOverlap {

	public static void main(String[] args) {

	}

	public int solve(int A, int B, int C, int D, int E, int F, int G, int H) {
		if (E >= C || A >= G || B >= H || D <= F) {
			return 0;
		}
		return 1;
	}

	public int solve1(int A, int B, int C, int D, int E, int F, int G, int H) {
		int xs = Math.max(A, E), xe = Math.min(C, G);
		int ys = Math.max(B, F), ye = Math.min(D, H);
		if (xs < xe && ys < ye)
			return 1;
		else
			return 0;
	}

}
