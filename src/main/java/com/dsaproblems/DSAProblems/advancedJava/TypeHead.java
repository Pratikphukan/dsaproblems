package com.dsaproblems.DSAProblems.advancedJava;

import java.util.*;

public class TypeHead {

    static class TrieNode {
        TrieNode[] children;
        ArrayList<String> suggestion;

        TrieNode() {
            this.children = new TrieNode[26];
            this.suggestion = new ArrayList<>();
        }
    }

    private final HashMap<String, Integer> searchTermFreq;
    private final TrieNode root;

    public TypeHead() {
        this.searchTermFreq = new HashMap<>();
        this.root = new TrieNode();
    }

    public void incrementSearchTermFrequency(String s, int incr) {
        int newFreq = searchTermFreq.getOrDefault(s, 0) + incr;
        searchTermFreq.put(s, newFreq);
        TrieNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            TrieNode child = temp.children[ch];
            if (child == null) {
                child = new TrieNode();
                child.suggestion.add(s);
                temp.children[ch] = child;
            } else {
                boolean found = false;
                for (String str : child.suggestion) {
                    if (str.equals(s)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    if (child.suggestion.size() < 5) {
                        child.suggestion.add(s);
                        sortSuggestions(child.suggestion);
                    } else {
                        child.suggestion.add(s);
                        sortSuggestions(child.suggestion);
                        child.suggestion.remove(5);
                    }
                }
            }
            temp = child;
        }
    }

    private void sortSuggestions(ArrayList<String> suggestion) {
        suggestion.sort((s1, s2) -> {
            if (!searchTermFreq.get(s1).equals(searchTermFreq.get(s2)))
                return searchTermFreq.get(s2) - searchTermFreq.get(s1);
            return s2.compareTo(s1);
        });
    }

    public String[] findTopXSuggestion(String prefix, int X) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ch = prefix.charAt(i) - 'a';
            TrieNode child = temp.children[ch];
            if (child == null) {
                temp = null;
                break;
            }
            temp = child;
        }
        String[] suggestions = new String[X];
        if (temp == null) {
            Arrays.fill(suggestions, "");
        } else {
            sortSuggestions(temp.suggestion);
            int count = 0;
            for (int i = 0; i < Math.min(X, temp.suggestion.size()); ++i) {
                suggestions[i] = temp.suggestion.get(i);
                ++count;
            }
            for (int i = count; i < X; ++i) {
                suggestions[i] = "";
            }
            Arrays.sort(suggestions, (s1, s2) -> s1.compareTo(s2));
        }
        return suggestions;
    }
}