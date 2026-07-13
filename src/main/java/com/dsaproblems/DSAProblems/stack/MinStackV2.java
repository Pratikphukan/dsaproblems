package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStackV2 {
    private final Deque<Integer> stack;
    private final Deque<Integer> helper;

    public MinStackV2() {
        stack = new ArrayDeque<>();
        helper = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.addFirst(val);
        if (helper.isEmpty() || val <= helper.peekFirst()) {
            helper.addFirst(val);
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peekFirst();
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int poppedElement = stack.pollFirst();
            if (!helper.isEmpty() && helper.peekFirst().equals(poppedElement)) {
                helper.pollFirst();
            }
        }
    }

    public int getMin() {
        if (!helper.isEmpty()) {
            return helper.peekFirst();
        }
        return -1;
    }
}
