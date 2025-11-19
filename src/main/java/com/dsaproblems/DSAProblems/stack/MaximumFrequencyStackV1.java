package com.dsaproblems.DSAProblems.stack;

import java.util.*;

class FreqStack {
    private final Map<Integer, Integer> freqMap;
    private final Map<Integer, Deque<Integer>> groupMap;
    private int maxFreq = 0;

    FreqStack() {
        this.freqMap = new HashMap<>();
        this.groupMap = new HashMap<>();
    }

    public void push(int val) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);
        groupMap.putIfAbsent(freq, new ArrayDeque<>());
        groupMap.get(freq).addFirst(val);
    }

    public Integer pop() {
        Deque<Integer> stack = groupMap.get(maxFreq);
        if (stack == null || stack.isEmpty()) return null;
        int x = stack.pollFirst();
        freqMap.put(x, freqMap.get(x) - 1);
        if (stack.isEmpty()) {
            maxFreq--;
        }
        return x;
    }
}

public class MaximumFrequencyStackV1 {

    public static ArrayList<Integer> performActionsv2(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> result = new ArrayList<>();
        FreqStack freqStack = new FreqStack();
        for (ArrayList<Integer> action : A) {
            int op = action.get(0);
            int val = action.get(1);
            if (op == 1) {
                freqStack.push(val);
                result.add(-1); // convention: push returns -1 in the action log
            } else if (op == 2) {
                result.add(freqStack.pop()); // returns -1 if empty
            }
        }
        return result;
    }
}
