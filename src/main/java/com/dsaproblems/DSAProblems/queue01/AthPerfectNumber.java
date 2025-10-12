package com.dsaproblems.DSAProblems.queue01;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AthPerfectNumber {

	public static void main(String[] args) {
		System.out.println(findAthPerfectNumberUsing12(8));
		System.out.println(findAthPerfectNumberUsing12v3(8));
	}

	private static String findAthPerfectNumberUsing12v3(int A) {
		Deque<String> queue = new LinkedList<>();
		String ans = null;
		queue.addLast("1");
		queue.addLast("2");
		Integer count = queue.size();
		BigDecimal ten = new BigDecimal(10);
		for (int i = 0; i < A; i++) {
			String multiplier = queue.removeFirst();
			Function<String, String> reverseString = (input) -> {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < input.length(); j++) {
					sb.insert(0, input.charAt(j));
				}
				return sb.toString();
			};
			String reversedMultiplier = reverseString.apply(multiplier);
			ans = multiplier + reversedMultiplier;
			//((new BigDecimal(multiplier).multiply(ten)).add(BigDecimal.ONE)).toString();
			if (count <= A) {
				queue.addLast(((new BigDecimal(multiplier).multiply(ten)).add(BigDecimal.ONE)).toString());
				queue.addLast(((new BigDecimal(multiplier).multiply(ten)).add(new BigDecimal(2))).toString());
				count += 2;
			}
		}
		return ans;
	}

	private static String findAthPerfectNumberUsing12v2(int A) {
		Deque<String> queue = new LinkedList<>();
		String ans = null;
		queue.addLast("1");
		queue.addLast("2");
		Integer count = queue.size();
		BigDecimal ten = new BigDecimal(10);
		for (int i = 0; i < A; i++) {
			String multiplier = queue.removeFirst();
			String reversedMultiplier = reverseString(multiplier);
			ans = multiplier + reversedMultiplier;
			((new BigDecimal(multiplier).multiply(ten)).add(BigDecimal.ONE)).toString();
			if (count <= A) {
				queue.addLast(((new BigDecimal(multiplier).multiply(ten)).add(BigDecimal.ONE)).toString());
				queue.addLast(((new BigDecimal(multiplier).multiply(ten)).add(new BigDecimal(2))).toString());
				count += 2;
			}
		}
		return ans;
	}

	private static String reverseString(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			sb.insert(0, input.charAt(i));
		}
		return sb.toString();
	}

	private static String findAthPerfectNumberUsing12(int A) {
		Deque<Integer> queue = new LinkedList<>();
		String ans = null;
		queue.addLast(1);
		queue.addLast(2);
		Integer count = queue.size();
		for (int i = 0; i < A; i++) {
			int multiplier = queue.removeFirst();
			int reversedMultiplier = reverseNumber(multiplier);
			ans = Integer.toString(multiplier) + Integer.toString(reversedMultiplier);
			if (count <= A) {
				queue.addLast(multiplier * 10 + 1);
				queue.addLast(multiplier * 10 + 2);
				count += 2;
			}
		}
		return ans;
	}

	private static int reverseNumber(int number) {
		int reversedNumber = 0;
		while (number != 0) {
			int remainder = number % 10;
			reversedNumber = reversedNumber * 10 + remainder;
			number /= 10;
		}
		return reversedNumber;
	}

}
