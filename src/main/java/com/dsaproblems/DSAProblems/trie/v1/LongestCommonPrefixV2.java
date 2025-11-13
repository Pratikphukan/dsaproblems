package com.dsaproblems.DSAProblems.trie.v1;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonPrefixV2 {

    private static TrieNode root = new TrieNode();

    static class TrieNode {
        Map<Character, TrieNode> nodes;
        boolean isEnd;

        TrieNode() {
            nodes = new HashMap<>();
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefixv1(new String[]{"flower", "flow", "flight"}));
    }

    private static String longestCommonPrefixv1(String[] strs) {
        for (String item : strs) {
            insertIntoTrie(root, item);
        }
        StringBuilder prefix = new StringBuilder();
        while (!root.nodes.isEmpty()) {
            if (root.nodes.size() > 1 || root.isEnd)
                return prefix.toString();
            prefix.append(root.nodes.keySet().iterator().next());
            root = root.nodes.values().iterator().next();
        }
        return prefix.toString();
    }

    private static void insertIntoTrie(TrieNode root, String item) {
        TrieNode curr = root;
        for (char c : item.toCharArray()) {
            if (!curr.nodes.containsKey(c))
                curr.nodes.put(c, new TrieNode());
            curr = curr.nodes.get(c);
        }
        curr.isEnd = true;
    }
}
