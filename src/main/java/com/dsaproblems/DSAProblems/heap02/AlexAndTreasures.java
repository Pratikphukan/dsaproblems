package com.dsaproblems.DSAProblems.heap02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AlexAndTreasures {

    public ArrayList<ArrayList<Integer>> solve(final List<ArrayList<Integer>> A, final int B) {

        // Min-heap by distance from origin
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((p1, p2) -> {
            long d1 = 1L * p1.get(0) * p1.get(0) + 1L * p1.get(1) * p1.get(1);
            long d2 = 1L * p2.get(0) * p2.get(0) + 1L * p2.get(1) * p2.get(1);
            return Long.compare(d1, d2);
        });

        for (ArrayList<Integer> point : A) {
            pq.offer(point);
            if (pq.size() > B) {
                pq.poll(); // remove the closest point
            }
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }

        // Sort result as required
        Collections.sort(ans, (p1, p2) -> {
            if (!p1.get(0).equals(p2.get(0))) {
                return Integer.compare(p1.get(0), p2.get(0));
            }
            return Integer.compare(p1.get(1), p2.get(1));
        });

        return ans;
    }
}
