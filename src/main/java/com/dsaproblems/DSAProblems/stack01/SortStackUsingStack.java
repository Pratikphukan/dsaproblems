package com.dsaproblems.DSAProblems.stack01;

import java.util.*;

public class SortStackUsingStack {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 17, 100, 11));
        // 5, 17, 100, 11
        // 5, 4, 3, 2, 1
        // 6, 9, 11, 7, 10
        System.out.println(sortStackUsingAnotherStackv3(input));
        System.out.println(sortStackUsingAnotherStackv2(input));
        // System.out.println(sortStackUsingAnotherStack(input));
        System.out.println(sortStackUsingAnotherStack1(input));
        // System.out.println(sortStackUsingAnotherStackv1(input));
    }

    //working solution
    private static List<Integer> sortStackUsingAnotherStackv3(List<Integer> input) {
        int n = input.size();
        if (n <= 1) return input;
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> helper = new ArrayDeque<>();
        for (Integer integer : input) {
            s1.addFirst(integer);
        }
        while (!s1.isEmpty()) {
            int temp = s1.pollFirst();
            // keep popping from helper till its peek value is more than temp
            while (!helper.isEmpty() && helper.peekFirst() > temp) {
                s1.addFirst(helper.pollFirst());
            }
            helper.addFirst(temp);
        }
        while (!helper.isEmpty()) {
            s1.addFirst(helper.pollFirst());
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!s1.isEmpty()) {
            ans.add(s1.pollFirst());
        }
        return ans;
    }

    private static List<Integer> sortStackUsingAnotherStackv2(List<Integer> input) {
        int n = input.size();
        if (n <= 1) return input;
        Deque<Integer> stack = new ArrayDeque<>(input);
        Deque<Integer> helper = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int topElement = stack.pollFirst();
            while (!helper.isEmpty() && topElement < helper.peekFirst()) {
                stack.addFirst(helper.pollFirst());
            }
            helper.addFirst(topElement);
        }
        while (!helper.isEmpty()) {
            stack.addFirst(helper.pollFirst());
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.pollFirst());
        }
        return ans;
    }

    private static Deque<Integer> sortStackUsingAnotherStackv1(List<Integer> input) {
        Deque<Integer> stack = new LinkedList<>();
        while (!input.isEmpty()) {
            int topElement = input.remove(input.size() - 1);
            while (!stack.isEmpty() && stack.peekFirst() < topElement) {
                input.add(stack.pollFirst());
            }
            stack.addFirst(topElement);
        }
        return stack;
    }

    private static List<Integer> sortStackUsingAnotherStack(List<Integer> input) {
        List<Integer> sortedStack = new ArrayList<>();
        while (!input.isEmpty()) {
            int topElement = input.remove(input.size() - 1);
            while (!sortedStack.isEmpty() && sortedStack.get(sortedStack.size() - 1) < topElement) {
                input.add(sortedStack.remove(sortedStack.size() - 1));
            }
            sortedStack.add(topElement);
        }
        return sortedStack;
    }

    private static List<Integer> sortStackUsingAnotherStack1(List<Integer> input) {
        List<Integer> sortedStack = new ArrayList<>();
        while (!input.isEmpty()) {
            int topElement = input.remove(input.size() - 1);
            while (!sortedStack.isEmpty() && sortedStack.get(sortedStack.size() - 1) > topElement) {
                input.add(sortedStack.remove(sortedStack.size() - 1));
            }
            sortedStack.add(topElement);
        }
        return sortedStack;
    }

}
