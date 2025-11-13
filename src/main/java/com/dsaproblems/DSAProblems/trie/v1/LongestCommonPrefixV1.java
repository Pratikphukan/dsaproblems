package com.dsaproblems.DSAProblems.trie.v1;

public class LongestCommonPrefixV1 {

    private static TrieNode root = new TrieNode();

    static class TrieNode {

        TrieNode[] children;
        int count;

        TrieNode() {
            this.children = new TrieNode[26];
            this.count = 0;
        }
    }

    public static void main(String[] args) {
        String[] A = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefixv1(A));
        System.out.println(longestCommonPrefixv2(A));
        System.out.println(longestCommonPrefixv3(A));
    }

    //working code
    private static String longestCommonPrefixv3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c)
                    return first.substring(0, i);
            }
        }
        return first;
    }

    private static String longestCommonPrefixv2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Initialize the prefix as the first string in the array
        // This is our starting candidate for the common prefix
        String prefix = strs[0];

        // Iterate over the strings starting from the second element
        for (int i = 1; i < strs.length; i++) {
            // While the current string does not start with the prefix,
            // trim the last character from the prefix
            while (strs[i].indexOf(prefix) != 0) {
                // Debug: This loop ensures that we reduce the prefix until it
                // becomes a prefix of the current string
                prefix = prefix.substring(0, prefix.length() - 1);

                // If the prefix becomes empty, then no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        // After processing all strings, return the computed prefix
        return prefix;
    }

    //not working/incomplete
    private static boolean longestCommonPrefixv1(String[] A) {
        for (String word : A) {
            insert(word.trim());
        }
        System.out.println(root);
        System.out.println(root.children.length);
        findNumberOfNonNullNodes(root);
        return false;
    }

    private static int findNumberOfNonNullNodes(TrieNode root) {
        int count = 0;
        for (TrieNode child : root.children) {
            if (child != null) count++;
        }
        return count;
    }

    private static void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
            curr.count++;
        }
    }
}
