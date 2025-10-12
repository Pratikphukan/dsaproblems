package com.dsaproblems.DSAProblems.trie;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class AlphabetTrieNode {

	private Map<Character, AlphabetTrieNode> nodes;

	private Boolean isEnd;

	public AlphabetTrieNode() {
		nodes = new HashMap<>();
		isEnd = false;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}
}
