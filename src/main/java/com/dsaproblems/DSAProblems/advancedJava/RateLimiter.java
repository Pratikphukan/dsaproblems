package com.dsaproblems.DSAProblems.advancedJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class RateLimiter {

    public static void main(String[] args) {
        ArrayList<Integer> A1 = new ArrayList<>();
        ArrayList<Integer> B1 = new ArrayList<>();
        // Client 1 makes 3 requests that should be successful and a 4th which should fail initially.
        // Input: A = [1, 1, 2, 1, 1, 1], B = [1, 2, 2, 9, 10, 11]
        A1.add(1);
        A1.add(1);
        A1.add(2);
        A1.add(1);
        A1.add(1);
        A1.add(1);
        B1.add(1);
        B1.add(2);
        B1.add(2);
        B1.add(9);
        B1.add(10);
        B1.add(11);
        // Expected result: [1, 1, 1, 1, 0, 1]
        System.out.println(rateLimiterv1(A1, B1));
    }

    private static ArrayList<Integer> rateLimiterv1(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Deque<Integer>> clientRequests = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            int clientId = A.get(i);
            int timestamp = B.get(i);
            clientRequests.putIfAbsent(clientId, new ArrayDeque<>());
            Deque<Integer> dq = clientRequests.get(clientId);
            while (!dq.isEmpty() && dq.peekFirst() < timestamp - 9) {
                dq.pollFirst();
            }
            if (dq.size() < 3) {
                dq.offerLast(timestamp);
                result.add(1);
            } else {
                result.add(0);
            }
        }
        return result;
    }
}
