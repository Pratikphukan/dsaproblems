package com.dsaproblems.DSAProblems.trie.v1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AutoCompletev1 {

    static TrieNode root = new TrieNode();

    static class TrieNode {
        TrieNode[] children;
        ArrayList<Suggestion> suggestions;

        TrieNode() {
            this.children = new TrieNode[26];
            this.suggestions = new ArrayList<>();
        }
    }

    static class Suggestion implements Comparable<Suggestion> {
        String word;
        int weight;

        Suggestion(String word, int weight) {
            this.word = word;
            this.weight = weight;
        }

        @Override
        public int compareTo(Suggestion suggestion) {
            return suggestion.weight - this.weight;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("abcd", "aecd", "abaa", "abef", "acdcc", "acbcc"));
        ArrayList<Integer> weights = new ArrayList<>(Arrays.asList(2, 1, 3, 4, 6, 5));
        ArrayList<String> prefixes = new ArrayList<>(Arrays.asList("ab", "abc", "a"));

        System.out.println(getTop5SuggestionsForPrefixv1(words, weights, prefixes));
    }

    private static void insert(String word, Integer wt) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            Suggestion suggestion = new Suggestion(word, wt);

//            curr.suggestions.add(suggestion);
//            if (curr.suggestions.size() > 1)
//                Collections.sort(curr.suggestions);

            int pos = Collections.binarySearch(curr.suggestions, suggestion);
            if (pos < 0) pos = -pos - 1;
            curr.suggestions.add(pos, suggestion);

            if (curr.suggestions.size() > 5) {
                curr.suggestions.remove(curr.suggestions.size() - 1);
            }
        }
    }

    private static ArrayList<String> query(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                return null;
            }
            curr = curr.children[idx];
        }
        ArrayList<String> result = new ArrayList<>();
        for (Suggestion s : curr.suggestions) {
            result.add(s.word);
        }
        return result;
    }

    private static ArrayList<ArrayList<String>> getTop5SuggestionsForPrefixv1(ArrayList<String> words, ArrayList<Integer> weights, ArrayList<String> prefixes) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            insert(words.get(i), weights.get(i));
        }
        for (String prefix : prefixes) {
            ArrayList<String> suggestions = query(prefix);
            result.add(suggestions);
        }
        return result;
    }
}
