package com.dsaproblems.DSAProblems.trie01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class Solution {

	@Getter
	public class TrieNode {

		private TrieNode left;

		private TrieNode right;

	}

	public class Trie {

		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(int num, int maxBits) {
			TrieNode temp = root;
			Integer bit = null;
			for (int i = maxBits - 1; i >= 0; i--) {
				bit = (num >> i) & 1;
				if (bit == 0) {
					if (temp.left == null) {
						temp.left = new TrieNode();
					}
					temp = temp.left;
				} else {
					if (temp.right == null) {
						temp.right = new TrieNode();
					}
					temp = temp.right;
				}
			}
		}

		public int getMaxXORPair(int num, int maxBits) {
			TrieNode temp = root;
			Integer bit = null;
			int maxXOR = 0;
			for (int i = maxBits - 1; i >= 0; i--) {
				bit = (num >> i) & 1;
				if (bit == 0) {
					if (temp.right != null) {
						temp = temp.right;
						maxXOR += (1 << i);
					} else {
						temp = temp.left;
					}
				} else {
					if (temp.left != null) {
						temp = temp.left;
						maxXOR += (1 << i);
					} else {
						temp = temp.right;
					}
				}
			}
			return maxXOR;
		}
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(25, 10, 2, 8, 5, 3));
		System.out.println(findMaximumXORv1(input));
		System.out.println(findMaximumXORv2(input));
	}

	private static int findMaximumXORv2(List<Integer> input) {
		Solution solution = new Solution();
		Trie trie = solution.new Trie();
		int maxXOR = 0;
		int maxBits = 0;
		for (Integer num : input) {
			maxBits = Math.max(maxBits, (int) (Math.log(num) / Math.log(2)) + 1);
		}
		for (Integer num : input) {
			trie.insert(num, maxBits);
		}
		for (Integer num : input) {
			maxXOR = Math.max(maxXOR, trie.getMaxXORPair(num, maxBits));
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
