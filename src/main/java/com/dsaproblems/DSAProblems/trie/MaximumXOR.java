package com.dsaproblems.DSAProblems.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumXOR {

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(25, 10, 2, 8, 5, 3));
		// numbers in the array will be positive
		System.out.println(findMaximumXORv1(input));
		System.out.println(findMaximumXORv2(input));
	}

	private static int findMaximumXORv2(List<Integer> input) {
		Trie trie = new Trie();
		int maxXOR = 0;
		int maxBits = 0;
		for (Integer num : input) {
			maxBits = Math.max(maxBits, (int) (Math.log(num) / Math.log(2)) + 1); // get the most significant bit
		}
		for (Integer num : input) {
			System.out.println(Integer.toBinaryString(num));
			trie.insert(num, maxBits);
		}
		System.out.println(trie.toString());
		for (Integer num : input) {
			maxXOR = Math.max(maxXOR, trie.getOtherXORPair(num, maxBits));
		}
		return maxXOR;
	}

	private static int findMaximumXORv1(List<Integer> input) {
		int maximumXOR = 0;
		for (int i = 0; i < input.size() - 1; i++) {
			for (int j = i + 1; j < input.size(); j++) {
				maximumXOR = Math.max(maximumXOR, input.get(i) ^ input.get(j));
			}
		}
		return maximumXOR;
	}

}
