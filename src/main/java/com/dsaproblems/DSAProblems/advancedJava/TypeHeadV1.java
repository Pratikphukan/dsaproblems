package com.dsaproblems.DSAProblems.advancedJava;

import java.util.*;

public class TypeHeadV1 {
    // Global-decay: real freq(term) = S(term) / G
    private long G = 1L;
    private final HashMap<String, Long> S = new HashMap<>();

    // Trie definition
    private static class TrieNode {
        int[] child = new int[26];
        ArrayList<String> top = new ArrayList<>(); // up to 5 best by (score desc, lex larger)

        TrieNode() {
            Arrays.fill(child, -1);
        }
    }

    private final ArrayList<TrieNode> trie = new ArrayList<>();

    public TypeHeadV1() {
        trie.add(new TrieNode()); // root
    }

    public void incrementSearchTermFrequency(String search_term, int increment) {
        S.put(search_term, S.getOrDefault(search_term, 0L) + (long) increment * G);
        int node = 0;
        for (int k = 0; k < search_term.length(); k++) {
            node = step(node, search_term.charAt(k));
            maintainTop(node, search_term);
        }
    }

    public String[] findTopXSuggestion(String queryPrefix, int X) {
        int node = 0;
        for (int k = 0; k < queryPrefix.length(); k++) {
            int idx = queryPrefix.charAt(k) - 'a';
            if (trie.get(node).child[idx] == -1) {
                String[] out = new String[X];
                Arrays.fill(out, "");
                return out; // prefix missing -> all empties
            }
            node = trie.get(node).child[idx];
        }

        // Take up to X best; enforce STRICT prefix (term longer than prefix)
        ArrayList<String> pick = new ArrayList<>();
        for (String t : trie.get(node).top) {
            if (pick.size() == X) break;
            if (t.length() > queryPrefix.length()) pick.add(t);
        }

        // Final requirement: lexicographically sort the returned strings, then pad with ""
        Collections.sort(pick);
        while (pick.size() < X) pick.add("");
        return pick.toArray(new String[0]);
    }

    public void dayPasses(int decayFactor) {
        G *= (long) decayFactor;
    }

    /* ---------------- internals ---------------- */

    // Comparator for node.top ranking
    private static class Better implements Comparator<String> {
        final HashMap<String, Long> Sref;

        Better(HashMap<String, Long> s) {
            this.Sref = s;
        }

        @Override
        public int compare(String a, String b) {
            long sa = Sref.getOrDefault(a, 0L);
            long sb = Sref.getOrDefault(b, 0L);
            if (sa != sb) return Long.compare(sb, sa); // higher score first
            return b.compareTo(a);                      // tie -> lexicographically larger first
        }
    }

    private int step(int node, char ch) {
        int i = ch - 'a';
        if (trie.get(node).child[i] == -1) {
            trie.get(node).child[i] = trie.size();
            trie.add(new TrieNode());
        }
        return trie.get(node).child[i];
    }

    private void maintainTop(int node, String term) {
        ArrayList<String> top = trie.get(node).top;
        boolean present = false;
        for (String s : top) {
            if (s.equals(term)) {
                present = true;
                break;
            }
        }
        if (!present) top.add(term);
        top.sort(new Better(S));
        if (top.size() > 5) {
            while (top.size() > 5) top.remove(top.size() - 1);
        }
    }
}
