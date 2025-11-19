package com.dsaproblems.DSAProblems.trie.v1;

//working code
public class WordDictionary {

    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private final TrieNode root;

    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    private boolean searchInNode(String word, int pos, TrieNode node) {
        if (pos == word.length()) return node.isEnd;
        char curr = word.charAt(pos);
        if (curr == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && searchInNode(word, pos + 1, node.children[i])) return true;
            }
            return false;
        }
        int idx = curr - 'a';
        if (node.children[idx] == null) return false;
        return searchInNode(word, pos + 1, node.children[idx]);
    }
}
