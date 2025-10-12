package com.dsaproblems.DSAProblems.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NthNumberUsing123 {

    public static void main(String[] args) {
        System.out.println(findNthNumberUsing123v1(13));
        System.out.println(findNthNumberUsing123v2(13));
        System.out.println(findNthNumberUsing123v3(13));
        System.out.println(findNthNumberUsing123v4(13));
    }

    private static List<Integer> findNthNumberUsing123v4(int A) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        while (ans.size() < A) {
            int multiplier = queue.removeFirst();
            ans.add(multiplier);
            queue.addLast(multiplier * 10 + 1);
            queue.addLast(multiplier * 10 + 2);
            queue.addLast(multiplier * 10 + 3);
        }
        return ans;
    }

    // working solution
    private static List<Integer> findNthNumberUsing123v3(int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(1);
        queue.offerLast(2);
        queue.offerLast(3);
        int count = queue.size();
        Integer multiplier = null;
        List<Integer> ans = new ArrayList<>();
        while (count < n) {
            multiplier = queue.pollFirst();
            ans.add(multiplier);
            queue.offerLast(multiplier * 10 + 1);
            queue.offerLast(multiplier * 10 + 2);
            queue.offerLast(multiplier * 10 + 3);
            count += 3;
        }
        for (int i = ans.size(); i < n; i++) {
            ans.add(queue.pollFirst());
        }
        return ans;
    }

    // working solution
    private static List<Integer> findNthNumberUsing123v2(int A) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        int a = 1, b = 2, c = 3;
        queue.addLast(a);
        queue.addLast(b);
        queue.addLast(c);
        int count = queue.size();
        for (int i = 0; i < A; i++) {
            int multiplier = queue.removeFirst();
            ans.add(multiplier);
            if (count >= A)
                continue;
            queue.addLast(multiplier * 10 + a);
            queue.addLast(multiplier * 10 + b);
            queue.addLast(multiplier * 10 + c);
            count += 3;
        }
        return ans;
    }

    private static int findNthNumberUsing123v1(int n) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(1);
        queue.offerLast(2);
        queue.offerLast(3);
        int count = queue.size();
        Integer multiplier = null;
        int removals = 0;
        while (count < n) {
            multiplier = queue.pollFirst();
            removals += 1;
            queue.offerLast(multiplier * 10 + 1);
            queue.offerLast(multiplier * 10 + 2);
            queue.offerLast(multiplier * 10 + 3);
            count += 3;
        }
        int ans = 1;
        for (int i = removals; i < n; i++) {
            ans = queue.pollFirst();
        }
        return ans;
    }

}
