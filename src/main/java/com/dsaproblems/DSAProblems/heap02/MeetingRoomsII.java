package com.dsaproblems.DSAProblems.heap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MeetingRoomsII {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
//        A.add(new ArrayList<>(Arrays.asList(0, 30)));
//        A.add(new ArrayList<>(Arrays.asList(5, 10)));
//        A.add(new ArrayList<>(Arrays.asList(15, 20)));


        A.add(new ArrayList<>(Arrays.asList(10, 14)));
        A.add(new ArrayList<>(Arrays.asList(14, 17)));
        A.add(new ArrayList<>(Arrays.asList(6, 10)));
        A.add(new ArrayList<>(Arrays.asList(8, 12)));
        A.add(new ArrayList<>(Arrays.asList(11, 15)));
        //System.out.println(solvev1(A.size(), A));
        System.out.println(solvev2(A.size(), A));
    }

    //time: O(n + endTime)
    //space: O(endTime)
    private static int solvev2(int A, ArrayList<ArrayList<Integer>> B) {
        int endTime = 0;
        //Get the maximum end time across all meetings
        for (ArrayList<Integer> b : B) {
            endTime = Math.max(endTime, b.get(1));
        }
        int[] overlap = new int[endTime + 1];
        //Increment at start, decrement at end (event-based tracking)
        for (ArrayList<Integer> b : B) {
            int start = b.get(0);
            int end = b.get(1);
            overlap[start]++;
            overlap[end]--;
        }
        int maxOverlap = 0;
        int currentOverlap = 0;
        for (int i = 0; i <= endTime; i++) {
            currentOverlap += overlap[i];
            maxOverlap = Math.max(maxOverlap, currentOverlap);
        }
        return maxOverlap;
    }

    //Time: O(n log n) — sort is O(n log n), heap ops (peek/poll/offer) are O(log n) each × n meetings
    //Space: O(n) — heap holds up to n end times
    public static int solvev1(int A, ArrayList<ArrayList<Integer>> B) {
        //Sort by start time
        B.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
        Queue<Integer> ends = new PriorityQueue<>(); //Heap tracks when rooms become free (end times)
        for (ArrayList<Integer> point : B) {
            int start = point.get(0);
            int end = point.get(1);
            if (!ends.isEmpty() && ends.peek() <= start) {
                ends.poll(); //If earliest freed room's end time ≤ current meeting start → reuse that room (poll it)
            }
            ends.offer(end);
        }
        return ends.size();
    }
}
