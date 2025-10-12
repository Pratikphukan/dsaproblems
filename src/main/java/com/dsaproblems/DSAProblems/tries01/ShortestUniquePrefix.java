package com.dsaproblems.DSAProblems.tries01;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ShortestUniquePrefix {

    @Getter
    static class TrieNode<T> {
        private boolean isEnd;
        private Map<T, TrieNode<T>> node;
        @Setter
        private int frequency;

        public TrieNode(boolean isEnd) {
            this.isEnd = isEnd;
            this.node = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        //"zebra", "dog", "duck", "dove"
        //"dog", "doog", "doe", "donk", "duck", "done"
        //"apple", "ball", "cat"
        List<String> A = new ArrayList<>(Arrays.asList("apple", "ball", "cat"));
        System.out.println(findShortestUniquePrefixv1(A));
        System.out.println(findShortestUniquePrefixv2(A));
    }

    //working code and better version
    private static List<String> findShortestUniquePrefixv2(List<String> A) {
        TrieNode<Character> root = new TrieNode<>(false);

        // Insert all words and update frequency
        TrieNode<Character> node = null;
        for (String word : A) {
            node = root;
            for (char c : word.toCharArray()) {
                node.getNode().putIfAbsent(c, new TrieNode<>(false));
                node = node.getNode().get(c);
                node.setFrequency(node.getFrequency() + 1);
            }
        }

        // Find shortest unique prefix for each word
        List<String> result = new ArrayList<>();
        for (String word : A) {
            node = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : word.toCharArray()) {
                node = node.getNode().get(c);
                prefix.append(c);
                if (node.getFrequency() == 1) {
                    break;
                }
            }
            result.add(prefix.toString());
        }
        return result;
    }

    //working code
    private static List<String> findShortestUniquePrefixv1(List<String> A) {
        TrieNode<Character> root = new TrieNode<>(false);

        // Insert all words from A into the Trie
        TrieNode<Character> node = null;
        for (String word : A) {
            node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.getNode().putIfAbsent(c, new TrieNode<>(i == (word.length() - 1)));
                node = node.getNode().get(c);
                node.setFrequency(node.getFrequency() + 1);
            }
        }
        List<String> result = new ArrayList<>();
        for (String word : A) {
            node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node = node.getNode().get(c);
                if (node.getFrequency() == 1) {
                    result.add(word.substring(0, i + 1));
                    break;
                }
            }
        }
        return result;
    }
}
