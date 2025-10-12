package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueServiceImpl implements QueueService {

    private Deque<Integer> stack1;
    private Deque<Integer> stack2;

    public QueueServiceImpl() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    @Override
    public void enqueue(Integer val) {
        stack1.addFirst(val);
    }

    @Override
    public Integer dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.addFirst(stack1.removeFirst()); // empty stack1
            }
        }
        return stack2.removeFirst();
    }
    /*
     * for the first dequeue we have n iterations empty stack1 and push all elements
     * into stack2 then do pop on stack2 after the first dequeue, any dequeue
     * operation after that will be of O(1) in total there will be n iterations
     */

    @Override
    public Integer peek() {
        return stack2.peekFirst();
    }

}
