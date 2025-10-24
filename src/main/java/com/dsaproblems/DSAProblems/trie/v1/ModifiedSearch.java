package com.dsaproblems.DSAProblems.trie.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ModifiedSearch {

    public static void main(String[] args) {

    }

    public String solve(ArrayList<String> A, ArrayList<String> B) {
        // HashSet to store all dictionary words from A for quick exact-match look-up.
        HashSet<String> dictionary = new HashSet<>(A);

        // HashMap to store patterns from words in A and their frequencies.
        // Key: a pattern string with one character replaced by '*' (wildcard)
        // Value: number of dictionary words that produce this pattern.
        HashMap<String, Integer> patternMap = new HashMap<>();

        // Iterate over every word in dictionary A
        for (String word : A) {
            int len = word.length(); // length of the word
            // For every index, create a pattern by replacing the character at that index
            for (int i = 0; i < len; i++) {
                // Create pattern: substring(0, i) + '*' + substring(i+1)
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                // Update the pattern frequency count in the map
                patternMap.put(pattern, patternMap.getOrDefault(pattern, 0) + 1);
            }
        }

        // StringBuilder to accumulate the binary result for each query in B.
        StringBuilder result = new StringBuilder();

        // Process every query string in B.
        for (String query : B) {
            // Flag to mark if a valid modification is found
            boolean isValidModification = false;

            int len = query.length(); // length of the query string

            // Only attempt to find a pattern if there is a word of the same length in A
            // Otherwise, skip and mark as '0' straight-away.
            // We can check this by verifying that at least one pattern would exist.
            // Since the patternMap keys contain words of various lengths, we check pattern lengths.

            // For each index in the query string, generate a pattern
            for (int i = 0; i < len; i++) {
                // Build the pattern by inserting '*' at the i-th position.
                String pattern = query.substring(0, i) + "*" + query.substring(i + 1);

                // If the pattern is present in our pattern map, then there are dictionary words
                // that differ by exactly one character at the i-th position.
                if (patternMap.containsKey(pattern)) {
                    int count = patternMap.get(pattern); // frequency of this pattern in dictionary A

                    // If the query itself exists in the dictionary A, then we must ensure
                    // that the candidate is not just the same word as the query.
                    // In that case, the same pattern would have counted the query itself.
                    if (dictionary.contains(query)) {
                        // To count as a valid modification, there must be at least one other word producing that pattern.
                        if (count > 1) {
                            isValidModification = true; // valid candidate found
                            break; // exit the loop early.
                        }
                    } else {
                        // If the query does not exactly match any word in A,
                        // any occurrence of the pattern indicates a valid one-character-different candidate.
                        isValidModification = true;
                        break; // exit the loop once a valid candidate is found.
                    }
                }
            }

            // Append '1' if a valid modification candidate is found, otherwise append '0'.
            result.append(isValidModification ? "1" : "0");
        }

        // Return the final binary string answer.
        return result.toString();
    }
}
