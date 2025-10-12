package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class SmallestXOR {

	public static void main(String[] args) {
		int a = 4;
		int b = 6;
		// (4,6),(9,3)
		System.out.println(findSmallestXORv1(a, b));
		System.out.println(findSmallestXORv2(a, b));
	}

	private static int findSmallestXORv2(int a, int b) {
		int setBitsCount = b;
		int mask = 0;
		int num = 0;
		for (int i = 30; i >= 0; i--) {
			mask = (1 << i);
			if ((mask & a) > 0 && setBitsCount > 0) {
				num = num | mask;
				setBitsCount--;
			}
		}
		int i = 0;
		while (setBitsCount > 0) {
			mask = (1 << i);
			if ((mask & num) == 0) {
				num = num | mask;
				setBitsCount--;
			}
			i++;
		}
		return num;
	}

	private static int getSetBitsOfNumber(int n) {
		int count = 0;
		if (n < 0) {
			count++;
		}
		for (int i = 0; i < 31; i++) {
			count += ((n >> i) & 1);
		}
		return count;
	}

	// working code
	private static int findSmallestXORv1(int a, int b) {
		List<Integer> bits = new ArrayList<>();
		Integer bitCheck = null;
		Integer setBitsCount = 0;
		int num = 0;
		for (int i = 0; i < 31; i++) {
			bitCheck = ((a << i) & 1073741824);
			if (bitCheck > 0) {
				setBitsCount += 1;
				if (setBitsCount <= b) {
					bits.add(1);
				} else {
					bits.add(0);
				}
			} else {
				bits.add(bitCheck);
			}
		}
		for (int i = bits.size() - 1; i >= 0 && setBitsCount < b; i--) {
			if (bits.get(i) == 0) {
				bits.set(i, 1);
				setBitsCount += 1;
			}
		}
		for (int i = 0; i < 31; i++) {
			num += Math.pow(2, 30 - i) * bits.get(i);
		}
		return num;
	}

}
