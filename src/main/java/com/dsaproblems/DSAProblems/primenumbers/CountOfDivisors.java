package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfDivisors {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 10));
		System.out.println(getCountOfDivisorsv1(input));
		System.out.println(getCountOfDivisorsv2(input));
		System.out.println(getCountOfFactors(1));
	}

	// working
	private static List<Integer> getCountOfDivisorsv1(List<Integer> input) {
		List<Integer> count = new ArrayList<>();
		for (int num : input) {
			count.add(getCountOfFactors(num));
		}
		return count;
	}

	private static Integer getCountOfFactors(int num) {
		int count = 0;
		for (int i = 1; i * i <= num; i++) {
			if (num % i == 0) {
				count += 2;
			}
			if (i * i == num) {
				count -= 1;
			}
		}
		return count;
	}

	private static ArrayList<Integer> getCountOfDivisorsv2(List<Integer> A) {
		int max = A.get(0);
		for (int i = 1; i < A.size(); i++) {
			max = Math.max(max, A.get(i));
		}
		ArrayList<Integer> spf = new ArrayList<>();
		for (int i = 0; i <= max; i++) {
			spf.add(i);
		}
		for (int i = 2; i * i <= max; i++) {
			if (spf.get(i) == i) { // this means i is prime
				for (int j = i * i; j <= max; j = j + i) {
					if (spf.get(j) == j) {
						spf.set(j, i);
					}
				}
			}

		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 0; i < A.size(); i++) {
			int total = 1;
			int n = A.get(i);
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

}
