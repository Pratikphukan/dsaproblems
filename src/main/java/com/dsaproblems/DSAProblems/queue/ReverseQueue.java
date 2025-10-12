package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ReverseQueue {

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4, 5);
        ArrayList<Integer> A = new ArrayList<>(a);
        int B = 3;
        // System.out.println(reverseQueueTillBv1(A, B));
        System.out.println(reverseQueueTillBv2(A, B));
        // System.out.println(reverseQueue(A));
        System.out.println(reverseQueueTillB(A, B));
    }

    // working solution
    private static ArrayList<Integer> reverseQueueTillBv2(ArrayList<Integer> A, int B) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        // Insert first B elements in stack
        for (i = 0; i < B; i++) {
            stack.offerFirst(A.get(i));
        }
        // Reverse the first B elements in the array A
        i = 0;
        while (!stack.isEmpty()) {
            A.set(i, stack.pollFirst());
            i++;
        }
        return A;
    }

    private static ArrayList<Integer> reverseQueueTillBv2faster(ArrayList<Integer> A, int B) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        // Insert first B elements in stack
        for (i = 0; i < B; i++) {
            stack.offerFirst(A.get(i));
        }
        // Reverse the first B elements in the array A
        i = 0;
        while (!stack.isEmpty()) {
            A.set(i, stack.peekFirst());
            stack.pollFirst();
            i++;
        }
        return A;
    }

    // working solution
    private static ArrayList<Integer> reverseQueueTillBv1(ArrayList<Integer> A, int B) {
        Deque<Integer> queue = new ArrayDeque<>();
        int i = 0;
        // Insert first B elements in queue
        for (i = 0; i < B; i++) {
            queue.offerLast(A.get(i));
        }
        // Reverse the first B elements in the array A
        while (!queue.isEmpty()) {
            i--;
            A.set(i, queue.pollFirst());
        }
        return A;
    }

    private static ArrayList<Integer> reverseQueueTillBv1faster(ArrayList<Integer> A, int B) {
        Deque<Integer> queue = new ArrayDeque<>();
        int i = 0;
        // Insert first B elements in queue
        for (i = 0; i < B; i++) {
            queue.offerLast(A.get(i));
        }
        // Reverse the first B elements in the array A
        while (!queue.isEmpty()) {
            i--;
            A.set(i, queue.peekFirst());
            queue.pollFirst();
        }
        return A;
    }

    private static Queue<Integer> reverseQueueTillB(ArrayList<Integer> A, int B) {
        Deque<Integer> queue = new LinkedList<>();
        for (Integer num : A) {
            queue.offerLast(num);
        }
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        while (!queue.isEmpty() && i < B) {
            stack.offerFirst(queue.pollFirst());
            i++;
        }
        Deque<Integer> ans = new LinkedList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.pollFirst());
        }
        while (!queue.isEmpty()) {
            ans.add(queue.pollFirst());
        }
        return ans;
    }

    private static Queue<Integer> reverseQueue(ArrayList<Integer> A) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < A.size(); i++) {
            q.add(A.get(i));
        }
        System.out.println(q);
        Stack<Integer> stack = new Stack<>();
        while (!q.isEmpty()) {
            int item = q.remove();
            stack.push(item);
        }
        System.out.println(stack);
        while (!stack.isEmpty()) {
            int item = stack.pop();
            q.add(item);
        }
        return q;
    }

}
