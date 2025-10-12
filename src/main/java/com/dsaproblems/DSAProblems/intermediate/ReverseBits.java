package com.dsaproblems.DSAProblems.intermediate;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
 * Reverse the bits of an 32 bit unsigned integer A.
 */
public class ReverseBits {

	public static void main(String[] args) {
		long input = 322122547;
		System.out.println(reverse32bitIntegerv1(input));
		System.out.println(reverse32bitIntegerv2(input));
		System.out.println(reverse32bitIntegerv3(input));
	}

	// working code
	private static long reverse32bitIntegerv3(long input) {
		return LongStream.range(0, 32).reduce(0, (acc, num) -> ((input >> num) & 1) == 1 ? acc << 1 | 1 : acc << 1);
		// return IntStream.range(0, 32).reduce(0, (acc, num) -> ((input >> num) & 1) ==
		// 1 ? acc << 1 | 1 : acc << 1);
	}

	// working code
	private static long reverse32bitIntegerv2(long input) {
		long reversedBitsNum = 0;
		for (int i = 0; i < 32; i++) {
			reversedBitsNum = reversedBitsNum << 1;
			if (((input >> i) & 1) == 1) {
				reversedBitsNum = reversedBitsNum | 1;
			}
		}
		return reversedBitsNum;
	}

	// working code
	private static long reverse32bitIntegerv1(long input) {
		long reversedBitsNum = 0;
		for (int i = 0; i < 32; i++) {
			reversedBitsNum = reversedBitsNum << 1;
			if (((1 << i) & input) > 0) {
				reversedBitsNum = reversedBitsNum | 1;
			}
		}
		return reversedBitsNum;
	}

}
