package com.dsaproblems.DSAProblems.trie.v1;

import java.util.*;

public class SpellingChecker {

    static class TNode {

        TNode[] children;
        boolean isEnd;

        TNode(boolean isEnd) {
            this.children = new TNode[26];
            this.isEnd = isEnd;
        }
    }

    //put(key, value) inserts or updates the value for the given key, overwriting any existing value.
    //putIfAbsent(key, value) only inserts the value if the key is not already present; if the key exists, it leaves the value unchanged.
    public static void main(String[] args) {
        List<String> A = new ArrayList<>(Arrays.asList("hat", "harp", "cat", "cater"));
        List<String> B = new ArrayList<>(Arrays.asList("cat", "ball"));
        System.out.println(checkValidWordsInBv1(A, B));
        System.out.println(checkValidWordsInBv2(A, B));
        System.out.println(checkValidWordsInBv3(A, B));
    }

    //working code
    private static ArrayList<Integer> checkValidWordsInBv3(List<String> A, List<String> B) {
        TNode root = new TNode(false);
        for (String word : A) {
            TNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.children[c - 'a'] == null)
                    temp.children[c - 'a'] = new TNode(word.charAt(word.length() - 1) == c);
                temp = temp.children[c - 'a'];
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (String word : B) {
            TNode temp = root;
            boolean found = true;
            for (char c : word.toCharArray()) {
                temp = temp.children[c - 'a'];
                if (temp == null) {
                    found = false;
                    break;
                }
            }
            result.add(found && temp != null && temp.isEnd ? 1 : 0);
        }
        return result;
    }

    //working code
    private static ArrayList<Integer> checkValidWordsInBv2(List<String> A, List<String> B) {
        // Add each word from the dictionary array to the HashSet.
        // This allows O(1) average lookup for each word in B.
        HashSet<String> dictionarySet = new HashSet<>(A);

        // Prepare an array to store the result of checks for each word in B.
        ArrayList<Integer> result = new ArrayList<>();
        for (String word : B) {
            // Check if the current word in B is contained in the dictionarySet.
            // If yes, mark the corresponding index in result as 1.
            // Else, mark it as 0.
            result.add(dictionarySet.contains(word) ? 1 : 0);
        }
        // Return the binary result array.
        return result;
    }

    //working code
    private static ArrayList<Integer> checkValidWordsInBv1(List<String> A, List<String> B) {
        TrieNode<Character> root = new TrieNode<>(false);
        for (String word : A) {
            TrieNode<Character> node = root;
            for (char c : word.toCharArray()) {
                node.next.putIfAbsent(c, new TrieNode<>(word.charAt(word.length() - 1) == c));
                node = node.next.get(c);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (String word : B) {
            TrieNode<Character> node = root;
            boolean found = true;
            for (char c : word.toCharArray()) {
                if (!node.next.containsKey(c)) {
                    found = false;
                    break;
                }
                node = node.next.get(c);
            }
            result.add(found && node.isEnd ? 1 : 0);
        }
        return result;
    }

}
