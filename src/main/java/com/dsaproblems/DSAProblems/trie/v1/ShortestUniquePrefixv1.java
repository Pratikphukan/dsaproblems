package com.dsaproblems.DSAProblems.trie.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShortestUniquePrefixv1 {

    static class TrieNode<T> {

        Map<T, TrieNode<T>> children;
        boolean isEnd;
        int frequency;

        TrieNode(boolean isEnd) {
            this.children = new HashMap<>();
            this.isEnd = isEnd;
            this.frequency = 0;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> A = new ArrayList<>(Arrays.asList("zebra", "dog", "duck", "dove"));
        System.out.println(shortestUniquePrefixesv2(A));
    }

    //working code
    private static ArrayList<String> shortestUniquePrefixesv2(ArrayList<String> A) {

        TrieNode<Character> root = new TrieNode<>(false);

        // Insert all words and update frequency
        for (String word : A) {
            TrieNode<Character> curr = root;
            for (char c : word.toCharArray()) {
                curr.children.putIfAbsent(c, new TrieNode<>(false));
                curr = curr.children.get(c);
                curr.frequency++;
            }
        }

        // Find shortest unique prefix for each word
        ArrayList<String> result = new ArrayList<>();
        for (String word : A) {
            TrieNode<Character> curr = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : word.toCharArray()) {
                curr = curr.children.get(c);
                prefix.append(c);
                if (curr.frequency == 1) break;
            }
            result.add(prefix.toString());
        }
        return result;
    }
}
