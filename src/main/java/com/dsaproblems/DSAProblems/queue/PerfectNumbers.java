package com.dsaproblems.DSAProblems.queue;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class PerfectNumbers {

    public static void main(String[] args) {
        int A = 23;

        // to have a symmetric idea
        Deque<Integer> queue1 = new ArrayDeque<>();
        queue1.offerLast(5);
        queue1.offerLast(4);
        queue1.offerLast(7);
        queue1.offerLast(9);
        System.out.println(queue1);
        System.out.println(queue1.peek());
        System.out.println(queue1.peekFirst());
        System.out.println(queue1.pollFirst());
        System.out.println(queue1);

        Deque<Integer> stack1 = new ArrayDeque<>();
        stack1.offerFirst(5);
        stack1.offerFirst(4);
        stack1.offerFirst(7);
        stack1.offerFirst(9);
        System.out.println(stack1);
        System.out.println(stack1.peek());
        System.out.println(stack1.peekFirst());
        System.out.println(stack1.pollFirst());

        // concepts of queue
        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(4);
        queue.add(7);
        queue.add(9);
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);

        // concepts of stack
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(5);
        stack.push(4);
        stack.push(7);
        stack.push(9);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
        System.out.println(stack.poll());

        System.out.println(findAthPerfectNumber(A));
        System.out.println(findAthPerfectNumberv1(5));

        System.out.println(findAthPerfectNumberv2(5));
    }

    //working solution
    private static String findAthPerfectNumberv2(int A) {
        Deque<String> queue = new LinkedList<>();
        queue.offerLast("1");
        queue.offerLast("2");
        String ans = "";
        for (int i = 0; i < A; i++) {
            ans = queue.pollFirst();
            queue.offerLast(ans + "1");
            queue.offerLast(ans + "2");
        }
        return ans + new StringBuilder(ans).reverse().toString();
    }

    private static String findAthPerfectNumberv1(int A) {
        if (A == 1)
            return "11";
        if (A == 2)
            return "22";
        Deque<String> queue = new LinkedList<>();
        queue.offerLast("1");
        queue.offerLast("2");
        StringBuilder sb = null;
        String ans = null;
        int size = queue.size();
        while (!queue.isEmpty() && queue.size() < A) {
            sb = new StringBuilder(queue.pollFirst());
            queue.offerLast(sb.append("1").toString());
            size += 1;
            if (size == A) {
                ans = sb.toString();
                break;
            }
            sb.deleteCharAt(sb.length() - 1);
            queue.offerLast(sb.append("2").toString());
            size += 1;
            if (size == A) {
                ans = sb.toString();
                break;
            }
        }
        return ans + new StringBuilder(ans).reverse().toString();
    }

    private static String reverseString(String multiplier) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multiplier.length(); i++) {
            sb.insert(0, multiplier.charAt(i));
        }
        return sb.toString();
    }

    private static String findAthPerfectNumber(int A) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        StringBuilder sb = new StringBuilder();
        int n = 23;
        Double a = Math.ceil(Math.log(n + 2) / Math.log(2)) - 1;
        int possibilities = a.intValue();
        for (int i = 0; i < possibilities; i++) {

        }
        System.out.println(a);

        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);
        return "erty";
    }

}
