package com.dsaproblems.DSAProblems.primenumbers;

public class CheckPrime {

	public static void main(String[] args) {
		System.out.println(isPrime(2));
		System.out.println(getNoOfPrimesv1(30));// 2,3,5,7,11,13,17,19,23,29
		System.out.println(getNoOfPrimesv2(30));
		long begin = System.nanoTime();
		System.out.println(getNoOfPrimesv3(10000));
		long execution1 = System.nanoTime();
		System.out.println(getNoOfPrimesv4(10000));
		long execution2 = System.nanoTime();
		System.out.println(getNoOfPrimesv5(10000));
		long execution3 = System.nanoTime();
		System.out.println(execution1 - begin);
		System.out.println(execution2 - execution1);
		System.out.println(execution3 - execution2);
	}

	// better solution
	private static int getNoOfPrimesv5(int num) {
		Boolean[] checkPrime = new Boolean[num + 1];
		checkPrime[0] = checkPrime[1] = false;
		for (int i = 2; i * i <= num; i++) {
			if (checkPrime[i] == null)
				checkPrime[i] = true;
			if (checkPrime[i]) {
				for (int j = i * i; j <= num; j = j + i) {
					if (checkPrime[j] == null)
						checkPrime[j] = false;
					else
						continue;
				}
			}
		}
		int count = 0;
		for (Boolean check : checkPrime)
			count += (check == null || check) ? 1 : 0;
		return count;
	}

	private static int getNoOfPrimesv4(int num) {
		Boolean[] checkPrime = new Boolean[num + 1];
		checkPrime[0] = checkPrime[1] = false;
		for (int i = 2; i <= num; i++) {
			if (checkPrime[i] == null)
				checkPrime[i] = true;
			if (checkPrime[i]) {
				for (int j = i * i; j <= num; j = j + i) {
					if (checkPrime[j] == null)
						checkPrime[j] = false;
					else
						continue;
				}
			}
		}
		int count = 0;
		for (boolean check : checkPrime)
			count += (check) ? 1 : 0;
		return count;
	}

	// working
	private static int getNoOfPrimesv3(int num) {
		Boolean[] checkPrime = new Boolean[num + 1];
		checkPrime[0] = checkPrime[1] = false;
		for (int i = 2; i <= num; i++) {
			if (checkPrime[i] == null)
				checkPrime[i] = true;
			if (checkPrime[i]) {
				for (int j = 2 * i; j <= num; j = j + i) {
					if (checkPrime[j] == null)
						checkPrime[j] = false;
					else
						continue;
				}
			}
		}
		int count = 0;
		for (boolean check : checkPrime)
			count += (check) ? 1 : 0;
		return count;
	}

	// TC is O(N*sqrt(N))
	private static int getNoOfPrimesv2(int num) {
		if (num == 1) {
			return 0;
		}
		int count = 0;
		for (int i = 2; i <= num; i++) {
			boolean isPrime = true;
			for (int fac = 2; fac * fac <= i; fac++) {
				if (i % fac == 0) {
					isPrime = false;
				}
			}
			if (isPrime) {
				count += 1;
			}
		}
		return count;
	}

	private static int getNoOfPrimesv1(int num) {
		if (num == 1) {
			return 0;
		}
		int count = 0;
		for (int i = 2; i <= num; i++) {
			if (isPrime(i)) {
				count += 1;
			}
		}
		return count;
	}

	public static boolean isPrime(int num) {
		if (num == 1) {
			return false;
		}
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
