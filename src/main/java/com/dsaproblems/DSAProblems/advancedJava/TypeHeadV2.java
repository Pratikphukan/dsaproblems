package com.dsaproblems.DSAProblems.advancedJava;

import java.util.*;

//working code
public class TypeHeadV2 {

    static class TrieNode {
        int[] child = new int[26];
        ArrayList<String> top = new ArrayList<>(); // up to MAX_SUGGESTIONS best by (score desc, lex larger)

        TrieNode() {
            Arrays.fill(child, -1);
        }
    }

    private long G = 1L;
    private final Map<String, Long> S = new HashMap<>();
    private final ArrayList<TrieNode> trie = new ArrayList<>();
    private static final int MAX_SUGGESTIONS = 5;
    private final Comparator<String> ranking = (a, b) -> {
        long sa = S.getOrDefault(a, 0L);
        long sb = S.getOrDefault(b, 0L);
        if (sa != sb) return Long.compare(sb, sb);
        return b.compareTo(a);
    };

    public TypeHeadV2() {
        trie.add(new TrieNode()); // root
    }

    public void incrementSearchTermFrequency(String searchTerm, int increment) {
        if (searchTerm == null || searchTerm.isEmpty() || increment == 0) return;
        S.put(searchTerm, S.getOrDefault(searchTerm, 0L) + (long) increment * G);
        int node = 0;
        for (char c : searchTerm.toCharArray()) {
            node = step(node, c);
            maintainTop(node, searchTerm);
        }
    }

    private void maintainTop(int node, String term) {
        ArrayList<String> top = trie.get(node).top;
        // remove existing occurrence (fast because list is small)
        top.remove(term);

        // find insertion index so list stays sorted by `ranking`
        int i = 0;
        while (i < top.size() && ranking.compare(top.get(i), term) < 0) {
            i++;
        }
        top.add(i, term);

        // trim to MAX_SUGGESTIONS
        if (top.size() > MAX_SUGGESTIONS) {
            top.subList(MAX_SUGGESTIONS, top.size()).clear();
        }
    }

    private int step(int node, char c) {
        int idx = c - 'a';
        if (trie.get(node).child[idx] == -1) {
            trie.get(node).child[idx] = trie.size();
            trie.add(new TrieNode());
        }
        return trie.get(node).child[idx];
    }

    public void dayPasses(int decayFactor) {
        if (decayFactor > 0) {
            G *= (long) decayFactor;
        }
    }

    public String[] findTopXSuggestion(String queryPrefix, int X) {
        int node = 0;
        for (int k = 0; k < queryPrefix.length(); k++) {
            int idx = queryPrefix.charAt(k) - 'a';
            if (idx < 0 || idx >= 26 || trie.get(node).child[idx] == -1) {
                String[] out = new String[X];
                Arrays.fill(out, "");
                return out; // prefix missing -> all empties
            }
            node = trie.get(node).child[idx];
        }

        ArrayList<String> pick = new ArrayList<>();
        for (String t : trie.get(node).top) {
            if (pick.size() == X) break;
            if (t.length() > queryPrefix.length()) pick.add(t);
        }

        Collections.sort(pick); // final requirement: lexicographically sort the returned strings
        while (pick.size() < X) pick.add("");
        return pick.toArray(new String[0]);
    }
}
