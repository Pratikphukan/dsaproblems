package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonPrefix {

    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26]; // Assuming only lowercase a-z
            isEnd = false;
        }
    }

    public static void main(String[] args) {
        //"abab", "ab", "abcd"
        //"abcdefgh", "aefghijk", "abcefgh"
        //"abcdefgh", "aefgabck", "efghabc";  a prefix is a substring that starts at the beginning of the string
        List<String> input = new ArrayList<>(Arrays.asList("abab", "ab", "abcd"));
        System.out.println(findLongestCommonPrefixv1(input));
        System.out.println(findLongestCommonPrefixv2(input));

        System.out.println(findLongestCommonPrefixv3(new String[]{"flower", "flow", "flight"}));

        System.out.println(findLongestCommonPrefixv4(new String[]{"flower", "flow", "flight"}));

        System.out.println(findLongestCommonPrefixv5(new String[]{"flower", "flow", "flight"}));

        System.out.println(findLongestCommonPrefixv6(new String[]{"flower", "flow", "flight"}));
    }

    private static String findLongestCommonPrefixv6(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        TrieNode root = new TrieNode();
        for (String word : strs) insert(root, word);

        StringBuilder prefix = new StringBuilder();
        TrieNode node = root;
        while (true) {
            int count = 0, idx = -1;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    count++;
                    idx = i;
                }
            }
            if (count != 1 || node.isEnd) break;
            prefix.append((char) ('a' + idx));
            node = node.children[idx];
        }
        return prefix.toString();
    }

    private static void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    //working code
    private static String findLongestCommonPrefixv5(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return lcp(strs, 0, strs.length - 1);
    }

    private static String lcp(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        } else {
            int mid = (left + right) / 2;
            String lcpLeft = lcp(strs, left, mid);
            String lcpRight = lcp(strs, mid + 1, right);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    //working code
    //TC: O(N*M) where N is number of strings and M is length of the shortest string
    //SC: O(1)
    private static String findLongestCommonPrefixv4(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // LCP length can’t exceed the shortest string
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) minLen = Math.min(minLen, s.length());
        if (minLen == 0) return "";

        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (int k = 1; k < strs.length; k++) {
                if (strs[k].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0].substring(0, minLen);
    }

    //working code
    private static String findLongestCommonPrefixv3(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int minLen = Integer.MAX_VALUE;
        String prefix = "";
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
                prefix = s;
            }
        }
        for (String str : strs) {
            while (!prefix.isEmpty() && str.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, --minLen);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    private static String findLongestCommonPrefixv2(List<String> A) {
        if (A.isEmpty()) return "";
        StringBuilder res = new StringBuilder();
        int min = Integer.MAX_VALUE;
        for (String s : A) {
            min = Math.min(min, s.length());
        }
        for (int i = 0; i < min; i++) {
            char c = A.get(0).charAt(i);
            // check if c character is same in every string or not
            for (int j = 1; j < A.size(); j++) {

                if (c != A.get(j).charAt(i)) return res.toString();
            }
            res.append(c);
        }
        return res.toString();
    }

    private static String findLongestCommonPrefixv1(List<String> input) {
        String possiblePrefix = input.get(0);
        for (String val : input) {
            while (val.indexOf(possiblePrefix) != 0) {
                possiblePrefix = possiblePrefix.substring(0, possiblePrefix.length() - 1);
                if (possiblePrefix.isEmpty()) return "";
            }
        }
        return possiblePrefix;
    }
}
