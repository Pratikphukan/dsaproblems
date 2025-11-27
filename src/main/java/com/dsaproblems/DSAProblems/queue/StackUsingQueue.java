package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingQueue {

    private Deque<Integer> queue1;
    private Deque<Integer> queue2;

    public StackUsingQueue() {
        this.queue1 = new ArrayDeque<>();
        this.queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
        queue2.addLast(x);
        while (!queue1.isEmpty()) {
            queue2.addLast(queue1.pollFirst());
        }
        Deque<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public Integer pop() {
        return queue1.pollFirst();
    }

    public Integer top() {
        return queue1.peekFirst();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
