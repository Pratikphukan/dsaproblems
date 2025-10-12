package com.dsaproblems.DSAProblems.heap02;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {

    public static void main(String[] args) {

    }

    public static int solve(int A, ArrayList<ArrayList<Integer>> B) {
        B.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
        Queue<Integer> ends = new PriorityQueue<>();
        for (ArrayList<Integer> point : B) {
            int start = point.get(0);
            int end = point.get(1);
            if (!ends.isEmpty() && ends.peek() <= start) {
                ends.poll();
            }
            ends.offer(end);
        }
        return ends.size();
    }
}
