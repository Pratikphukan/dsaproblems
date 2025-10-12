package com.dsaproblems.DSAProblems.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumXORSubarray {

    static class TrieNode {

        TrieNode[] children;
        int idx;

        TrieNode() {
            this.children = new TrieNode[2];
            this.idx = -1;
        }
    }

    public static void main(String[] args) {
        // List<Integer> input = new ArrayList<>(Arrays.asList(25, 10, 2, 8, 5, 3));
        // List<Integer> input = new ArrayList<>(Arrays.asList(33, 29, 18));
        // List<Integer> input = new ArrayList<>(Arrays.asList(28, 31, 13, 22, 17, 22));
        List<Integer> input = new ArrayList<>(Arrays.asList(32, 17, 9, 15, 28, 41, 10));
        // numbers in the array will be positive
        //System.out.println(findMaximumXORSubarrayv1(input));
        System.out.println(findMaximumXORSubarrayv2(input));
    }

    private static List<Integer> findMaximumXORSubarrayv2(List<Integer> A) {
        TrieNode root = new TrieNode();
        int n = A.size();
        int[] prefixXor = new int[n + 1];
        prefixXor[0] = 0;
        int maxXor = Integer.MIN_VALUE, start = -1, end = -1;

        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (prefixXor[0] >> i) & 1;
            if (curr.children[bit] == null) curr.children[bit] = new TrieNode();
            curr = curr.children[bit];
        }
        curr.idx = 0;
        for (int i = 1; i <= n; i++) {
            prefixXor[i] = prefixXor[i - 1] ^ A.get(i - 1);

            // Query Trie for best XOR
            curr = root;
            int currXor = 0;
            for (int j = 31; j >= 0; j--) {
                int bit = (prefixXor[i] >> j) & 1;
                int toggled = bit ^ 1;
                if (curr.children[toggled] != null) {
                    currXor |= (1 << j);
                    curr = curr.children[toggled];
                } else {
                    curr = curr.children[bit];
                }
            }
            int prevIdx = curr.idx;
            if (currXor > maxXor || (currXor == maxXor && (i - prevIdx) < (end - start + 1))) {
                maxXor = currXor;
                start = prevIdx + 1;
                end = i;
            }

            // Insert current prefixXor into Trie
            curr = root;
            for (int j = 31; j >= 0; j--) {
                int bit = (prefixXor[i] >> j) & 1;
                if (curr.children[bit] == null) curr.children[bit] = new TrieNode();
                curr = curr.children[bit];
            }
            curr.idx = i;
        }
        return new ArrayList<>(Arrays.asList(start, end));
    }

    // working code
    private static List<Integer> findMaximumXORSubarrayv1(List<Integer> input) {
        List<Integer> prefixXor = new ArrayList<>();
        List<Integer> ans = new ArrayList<>(Arrays.asList(1, 1));
        int maxXOR = input.get(0);
        prefixXor.add(input.get(0));
        //It computes prefix XORs for the array, which allows quick calculation of XOR for any subarray
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) > maxXOR) {
                maxXOR = input.get(i);
                ans.set(0, i + 1);
                ans.set(1, i + 1);
            }
            prefixXor.add(prefixXor.get(i - 1) ^ input.get(i));
            if (prefixXor.get(i) > maxXOR) { //A prefix subarray starting from index 0 is a possible ans
                maxXOR = prefixXor.get(i);
                ans.set(0, 1);
                ans.set(1, i + 1);
            }
        }
        //For each pair (i, j), the XOR of subarray from i to j is prefixXor[j] ^ prefixXor[i-1].
        //It updates the answer if a higher XOR is found or if the same XOR is found with a shorter subarray.
        for (int i = 1; i < input.size(); i++) {
            int left = prefixXor.get(i - 1);
            for (int j = i; j < input.size(); j++) {
                int right = prefixXor.get(j);
                if (maxXOR == (left ^ right) && (ans.get(1) - ans.get(0)) > (j - i)) {
                    ans.set(0, i + 1);
                    ans.set(1, j + 1);
                }
                if (maxXOR < (left ^ right)) {
                    maxXOR = left ^ right;
                    ans.set(0, i + 1);
                    ans.set(1, j + 1);
                }
            }
        }
        return ans;
    }

}
