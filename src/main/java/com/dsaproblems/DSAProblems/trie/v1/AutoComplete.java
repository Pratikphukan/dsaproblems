package com.dsaproblems.DSAProblems.trie.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AutoComplete {

    // Suggestion class to store a word and its weight
    static class Suggestion implements Comparable<Suggestion> {
        String word; // The word itself
        int weight;  // The corresponding weight

        // Constructor to initialize word and weight
        public Suggestion(String word, int weight) {
            this.word = word; // Initialize the word
            this.weight = weight; // Initialize the weight
        }

        // Overriding compareTo method to sort in descending order of weight
        public int compareTo(Suggestion other) {
            return other.weight - this.weight; // Higher weight comes first
        }
    }

    static class TrieNode {
        TrieNode[] children; // Array of children nodes for each letter 'a' to 'z'
        ArrayList<Suggestion> suggestions; // List to store top suggestions that pass through this node

        // Constructor to initialize TrieNode
        public TrieNode() {
            children = new TrieNode[26]; // There are 26 possible lowercase letters
            suggestions = new ArrayList<>(); // Initialize the suggestion list
        }
    }

    static TrieNode root = new TrieNode();

    // Method to insert a word along with its weight into the trie
    public static void insert(String word, int weight) {
        TrieNode curr = root; // Start from the root node
        // Traverse each character in the word
        for (int i = 0; i < word.length(); i++) {
            // Compute the index for this character (e.g., 'a' -> 0)
            int index = word.charAt(i) - 'a';
            // If the child node does not exist, create a new TrieNode
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index]; // Move to the child node

            // Create a new suggestion for the current word
            Suggestion suggestion = new Suggestion(word, weight);
            // Add this suggestion to the current node's suggestion list
            curr.suggestions.add(suggestion);
            // Sort the list so that the highest weights come first
            Collections.sort(curr.suggestions);
            // If there are more than 5 suggestions, remove the lowest weight suggestion
            if (curr.suggestions.size() > 5) {
                curr.suggestions.remove(curr.suggestions.size() - 1);
            }
        }
    }

    // Method to query the trie for a given prefix and return the suggestions
    public static List<String> query(String prefix) {
        TrieNode curr = root; // Start from the root node
        // Traverse the trie based on each character in the prefix
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a'; // Calculate index for the character
            // If the path does not exist, return null indicating no suggestions
            if (curr.children[index] == null) {
                return null;
            }
            curr = curr.children[index]; // Move to the next node
        }
        // If we have reached the node, extract suggestions
        List<String> result = new ArrayList<>();
        for (Suggestion s : curr.suggestions) {
            // Add the word from each suggestion into our result list
            result.add(s.word);
        }
        return result;
    }

    public static void main(String[] args) {
        // Create scanner object to read from standard input
        Scanner sc = new Scanner(System.in);

        // Read the number of test cases
        int T = sc.nextInt();
        // Process each test case
        for (int t = 0; t < T; t++) {
            // Reset the trie root for each test case (to avoid mixing data)
            root = new TrieNode();

            // Read the number of dictionary words and number of prefix queries
            int N = sc.nextInt();
            int M = sc.nextInt();

            // Array to store the dictionary words
            String[] words = new String[N];
            // Read the N words from input
            for (int i = 0; i < N; i++) {
                words[i] = sc.next();
            }

            // Array to store the corresponding weights for each word
            int[] weights = new int[N];
            for (int i = 0; i < N; i++) {
                weights[i] = sc.nextInt();
            }

            // Insert every word into the trie with its corresponding weight
            for (int i = 0; i < N; i++) {
                insert(words[i], weights[i]);
            }

            // Process each prefix query
            for (int i = 0; i < M; i++) {
                String prefix = sc.next();      // Read the prefix
                List<String> suggestions = query(prefix); // Query the trie for suggestions
                // If no suggestions exist for the prefix, print -1
                if (suggestions == null || suggestions.size() == 0) {
                    System.out.print("-1 ");
                } else {
                    // Otherwise, print each suggestion separated by a space
                    for (String word : suggestions) {
                        System.out.print(word + " ");
                    }
                }
                // Print a newline after every query's output
                System.out.println();
            }
        }
        // Close the scanner after processing
        sc.close();
    }
}
