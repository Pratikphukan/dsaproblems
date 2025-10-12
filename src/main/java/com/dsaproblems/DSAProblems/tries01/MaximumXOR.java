package com.dsaproblems.DSAProblems.tries01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumXOR {

    static class TrieNode {

        private TrieNode left;

        private TrieNode right;
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(25, 10, 2, 8, 5, 3));
        // numbers in the array will be positive
        System.out.println(findMaximumXORv1(input));
    }

    private static int findMaximumXORv1(List<Integer> input) {
        int maxXOR = 0;
        int highestIdxOfMSB = 0;
        // get the highest most significant bit
        for (Integer num : input) {
            highestIdxOfMSB = Math.max(highestIdxOfMSB, (int) (Math.log(num) / Math.log(2)) + 1);
        }
        TrieNode root = new TrieNode();
        for (Integer num : input) {
            System.out.println(Integer.toBinaryString(num));
            insertIntoTrie(num, highestIdxOfMSB, root);
        }
        for (Integer num : input) {
            maxXOR = Math.max(maxXOR, getOtherXORPair(num, highestIdxOfMSB, root));
        }
        return maxXOR;
    }

    private static int getOtherXORPair(Integer num, int highestIdxOfMSB, TrieNode root) {
        TrieNode temp = root;
        int result = 0;
        for (int i = highestIdxOfMSB - 1; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (temp.right != null) {
                    temp = temp.right;
                    result |= (1 << i);
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.left != null) {
                    temp = temp.left;
                    result |= (1 << i);
                } else {
                    temp = temp.right;
                }
            }
        }
        return result;
    }

    private static void insertIntoTrie(Integer num, int highestIdxOfMSB, TrieNode root) {
        TrieNode temp = root;
        for (int i = highestIdxOfMSB - 1; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (temp.left == null) temp.left = new TrieNode();
                temp = temp.left;
            } else {
                if (temp.right == null) temp.right = new TrieNode();
                temp = temp.right;
            }
        }
    }
}
