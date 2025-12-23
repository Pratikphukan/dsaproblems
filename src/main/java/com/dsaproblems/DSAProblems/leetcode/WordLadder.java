package com.dsaproblems.DSAProblems.leetcode;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result1 = ladderLengthv2("hit", "cog", wordList1);
        System.out.println(result1);
    }

    //working code
    private static int ladderLengthv2(String begin, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(end)) return 0;
        if (begin.equals(end)) return 1;

        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(begin);
        wordSet.remove(begin);// mark visited by removing

        int level = 1; // BeginWord counts as level 1
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.pollFirst();
                // Try all possible one letter transformations
                char[] wordChars = curr.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char original = wordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue; // Skip same letter
                        wordChars[j] = c;
                        String transformedWord = new String(wordChars);
                        if (transformedWord.equals(end)) return level + 1;
                        if (wordSet.remove(transformedWord)) {// only enqueue if present (and mark visited)
                            queue.addLast(transformedWord);
                        }
                    }
                    wordChars[j] = original;// Restore original character
                }
            }
            level++;// Increment level after processing one round
        }
        return 0; // If we exit the loop, no transformation sequence exists
    }

    private static int ladderLengthv1(String begin, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(end)) return 0;
        Deque<String> queue = new ArrayDeque<>();
        queue.addLast(begin);
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        int level = 1; // BeginWord counts as level 1
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.pollFirst();
                if (curr != null && curr.equals(end)) return level;
                // Try all possible one letter transformations
                char[] wordChars = curr.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue; // Skip same letter
                        wordChars[j] = c;
                        String transformedWord = new String(wordChars);
                        // If the transformed word is in wordSet and not visited, add it to the queue.
                        if (wordSet.contains(transformedWord) && !visited.contains(transformedWord)) {
                            visited.add(transformedWord);
                            queue.addLast(transformedWord);
                        }
                    }
                    wordChars[j] = originalChar;// Restore original character
                }
            }
            level++;// Increment level after processing one round
        }
        return 0; // If we exit the loop, no transformation sequence exists
    }
}
