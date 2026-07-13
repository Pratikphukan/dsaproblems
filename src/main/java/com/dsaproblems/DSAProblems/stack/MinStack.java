package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MinStack {

    //not working version
    static class MinStackV1 {
        private ArrayList<Integer> stack;
        private int top;
        private ArrayList<Integer> minStack;
        private int min;

        public MinStackV1() {
            this.stack = new ArrayList<Integer>();
            this.top = -1;
            this.minStack = new ArrayList<Integer>();
            this.min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (x < min) {
                min = x;
                minStack.add(x);
            }
            top++;
            stack.add(x);
        }

        public void pop() {
            if (top == -1) {
                return;
            }
            if (minStack.isEmpty()) {
                min = -1;
            } else if (minStack.size() == 1) {
                min = minStack.get(0);
            } else if (minStack.get(minStack.size() - 1) == stack.get(top)) {
                min = minStack.get(minStack.size() - 2);
                minStack.remove(minStack.size() - 1);
            }
            stack.remove(top);
            top--;
        }

        public int top() {
            if (top == -1) {
                return -1;
            }
            int x = stack.get(top);
            return x;
        }

        public int getMin() {
            if (top == -1) {
                return -1;
            }
            return min;
        }
    }


    public static void main(String[] args) {
        MinStackV2 minStack = new MinStackV2();
        minStack.push(5);
        minStack.push(2);
        minStack.push(8);
        minStack.push(1);
        minStack.push(3);

        System.out.println("Top element: " + minStack.top());
        System.out.println("Minimum element: " + minStack.getMin());

        minStack.pop();
        System.out.println("Top element after pop: " + minStack.top());
        System.out.println("Minimum element after pop: " + minStack.getMin());
    }
}
