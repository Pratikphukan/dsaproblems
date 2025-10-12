package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class ReverseBitsOfUnsignedInt {

	public static void main(String[] args) {
		long a = 3;
		System.out.println(reverseBitsOfUnsignedIntv1(a));
		System.out.println(reverseBitsOfUnsignedIntv2(a));
		System.out.println(reverseBitsOfUnsignedIntv3(a));
	}

	private static long reverseBitsOfUnsignedIntv3(long num) {
		long reversedBitsNum = 0;
		for (int i = 0; i < 32; i++) {
			reversedBitsNum = reversedBitsNum << 1;
			if (((1 << i) & num) > 0) {
				reversedBitsNum = reversedBitsNum | 1;
			}
		}
		return reversedBitsNum;
	}

	private static long reverseBitsOfUnsignedIntv2(long num) {
		List<Integer> bits = new ArrayList<>();
		long reversedBitsNum = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & num) > 0) {
				bits.add(1);
			} else {
				bits.add(0);
			}
		}
		for (int i = 0; i < 32; i++) {
			reversedBitsNum += Math.pow(2, 31 - i) * bits.get(i);
		}
		return reversedBitsNum;
	}

	private static long reverseBitsOfUnsignedIntv1(long num) {
		List<Integer> bits = new ArrayList<>();
		long reversedNum = 0;
		for (int i = 31; i >= 0; i--) {
			if (((1 << i) & num) > 0) {
				bits.add(0, 1);
			} else {
				bits.add(0, 0);
			}
		}
		for (int i = 0; i < 32; i++) {
			reversedNum += Math.pow(2, 31 - i) * bits.get(i);
		}
		return reversedNum;
	}

}
