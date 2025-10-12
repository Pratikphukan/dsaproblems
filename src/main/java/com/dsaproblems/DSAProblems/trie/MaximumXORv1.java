package com.dsaproblems.DSAProblems.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumXORv1 {

	static class TrieNode {

		private TrieNode left;

		private TrieNode right;

		private Integer val;

		public TrieNode(Integer val) {
			super();
			this.val = val;
		}
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(25, 10, 2, 8, 5, 3));
		// numbers in the array will be positive
		System.out.println(findMaximumXORv1(input));
	}

	private static int findMaximumXORv1(List<Integer> input) {
		int maxXOR = 0;
		int highestIdxOfMSB = 0;
		// get the highest most significant bit
		for (Integer num : input) {
			highestIdxOfMSB = Math.max(highestIdxOfMSB, (int) (Math.log(num) / Math.log(2)) + 1);
		}
		TrieNode root = new TrieNode(0);
		for (Integer num : input) {
			System.out.println(Integer.toBinaryString(num));
			insertIntoTrie(num, highestIdxOfMSB, root);
		}
		for (Integer num : input) {
			maxXOR = Math.max(maxXOR, getOtherXORPair(num, highestIdxOfMSB, root));
		}
		return maxXOR;
	}

	private static int getOtherXORPair(Integer num, int highestIdxOfMSB, TrieNode root) {
		TrieNode temp = root;
		int anotherPair = 0;
		for (int i = highestIdxOfMSB - 1; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (bit == 0) {
				if (temp.right != null) {
					temp = temp.right;
					anotherPair += (1 << i);
				} else {
					temp = temp.left;
				}
			} else {
				if (temp.left != null) {
					temp = temp.left;
					anotherPair += (1 << i);
				} else {
					temp = temp.right;
				}
			}
		}
		return anotherPair;
	}

	private static void insertIntoTrie(Integer num, int highestIdxOfMSB, TrieNode root) {
		TrieNode temp = root;
		for (int i = highestIdxOfMSB - 1; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (bit == 0) {
				if (temp.left == null) {
					temp.left = new TrieNode(0);
				}
				temp = temp.left;
			} else {
				if (temp.right == null) {
					temp.right = new TrieNode(1);
				}
				temp = temp.right;
			}
		}
	}

}
