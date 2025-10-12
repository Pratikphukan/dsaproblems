package com.dsaproblems.DSAProblems.primenumbers;

import java.util.ArrayList;

public class NumberOfOpenDoors {

	public static void main(String[] args) {
		// 2258732, 5, 844612, 100, 36
		System.out.println(findNumberOfOpenDoorsv1(10));
		System.out.println(findNumberOfOpenDoorsv2(7));
		System.out.println(findNumberOfOpenDoorsv3(10));
		System.out.println(findNumberOfOpenDoorsv4(2258732));
		System.out.println(findNumberOfOpenDoorsv5(100));
		System.out.println(findNumberOfOpenDoorsv6(844612));
		System.out.println(findNumberOfOpenDoorsv7(36));
	}

	private static int findNumberOfOpenDoorsv7(int num) {
		int openDoors = 0;
		int i = 1;
		for (i = 1; i * i <= num; i++) {
			if (i * i == num) {
				openDoors = i;
			}
		}
		if (openDoors == 0) {
			openDoors = i - 1;
		}
		return openDoors;
	}

	// all the perfect squares will be open doors
	private static int findNumberOfOpenDoorsv6(int num) {
		int openDoors = 0;
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j * j <= i; j++) {
				if (j * j == i) {
					openDoors += 1;
				}
			}
		}
		return openDoors;
	}

	private static int findNumberOfOpenDoorsv5(int num) {
		int openDoors = 0;
		int count = 0;
		for (int i = 1; i <= num; i++) {
			count = 0;
			for (int j = 1; j * j <= i; j++) {
				if (i % j == 0) {
					count += 2;
				}
				if (j * j == i) {
					count -= 1;
				}
			}
			if (count % 2 == 1) {
				openDoors += 1;
			}
		}
		return openDoors;
	}

	private static int findNumberOfOpenDoorsv4(int num) {
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
		int count = 0;
		for (int element : ans) {
			if (element % 2 == 1)
				count += 1;
		}
		return count;
	}

	private static int findNumberOfOpenDoorsv3(int num) {
		boolean[] doorStatus = new boolean[num + 1]; // false->closed
		for (int i = 1; i <= num; i++) {
			doorStatus[i] = !doorStatus[i];
		}
		for (int i = 2; i <= num; i++) {
			doorStatus[i] = !doorStatus[i];
			for (int j = 2 * i; j <= num; j = j + i) {
				doorStatus[j] = !doorStatus[j];
			}
		}
		int count = 0;
		for (boolean status : doorStatus) {
			if (status)
				count += 1;
		}
		return count;
	}

	private static int findNumberOfOpenDoorsv2(int num) {
		boolean[] doorStatus = new boolean[num + 1]; // false->closed
		for (int i = 1; i <= num; i++) {
			doorStatus[i] = !doorStatus[i];
		}
		for (int i = 2; i <= num; i++) {
			for (int j = i; j <= num; j = j + i) {
				doorStatus[j] = !doorStatus[j];
			}
		}
		int count = 0;
		for (boolean status : doorStatus) {
			if (status)
				count += 1;
		}
		return count;
	}

	// working code, but giving TLE
	private static int findNumberOfOpenDoorsv1(int num) {
		boolean[] doorStatus = new boolean[num + 1]; // false->closed
		for (int i = 0; i < num; i++) {
			for (int j = i + 1; j <= num; j = j + i + 1) {
				doorStatus[j] = !doorStatus[j];
			}
		}
		int count = 0;
		for (boolean status : doorStatus) {
			if (status)
				count += 1;
		}
		return count;
	}

}
