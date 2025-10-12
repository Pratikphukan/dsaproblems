package com.dsaproblems.DSAProblems.trie01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellingChecker {

	public class AlphabetTrieNode {

		private Map<Character, AlphabetTrieNode> nodes;

		private Boolean isEnd;

		public AlphabetTrieNode() {
			nodes = new HashMap<>();
			isEnd = false;
		}
	}

	public class AlphabetTrie {
		private AlphabetTrieNode root;

		public AlphabetTrie() {
			root = new AlphabetTrieNode();
		}

		public void insertIntoTrie(String item) {
			AlphabetTrieNode temp = root;
			for (Character alphabet : item.toCharArray()) {
				if (!temp.nodes.containsKey(alphabet))
					temp.nodes.put(alphabet, new AlphabetTrieNode());
				temp = temp.nodes.get(alphabet);
			}
			temp.isEnd = true;
		}

		public Integer checkInDictionary(String item) {
			AlphabetTrieNode temp = root;
			for (Character alphabet : item.toCharArray()) {
				if (!temp.nodes.containsKey(alphabet))
					return 0;
				temp = temp.nodes.get(alphabet);
			}
			if (temp.isEnd)
				return 1;
			return 0;
		}
	}

	public static void main(String[] args) {
		List<String> A = new ArrayList<>(Arrays.asList("ab", "abc", "abcd", "abcde", "abcdef", "abcdefg"));
		// ("hat", "harp", "cat", "cater"),("cat", "ball")
		// ("ab","abc","abcd","abcde","abcdef","abcdefg"),("a","b","ab","abcd")
		List<String> B = new ArrayList<>(Arrays.asList("a", "b", "ab", "abcd"));
		System.out.println(checkValidWordsInB(A, B));
	}

	private static List<Integer> checkValidWordsInB(List<String> A, List<String> B) {
		List<Integer> wordCheck = new ArrayList<>();
		SpellingChecker spellingChecker = new SpellingChecker();
		AlphabetTrie alphabetTrie = spellingChecker.new AlphabetTrie();
		for (String item : A) {
			alphabetTrie.insertIntoTrie(item);
		}
		for (String item : B) {
			wordCheck.add(alphabetTrie.checkInDictionary(item));
		}
		return wordCheck;
	}

}
