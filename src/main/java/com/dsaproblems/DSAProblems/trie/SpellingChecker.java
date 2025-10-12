package com.dsaproblems.DSAProblems.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellingChecker {

	public static void main(String[] args) {
		List<String> A = new ArrayList<>(Arrays.asList("hat", "harp", "cat", "cater"));
		List<String> B = new ArrayList<>(Arrays.asList("cat", "ball"));
		System.out.println(checkValidWordsInB(A, B));
	}

	private static List<Integer> checkValidWordsInB(List<String> A, List<String> B) {
		List<Integer> wordCheck = new ArrayList<>();
		AlphabetTrie alphabetTrie = new AlphabetTrie();
		for (String item : A) {
			alphabetTrie.insertIntoTrie(item);
		}
		for (String item : B) {
			wordCheck.add(alphabetTrie.checkInDictionary(item));
		}
		return wordCheck;
	}
}
