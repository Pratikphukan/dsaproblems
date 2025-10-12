package com.dsaproblems.DSAProblems.tries01;

import com.dsaproblems.DSAProblems.trie.AlphabetTrieNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellingChecker {

    public static void main(String[] args) {
//        List<String> A = new ArrayList<>(Arrays.asList("hat", "harp", "cat", "cater"));
//        List<String> B = new ArrayList<>(Arrays.asList("cat", "ball"));

//        List<String> A = new ArrayList<>(Arrays.asList("a", "b"));
//        List<String> B = new ArrayList<>(Arrays.asList("a", "b"));

        List<String> A = new ArrayList<>(Arrays.asList("ab", "abc", "abcd", "abcde", "abcdef", "abcdefg"));
        List<String> B = new ArrayList<>(Arrays.asList("a", "b", "ab", "abcd"));
        System.out.println(checkValidWordsInBv1(A, B));
        System.out.println(checkValidWordsInBv2(A, B));
    }

    //working code
    private static List<Integer> checkValidWordsInBv2(List<String> A, List<String> B) {
        TrieNode<Character> root = new TrieNode<>(false);

        // Insert all words from A into the Trie
        TrieNode<Character> node = null;
        for (String word : A) {
            node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.getNode().putIfAbsent(c, new TrieNode<>(i == (word.length() - 1)));
                node = node.getNode().get(c);
            }
        }

        // Check each word in B
        List<Integer> result = new ArrayList<>();
        for (String word : B) {
            node = root;
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.getNode().containsKey(c)) {
                    found = false;
                    break;
                }
                node = node.getNode().get(c);
            }
            result.add(found && node.isEnd() ? 1 : 0);
        }
        return result;
    }

    //working code
    private static List<Integer> checkValidWordsInBv1(List<String> A, List<String> B) {
        TrieNode<Character> root = new TrieNode<>(false);
        TrieNode<Character> temp = root;
        for (String item : A) {
            for (int i = 0; i < item.length(); i++) {
                if (!temp.getNode().containsKey(item.charAt(i))) {
                    temp.getNode().put(item.charAt(i), new TrieNode<>(i == (item.length() - 1)));
                }
                temp = temp.getNode().get(item.charAt(i));
            }
            temp = root;
        }
        List<Integer> wordCheck = new ArrayList<>();
        for (String item : B) {
            for (int i = 0; i < item.length(); i++) {
                if (!temp.getNode().containsKey(item.charAt(i))) {
                    wordCheck.add(0);
                    break;
                }
                temp = temp.getNode().get(item.charAt(i));
                if (i == (item.length() - 1)) {
                    wordCheck.add(temp.isEnd() ? 1 : 0);
                }
            }
            temp = root; // Reset temp for the next word check
        }
        return wordCheck;
    }
}
