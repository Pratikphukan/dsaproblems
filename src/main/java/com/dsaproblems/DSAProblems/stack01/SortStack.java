package com.dsaproblems.DSAProblems.stack01;

import java.util.Deque;
import java.util.LinkedList;

public class SortStack {

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(10);
        stack.addFirst(7);
        stack.addFirst(11);
        stack.addFirst(9);
        stack.addFirst(6);
        System.out.println(sortStack(stack));
    }

    private static Deque<Integer> sortStack(Deque<Integer> stack1) {
        if (stack1.size() == 1) {
            return stack1;
        }
        Deque<Integer> stack2 = new LinkedList<>();
        int len = stack1.size();
        for (int i = 0; i < len / 2; i++) {
            stack2.addFirst(stack1.pollFirst());
        }
        stack1 = sortStack(stack1);
        stack2 = sortStack(stack2);
        return mergeSortedStacks(stack1, stack2);
    }

    private static Deque<Integer> mergeSortedStacks(Deque<Integer> stack1, Deque<Integer> stack2) {
        Deque<Integer> stack = new LinkedList<>();
        while (!(stack1.isEmpty() || stack2.isEmpty())) {
            if (stack1.peekFirst() > stack2.peekFirst()) {
                stack.addFirst(stack1.pollFirst());
            } else {
                stack.addFirst(stack2.pollFirst());
            }
        }
        while (!stack1.isEmpty()) {
            stack.addFirst(stack1.pollFirst());
        }
        while (!stack2.isEmpty()) {
            stack.addFirst(stack2.pollFirst());
        }
        return reverseStack(stack);
    }

    private static Deque<Integer> reverseStack(Deque<Integer> stack) {
        Deque<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty()) {
            ans.push(stack.pollFirst());
        }
        return ans;
    }

}
