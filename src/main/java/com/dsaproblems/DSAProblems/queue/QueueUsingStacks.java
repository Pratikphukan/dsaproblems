package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class QueueUsingStacks {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(9);
//		stack.addFirst(5);
//		stack.addFirst(4);
//		stack.addFirst(7);
//		stack.addFirst(9);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
        System.out.println(stack.poll());
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
        System.out.println(stack);

        Deque<Integer> stack2 = new ArrayDeque<>();
        stack2.push(stack.poll());
        stack2.push(stack.poll());
        System.out.println(stack2);

        Stack<Integer> a = new Stack<>();
        a.push(5);
        a.push(4);
        a.push(7);
        a.push(9);
        System.out.println(a);
        System.out.println(a.peek());
        System.out.println(a.pop());
        System.out.println(a);

        QueueService qs = new QueueServiceImpl();
        qs.enqueue(2);
        qs.enqueue(3);
        qs.enqueue(8);
        qs.enqueue(9);

        System.out.println(qs.dequeue());
        System.out.println(qs.dequeue());


        qs.enqueue(11);
        qs.enqueue(76);
    }

}
