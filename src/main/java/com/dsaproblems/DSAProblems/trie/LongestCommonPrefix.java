package com.dsaproblems.DSAProblems.trie;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(findLongestCommonPrefixv1(new String[]{"flower", "flow", "flight"}));
    }

    private static String findLongestCommonPrefixv1(String[] strs) {
        AlphabetTrieNode root = new AlphabetTrieNode();
        for (String item : strs) {
            insertIntoTrie(root, item);
        }
        StringBuilder prefix = new StringBuilder();
        while (!root.getNodes().isEmpty()) {
            if (root.getNodes().size() > 1 || root.getIsEnd())
                return prefix.toString();
            prefix.append(root.getNodes().keySet().iterator().next());
            root = root.getNodes().values().iterator().next();
        }
        return prefix.toString();
    }

    public static void insertIntoTrie(AlphabetTrieNode root, String item) {
        AlphabetTrieNode temp = root;
        for (Character alphabet : item.toCharArray()) {
            if (!temp.getNodes().containsKey(alphabet))
                temp.getNodes().put(alphabet, new AlphabetTrieNode());
            temp = temp.getNodes().get(alphabet);
        }
        temp.setIsEnd(true);
    }
}
