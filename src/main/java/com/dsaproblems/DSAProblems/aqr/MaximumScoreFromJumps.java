package com.dsaproblems.DSAProblems.aqr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumScoreFromJumps {

	public static void main(String[] args) {
		// 100, -30, -50, -15, -20, -30|3
		// 3, 4, -2, 1, 2|2
		List<Integer> input = new ArrayList<>(Arrays.asList(3, 4, -2, 1, 2));
		int maxJump = 2;
		// System.out.println(getMaxScore(input, maxJump));
		int[] arr = new int[] { 3, 4, -2, 1, 2 };
		System.out.println(getMaxScorev1(arr, maxJump));
		System.out.println(getMaxScorev2(arr, arr.length, maxJump));
	}

	private static int getMaxScorev2(int[] A, int N, int K) {
//		int[] dp = new int[N];
//		dp[N - 1] = A[N - 1];
//
//		// Traverse dp array from the second last index to the first
//		for (int i = N - 2; i >= 0; i--) {
//			// initial score
//			int score = Integer.MIN_VALUE;
//			for (int j = 1; j <= K && i + j < N; j++) {
//				// Update the score with the maximum value
//				// found among the possible steps
//				score = Math.max(score, dp[i + j]);
//			}
//			// Calculate the maximum score possible
//			dp[i] = score + A[i];
//		}
//
//		// return answer
//		return dp[0];

		int[] dp = new int[N];
		dp[N - 1] = A[N - 1];
		for (int i = N - 2; i >= 0; i++) {
			int score = Integer.MIN_VALUE;
			for (int j = 1; j <= K && i + j < N; j++) {
				score = Math.max(score, dp[i + j]);
			}
			dp[i] = score + A[i];
		}
		return dp[0];
	}

	private static int getMaxScorev1(int[] arr, int maxJump) {
		int len = arr.length;
		int[] dp = new int[len];
		for (int i = 0; i < len; i++)
			dp[i] = -1;
		return maxPossibleScorev1(0, arr, len, maxJump, dp);
	}

	private static int maxPossibleScorev1(int i, int[] A, int N, int K, int[] dp) {
		if (i >= N - 1)
			return A[N - 1];

		// If the value for the current
		// index is pre-calculated
		if (dp[i] != -1)
			return dp[i];
		int score = Integer.MIN_VALUE;

		// Calculate maximum score
		// for all the steps in the
		// range from i + 1 to i + k
		for (int j = 1; j <= K; j++) {

			// Score for index (i + j)
			score = Math.max(score, maxPossibleScorev1(i + j, A, N, K, dp));
		}

		// Update dp[i] and return
		// the maximum value
		return dp[i] = score + A[i];
	}

	private static int getMaxScore(List<Integer> input, int maxJump) {
		int len = input.size();
		int[] dp = new int[len];
		for (int i = 0; i < len; i++)
			dp[i] = -1;
		return maxPossibleScore(0, input, maxJump, dp);
	}

	private static int maxPossibleScore(int start, List<Integer> input, int maxJump, int[] dp) {
		if (start >= input.size() - 1) {
			return dp[input.size() - 1];
		}
		int score;
		if (dp[start] == -1) {
			score = Integer.MIN_VALUE;
			for (int i = 1; i <= maxJump; i++) {
				score = Math.max(score, maxPossibleScore(start + i, input, maxJump, dp));
			}
			dp[start] = score + input.get(start);
		}
		return dp[start];
	}

}
