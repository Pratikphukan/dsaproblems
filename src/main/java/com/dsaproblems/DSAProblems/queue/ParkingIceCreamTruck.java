package com.dsaproblems.DSAProblems.queue;

import java.util.*;
import java.util.PriorityQueue;

public class ParkingIceCreamTruck {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(32, 17, 9, 15, 28, 41, 10));
        int B = 3;
        System.out.println(slidingMaximumv1(A, B));
        System.out.println(slidingMaximumv2(A, B));
    }

    //working code
    //no of elements in the result array will be n-B+1
    private static ArrayList<Integer> slidingMaximumv2(List<Integer> A, int B) {
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(A.size() - B + 1, 0));
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < A.size(); i++) {
            //remove indices from deque that are out of this window's bounds
            //the window's left boundary is i-B+1
            while (!deque.isEmpty() && deque.peekFirst() < i - B + 1) {
                deque.pollFirst(); //remove from the front
            }
            //remove from back all elements which are less than the current element value
            //as they cannot be max if the current element is larger
            while (!deque.isEmpty() && A.get(deque.peekLast()) < A.get(i)) {
                deque.pollLast();
            }
            //add the current element's index to the deque
            deque.offerLast(i);
            //if the window has hit the size B, record the max value from the front of the deque
            if (!deque.isEmpty() && i >= B - 1) {
                result.set(i - B + 1, A.get(deque.peekFirst()));
            }
        }
        return result;
    }

    //working code, less optimal
    public static ArrayList<Integer> slidingMaximumv1(final List<Integer> A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < B; i++) {
            maxHeap.add(A.get(i));
        }
        ans.add(maxHeap.peek());
        for (int i = 0; i < A.size() - B; i++) {
            maxHeap.remove(A.get(i));
            maxHeap.add(A.get(i + B));
            ans.add(maxHeap.peek());
        }
        return ans;
    }
}
