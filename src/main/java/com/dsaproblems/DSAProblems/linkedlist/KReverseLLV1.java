package com.dsaproblems.DSAProblems.linkedlist;

public class KReverseLLV1 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        int B = 3;
        //System.out.println(kGroupReverseLLv1(head, B));
        //System.out.println(kGroupReverseLLv2(head, B));
        System.out.println(kGroupReverseLLv3(head, B));
    }

    private static ListNode kGroupReverseLLv3(ListNode head, int k) {
        int count = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        ListNode curHead = head;
        ListNode preTail = dummy;
        while (cur != null) {
            count++;
            ListNode next = cur.next;
            if (count == k) {
                cur.next = null;
                ListNode newHead = reverse(curHead);
                preTail.next = newHead;
                preTail = curHead;
                curHead = next;
                count = 0;
            }
            cur = next;
        }
        preTail.next = curHead;
        return dummy.next;
    }

    private static ListNode reverse(ListNode cur) {
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static ListNode kGroupReverseLLv2(ListNode head, int B) {
        if (B < 2 || head == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevTail = dummy;

        while (true) {
            // find k-th node from prevTail
            ListNode kth = prevTail;
            int i = 0;
            for (; i < B && kth != null; i++) kth = kth.next;
            if (kth == null) break; // fewer than B nodes remain -> done

            ListNode groupNext = kth.next; // node after current group

            // reverse current group: [prevTail.next .. kth]
            ListNode prev = groupNext;
            ListNode curr = prevTail.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // attach reversed group
            ListNode groupHead = prev;           // new head of reversed group (== kth)
            ListNode groupTail = prevTail.next; // old head becomes tail
            prevTail.next = groupHead;
            prevTail = groupTail;
        }

        return dummy.next;
    }

    //working code, whatever remains at last don't reverse them
    private static ListNode kGroupReverseLLv1(ListNode head, int B) {
        if (B < 2 || head == null || head.next == null) {
            return head;
        }
        int len = size(head);
        if (len < B) return head;
        int remain = len % B;
        if (remain == 0) return reversev1(head, B);
        ListNode temp = head;
        int k = len - remain;
        while (k-- > 0) {
            temp = temp.next;
        }
        ListNode newHead = reversev2(head, B, temp);
        ListNode tail = newHead;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        if (tail != null) tail.next = temp;
        return newHead;
    }

    private static ListNode reversev1(ListNode head, int B) {
        if (B < 2 || head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head, h2 = null;
        int i = 0;
        while (h1 != null && i < B) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
            i++;
        }
        if (h1 != null) {
            head.next = reversev1(h1, B);
        }
        return h2;
    }

    private static ListNode reversev2(ListNode head, int B, ListNode terminal) {
        if (B < 2 || head == null || head.next == null) {
            return head;
        }
        ListNode h1 = head, h2 = null;
        int i = 0;
        while (h1 != null && i < B) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
            i++;
        }
        if (h1 != terminal) {
            head.next = reversev2(h1, B, terminal);
        }
        return h2;
    }

    public static int size(ListNode node) {
        ListNode temp = node;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
