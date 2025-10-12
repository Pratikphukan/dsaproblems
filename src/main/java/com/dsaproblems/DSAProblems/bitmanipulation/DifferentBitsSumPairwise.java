package com.dsaproblems.DSAProblems.bitmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DifferentBitsSumPairwise {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5));
		System.out.println(getDifferentBitsPairSumv1(input));
		System.out.println(getDifferentBitsPairSumv2(input));
		System.out.println(getDifferentBitsPairSumv3(input));
	}

	private static int getDifferentBitsPairSumv3(List<Integer> input) {
		int mod = 1000000007;
		int ans = 0;
		long count = 0;// very common mistake of having int
		for (int i = 0; i < 31; i++) {
			count = 0;
			for (Integer element : input) { // if(((1<<i)&A.get(j))>0)
				count += ((element >> i) & 1);
			}
			ans += 2 * count * (input.size() - count);
			ans %= mod;
		}
		return ans;
	}

	private static int getDifferentBitsPairSumv2(List<Integer> input) {
		int setBitsSum = 0;
		for (int i = 0; i < input.size() - 1; i++) {
			for (int j = i + 1; j < input.size(); j++) {
				setBitsSum += getDifferentBits(input.get(i), input.get(j));
			}
		}
		return setBitsSum * 2;
	}

	private static int getDifferentBits(Integer num1, Integer num2) {
		int count = 0;
		while (num1 > 0 || num2 > 0) { // considering positive numbers only
			if (((num1 & 1) == 1 && (num2 & 1) == 0) || ((num1 & 1) == 0 && (num2 & 1) == 1)) {
				count += 1;
			}
			num1 = num1 >> 1;
			num2 = num2 >> 1;
		}
		return count;
	}

	private static int getDifferentBitsPairSumv1(List<Integer> input) {
		int setBitsSum = 0;
		for (int i = 0; i < input.size() - 1; i++) {
			for (int j = i + 1; j < input.size(); j++) {
				setBitsSum += getSetBitsOfNumber(input.get(i) ^ input.get(j)); // 2,3 pair is not same 3,2 pair
			}
		}
		return setBitsSum * 2;
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

}
