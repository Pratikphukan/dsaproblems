package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;

public class FindSmallestPrimeFactor {

	public static void main(String[] args) {
		System.out.println(getSmallestPrimeFactors(30));
		System.out.println(getHighestPrimeFactors(30));
		System.out.println(getCountOfDivisorsOfNumber(30));
	}

	private static ArrayList<Integer> getCountOfDivisorsOfNumber(int num) {
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= num; i++) {
			spf.add(i);
		}
		for (int i = 2; i * i <= num; i++) {
			if (spf.get(i) == i) {
				for (int j = i * i; j <= num; j = j + i) {
					if (spf.get(j) == j) {
						spf.set(j, i);
					}
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			int total = 1;
			int n = i;
			while (n > 1) {
				int p = spf.get(n);
				int c = 0;
				while (n % p == 0) {
					c += 1;
					n = n / p;
				}
				total *= (c + 1);
			}
			ans.add(total);
		}
		return ans;
	}

	private static ArrayList<Integer> getHighestPrimeFactors(int num) {
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= num; i++) {
			spf.add(i);
		}
		for (int i = 2; i * i <= num; i++) {
			if (spf.get(i) == i) {
				for (int j = i * i; j <= num; j = j + i) {
					spf.set(j, i);
				}
			}
		}
		return spf;
	}

	private static ArrayList<Integer> getSmallestPrimeFactors(int num) {
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= num; i++) {
			spf.add(i);
		}
		for (int i = 2; i * i <= num; i++) {
			if (spf.get(i) == i) {
				for (int j = i * i; j <= num; j = j + i) {
					if (spf.get(j) == j) {
						spf.set(j, i);
					}
				}
			}
		}
		return spf;
	}

}
