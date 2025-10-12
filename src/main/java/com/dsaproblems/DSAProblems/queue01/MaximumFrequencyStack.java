package com.dsaproblems.DSAProblems.queue01;

import java.util.*;

public class MaximumFrequencyStack {

    public static void main(String[] args) {
    }

    public int[] solve(int[][] A) {
        int n = A.length;
        int[] ans = new int[n];
        int index = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, Deque<Integer>> stackHM = new HashMap<>();
        for (int[] a : A) {
            int val = -1;
            if (a[0] == 1) {
                insert(freq, a[1], stackHM);
            } else {
                val = pop(freq, stackHM);
            }
            ans[index++] = val;
        }
        return ans;
    }

    int maxCount = 0;

    int insert(Map<Integer, Integer> freq, int number, HashMap<Integer, Deque<Integer>> stackHM) {
        freq.put(number, freq.getOrDefault(number, 0) + 1);
        int frequency = freq.get(number);
        if (stackHM.containsKey(frequency)) {
            Deque<Integer> top = stackHM.get(frequency);
            top.addFirst(number);
            stackHM.put(frequency, top);
        } else {
            Deque<Integer> st = new ArrayDeque<>();
            st.addFirst(number);
            stackHM.put(frequency, st);
            maxCount++;
        }
        return -1;
    }

    int pop(Map<Integer, Integer> freq, HashMap<Integer, Deque<Integer>> stackHM) {
        Deque<Integer> top = stackHM.get(maxCount);
        int val = top.pop();
        if (top.isEmpty()) {
            stackHM.remove(maxCount);
            maxCount--;
        } else {
            stackHM.put(maxCount, top);
        }
        int count = freq.get(val);
        if (count == 1) {
            freq.remove(val);
        } else {
            freq.put(val, count - 1);
        }
        return val;
    }
}
