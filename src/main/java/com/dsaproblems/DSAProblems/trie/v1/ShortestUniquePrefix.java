package com.dsaproblems.DSAProblems.trie.v1;

import java.util.ArrayList;
import java.util.Arrays;

public class ShortestUniquePrefix {

    private static TrieNode root = new TrieNode();

    static class TrieNode {

        TrieNode[] children;
        int count;

        TrieNode() {
            this.children = new TrieNode[26];
            this.count = 0;
        }
    }

    private static void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            curr.count++;
        }
    }

    private static String findPrefix(String word) {
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {
            prefix.append(c);
            int idx = c - 'a';
            curr = curr.children[idx];
            if (curr.count == 1) break;
        }

        return prefix.toString();
    }

    //working code
    public static void main(String[] args) {
        ArrayList<String> A = new ArrayList<>(Arrays.asList("zebra", "dog", "duck", "dove"));
        System.out.println(shortestUniquePrefixesv1(A));
    }

    private static ArrayList<String> shortestUniquePrefixesv1(ArrayList<String> A) {
        for (String word : A) {
            insert(word.trim());
        }
        ArrayList<String> result = new ArrayList<>(A.size());
        for (String word : A) {
            result.add(findPrefix(word.trim()));
        }
        return result;
    }
}
