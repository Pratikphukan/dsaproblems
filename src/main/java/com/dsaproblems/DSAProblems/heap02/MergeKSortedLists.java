package com.dsaproblems.DSAProblems.heap02;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode mergeKLists(ArrayList<ListNode> a) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>((x, y) -> x.val - y.val);

        for (ListNode x : a) {

            pq.add(x);

        }

        ListNode ans = new ListNode(-1);

        ListNode temp = ans;

        while (!pq.isEmpty()) {

            ListNode t = pq.poll();

            temp.next = t;

            if (t.next != null) {
                t = t.next;
                pq.add(t);
            }

            temp = temp.next;

        }

        return ans.next;
    }
}
