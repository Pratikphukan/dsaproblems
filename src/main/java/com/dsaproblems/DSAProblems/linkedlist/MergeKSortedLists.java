package com.dsaproblems.DSAProblems.linkedlist;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(4);
        h1.next.next = new ListNode(5);

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(3);
        h2.next.next = new ListNode(4);

        ListNode h3 = new ListNode(2);
        h3.next = new ListNode(6);

        ListNode[] A = new ListNode[]{h1, h2, h3};
        //System.out.println(mergeKSortedListsv1(A));
        //System.out.println(mergeKSortedListsv2(A));
        System.out.println(mergeKSortedListsv3(A));
    }

    //working code
    //use a min-heap (`PriorityQueue`) to merge k lists in O(N log k) time and O(k) extra space
    //Each node is pushed/popped from heap once at O(log k)
    //Space: O(k) — heap holds at most one node per list
    //The heap only holds one node per list at a time
    //When you poll() a node, you offer() its next node (from the same list)
    //So heap size never exceeds k
    //Therefore each push/pop is O(log k), not O(log N)
    private static ListNode mergeKSortedListsv3(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!pq.isEmpty()) {
            ListNode curr = pq.poll();
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) pq.offer(curr.next);
        }
        return dummy.next;
    }

    //working code
    private static ListNode mergeKSortedListsv2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeRange(lists, 0, lists.length - 1);
    }

    private static ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left]; //single sorted list, return it directly
        int mid = left + (right - left) / 2;
        ListNode lNode = mergeRange(lists, left, mid);
        ListNode rNode = mergeRange(lists, mid + 1, right);
        return mergeSortedLinkedLists(lNode, rNode); // existing helper in the class
    }

    private static ListNode mergeKSortedListsv1(ListNode[] lists) {
        ListNode root = lists[0];
        for (int i = 1; i < lists.length; i++) {
            root = mergeSortedLinkedLists(root, lists[i]);
        }
        return root;
    }

    //O(n+m) time, O(1) space (in-place pointer manipulation, no extra list)
    public static ListNode mergeSortedLinkedLists(ListNode h1, ListNode h2) { // h1 and h2 are sorted in ascending order
        // and head is sorted in ascending order
        if (h1 == null && h2 == null) return null;
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        ListNode head;
        if (h1.val > h2.val) {
            head = h2;
            h2 = h2.next;
        } else {
            head = h1;
            h1 = h1.next;
        }
        ListNode temp = head;
        while (h1 != null && h2 != null) {
            if (h1.val > h2.val) {
                temp.next = h2;
                h2 = h2.next;
            } else {
                temp.next = h1;
                h1 = h1.next;
            }
            temp = temp.next;
        }
        if (h1 == null) temp.next = h2;
        if (h2 == null) temp.next = h1;
        return head;
    }
}
