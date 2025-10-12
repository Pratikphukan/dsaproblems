package com.dsaproblems.DSAProblems.queue;

import com.dsaproblems.DSAProblems.trie01.A;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class UniqueLetter {

    public static void main(String[] args) {
        String A = "abadbc";
        System.out.println(solve(A));
        System.out.println(findFirstUniqueForEveryIdxv1(A));
    }

    //working code
    private static String findFirstUniqueForEveryIdxv1(String A) {
        Map<Character, Integer> freq = new HashMap<>();
        Deque<Character> queue = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();
        for (char ch : A.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            queue.addLast(ch);
            while (!queue.isEmpty() && freq.get(queue.peekFirst()) > 1) { //remove any character from the front whose frequency is greater than 1
                queue.pollFirst();
            }
            ans.append(queue.isEmpty() ? '#' : queue.peekFirst());
        }
        return ans.toString();
    }

    //working code
    public static String solve(String A) {
        int[] freq = new int[26];
        Deque<Character> q = new ArrayDeque<>();
        StringBuilder out = new StringBuilder(A.length());
        for (char ch : A.toCharArray()) {
            int i = ch - 'a';
            freq[i]++;
            q.offerLast(ch);
            while (!q.isEmpty() && freq[q.peekFirst() - 'a'] > 1) {
                q.pollFirst();
            }
            out.append(q.isEmpty() ? '#' : q.peekFirst());
        }
        return out.toString();
    }
}
