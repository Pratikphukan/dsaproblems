package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;

public class PrimeSum {

	public static void main(String[] args) {
		// 74, 1048574
		System.out.println(primeSumv1(74));
		System.out.println(primeSumv2(1048574));
	}

	// working solution
	private static ArrayList<Integer> primeSumv2(int num) {
		// boolean[] checkPrimes = getPrimesTillNumv1(num);
		// boolean[] checkPrimes = getPrimesTillNumv2(num);
		// boolean[] checkPrimes = getPrimesTillNumv3(num);
		boolean[] checkPrimes = getPrimesTillNumv4(num);
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 2; i <= num; i++) {
			if (checkPrimes[i] && checkPrimes[num - i]) {
				ans.add(i);
				ans.add(num - i);
				return ans;
			}
		}
		return null;
	}

	// working
	private static boolean[] getPrimesTillNumv4(int num) {
		boolean[] checkPrimes = new boolean[num + 1];
		for (int i = 0; i <= num; i++)
			checkPrimes[i] = true; // considering all of them to be primes
		checkPrimes[0] = checkPrimes[1] = false;
		for (int i = 2; i * i <= num; i++) {
			if (checkPrimes[i]) {
				for (int mul = i * i; mul <= num; mul += i) {
					if (checkPrimes[mul])
						checkPrimes[mul] = false;
				}
			}
		}
		return checkPrimes;
	}

	// working but fails for a number like 1048574
	private static boolean[] getPrimesTillNumv3(int num) {
		boolean[] checkPrimes = new boolean[num + 1];
		for (int i = 0; i <= num; i++)
			checkPrimes[i] = true; // considering all of them to be primes
		checkPrimes[0] = checkPrimes[1] = false;
		for (int i = 2; i <= num; i++) {
			if (checkPrimes[i]) {
				for (int mul = i * i; mul <= num; mul += i) {
					if (checkPrimes[mul])
						checkPrimes[mul] = false;
				}
			}
		}
		return checkPrimes;
	}

	// working
	private static boolean[] getPrimesTillNumv2(int num) {
		boolean[] checkPrimes = new boolean[num + 1];
		for (int i = 0; i <= num; i++)
			checkPrimes[i] = true; // considering all of them to be primes
		checkPrimes[0] = checkPrimes[1] = false;
		for (int i = 2; i <= num; i++) {
			if (checkPrimes[i]) {
				for (int mul = 2 * i; mul <= num; mul += i) {
					if (checkPrimes[mul])
						checkPrimes[mul] = false;
				}
			}
		}
		return checkPrimes;
	}

	// working
	private static boolean[] getPrimesTillNumv1(int num) {
		boolean[] checkPrimes = new boolean[num + 1];
		for (int i = 2; i <= num; i++) {
			checkPrimes[i] = true;
			for (int fac = 2; fac * fac <= i; fac++) {
				if (i % fac == 0)
					checkPrimes[i] = false;
			}
		}
		return checkPrimes;
	}

	private static ArrayList<Integer> primeSumv1(int n) {
		ArrayList<Integer> ans = new ArrayList<>();
		boolean isPrime[] = new boolean[n + 1];
		sieveOfEratosthenes(n, isPrime);
		for (int i = 0; i < n; i++) {
			if (isPrime[i] && isPrime[n - i]) {
				ans.add(i);
				ans.add(n - i);
				return ans;
			}
		}
		return ans;
	}

	private static void sieveOfEratosthenes(int n, boolean[] isPrime) {
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= n; i++)
			isPrime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			if (isPrime[p] == true) {
				for (int i = p * p; i <= n; i += p)
					isPrime[i] = false;
			}
		}
	}

	public ArrayList<Integer> primesum(int A) {
		ArrayList<Integer> ans = new ArrayList<>();
		boolean isPrime[] = new boolean[A + 1];
		sieveOfEratosthenes(A, isPrime);
		for (int i = 0; i < A / 2 + 1; i++) {
			if (isPrime[i] && isPrime[A - i]) {
				ans.add(i);
				ans.add(A - i);
				return ans;
			}
		}
		return ans;
	}

	public void sieveOfEratosthenes1(int n, boolean[] isPrime) {
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= n; i++)
			isPrime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			if (isPrime[p] == true) {
				for (int i = p * p; i <= n; i += p)
					isPrime[i] = false;
			}
		}
	}
}
