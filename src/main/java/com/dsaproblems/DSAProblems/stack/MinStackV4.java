package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/*
PriorityQueue.remove(Object) is O(n), so this approach is less efficient than the typical two-stack approach, but it is correct.
push(3) → stack: [3],  heap: [3]
push(1) → stack: [1,3], heap: [1,3]
push(2) → stack: [2,1,3], heap: [1,2,3]
pop()   → removes 2 from stack, but 2 ≠ heap.peek() (1), so heap stays [1,2,3]
pop()   → removes 1 from stack, 1 == heap.peek(), so heap becomes [2,3]
getMin()→ returns 2 ✅ (correct by luck here)

push(5) → stack: [5,3], heap: [2,3,5]  ← 2 is a GHOST; it was already popped!
getMin()→ returns 2 ❌ (should return 3)
 */
public class MinStackV4 {

    private final Deque<Integer> stack;
    private final PriorityQueue<Integer> minHeap;

    public MinStackV4() {
        this.stack = new ArrayDeque<>();
        this.minHeap = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.addFirst(x);
        minHeap.add(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int poppedElement = stack.pollFirst();
            minHeap.remove(poppedElement);
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peekFirst();
        }
        return -1;
    }

    public int getMin() {
        if (stack.isEmpty() && minHeap.isEmpty()) {
            return -1;
        }
        return minHeap.peek();
    }
}
