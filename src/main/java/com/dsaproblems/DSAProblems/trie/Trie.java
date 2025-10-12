package com.dsaproblems.DSAProblems.trie;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(int num, int maxBits) {
		TrieNode temp = root;
		for (int i = maxBits - 1; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (bit == 0) {
				if (temp.binaryZero == null) {
					temp.binaryZero = new TrieNode();
				}
				temp = temp.binaryZero;
			} else {
				if (temp.binaryOne == null) {
					temp.binaryOne = new TrieNode();
				}
				temp = temp.binaryOne;
			}
		}
	}

	public int getOtherXORPair(Integer num, int maxBits) {
		TrieNode temp = root;
		int anotherPair = 0;
		for (int i = maxBits - 1; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (bit == 0) {
				if (temp.binaryOne != null) {
					temp = temp.binaryOne;
					anotherPair += (1 << i);
				} else {
					temp = temp.binaryZero;
				}
			} else {
				if (temp.binaryZero != null) {
					temp = temp.binaryZero;
					anotherPair += (1 << i);
				} else {
					temp = temp.binaryOne;
				}
			}
		}
		return anotherPair;
	}

}
