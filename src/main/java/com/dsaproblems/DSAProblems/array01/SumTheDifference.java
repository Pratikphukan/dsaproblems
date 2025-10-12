package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SumTheDifference {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(5, 7, 2));
		System.out.println(findPossibilities(input));
		System.out.println(findPossibilitiesv3(input));
	}

	private static int findPossibilitiesv3(List<Integer> input) {
		Collections.sort(input);
		int Mod = 1000 * 1000 * 1000 + 7;
		long maxSums = 0;
		long minSums = 0;
		int val = 0;
		for (int i = 0; i < input.size(); i++) {
			val = input.get(i);
			minSums += (val * pow(2, input.size() - 1 - i, Mod)) % Mod;
			minSums %= Mod;
			maxSums += (val * pow(2, i, Mod)) % Mod;
			maxSums %= Mod;
		}
		return (int) ((maxSums - minSums + Mod) % Mod);
	}

	private static long pow(int x, int y, int mod) {
		long result = 1;
		while (y > 0) {
			if (y % 2 == 1) {
				result = (result * x) % mod;
				y--;
			}
			y >>= 1;
			x = (x * x) % mod;
		}
		return result;
	}

	private static Integer findPossibilities(List<Integer> input) {
		int len = input.size();
		int possibilities = 1 << len;
		Integer max = 0;
		Integer min = Integer.MAX_VALUE;
		Long sum = 0l;
		int mod = 1000000007;
		for (int i = 1; i < possibilities; i++) {
			max = 0;
			min = Integer.MAX_VALUE;
			for (int j = 0; j < len; j++) {
				if (((i >> j) & 1) == 1) {
					max = Math.max(max, input.get(j));
					min = Math.min(min, input.get(j));
				}
			}
			sum += (max - min) % mod;
		}
		return (int) (sum % mod);
	}

	private static Integer findPossibilitiesv1(List<Integer> A) {
		Collections.sort(A);
		int mod = 1000000007;
		long fac = 1;
		long sum1 = 0, sum2 = 0;
		for (int i = 0; i < A.size(); i++) {
			sum1 += (A.get(i) * fac) % mod;
			fac = (fac << 1) % mod;
		}
		fac = (fac >> 1) % mod;
		for (int i = 0; i < A.size(); i++) {
			sum2 += (A.get(i) * fac) % mod;
			fac = (fac >> 1) % mod;
		}
		return (int) (sum1 - sum2) % mod;
	}

	private static Integer findPossibilitiesv2(List<Integer> A) {
		Collections.sort(A);
		int mod = 1000000007;
		long maxSums = 0;
		long minSums = 0;
		int len = A.size();
		for (int i = 0; i < A.size(); i++) {
			maxSums += (A.get(i) % mod * Math.pow(2, i) % mod) % mod;
			maxSums %= mod;
		}
		for (int i = 0; i < A.size(); i++) {
			minSums += (A.get(i) % mod * Math.pow(2, len - i - 1) % mod) % mod;
			minSums %= mod;
		}
		return (int) (maxSums - minSums) % mod;
	}

}
