package com.dsaproblems.DSAProblems.trie;

public class AlphabetTrie {

	private AlphabetTrieNode root;

	public AlphabetTrie() {
		root = new AlphabetTrieNode();
	}

	public void insertIntoTrie(String item) {
		AlphabetTrieNode temp = root;
		for (Character alphabet : item.toCharArray()) {
			if (!temp.getNodes().containsKey(alphabet))
				temp.getNodes().put(alphabet, new AlphabetTrieNode());
			temp = temp.getNodes().get(alphabet);
		}
		temp.setIsEnd(true);
	}

	public int checkInDictionary(String item) {
		AlphabetTrieNode temp = root;
		for (Character alphabet : item.toCharArray()) {
			if (!temp.getNodes().containsKey(alphabet))
				return 0;
			temp = temp.getNodes().get(alphabet);
		}
		if (temp.getIsEnd())
			return 1;
		return 0;
	}

}
