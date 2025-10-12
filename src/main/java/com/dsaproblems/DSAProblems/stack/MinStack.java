package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MinStack {

//    static class MinStackV3 {
//        private Deque<Integer> stack = new LinkedList<>();
//        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//        private Integer min = Integer.MIN_VALUE;
//
//        public void push(int x) {
//            minHeap.add(x);
//            stack.addFirst(x);
//            min = minHeap.peek();
//        }
//
//        public void pop() {
//            Integer toBeRemoved = stack.peekFirst();
//            if (toBeRemoved != null) {
//                minHeap.remove(stack.peekFirst());
//                stack.removeFirst();
//                min = minHeap.peek();
//            }
//        }
//
//        public int top() {
//            if (stack.isEmpty()) {
//                return -1;
//            }
//            return stack.peekFirst();
//        }
//
//        public int getMin() {
//            if (stack.isEmpty()) {
//                return -1;
//            }
//            return min;
//        }
//    }

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

    //working version
    static class MinStackV2 {
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
