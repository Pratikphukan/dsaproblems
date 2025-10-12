package com.dsaproblems.DSAProblems.linkedlist;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class AddTwoNumbersAsLists {

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
//        ListNode A = new ListNode(9);
//        A.next = new ListNode(9);
//        A.next.next = new ListNode(1);
//
//        ListNode B = new ListNode(1);

        ListNode A = new ListNode(1);

        ListNode B = new ListNode(9);
        B.next = new ListNode(9);
        B.next.next = new ListNode(9);

        System.out.println(addListsv1(A, B));
        System.out.println(addListsv2(A, B));
    }

    //this runs in O(n) time and uses O(1) extra space
    private static ListNode addListsv2(ListNode A, ListNode B) {
        ListNode t1 = A, t2 = B;
        ListNode head = null, temp = null;
        int carry = 0;
        while (t1 != null || t2 != null) {
            int sum = (t1 != null ? t1.val : 0) + (t2 != null ? t2.val : 0) + carry;
            if (head == null) {
                head = new ListNode(sum % 10);
                temp = head;
            } else {
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
            }
            carry = sum / 10;
            t1 = t1 != null ? t1.next : null;
            t2 = t2 != null ? t2.next : null;
        }
        temp.next = carry > 0 ? new ListNode(carry) : null;
        return head;
    }

    //addListsv1 works for small numbers but is incorrect for large lists because it converts the entire linked list to an integer, which can cause integer overflow.
    private static ListNode addListsv1(ListNode A, ListNode B) {
        ListNode t1 = A;
        ListNode t2 = B;
        int num1 = 0, num2 = 0;
        int i = 0;
        while (t1 != null) {
            num1 += t1.val * (int) Math.pow(10, i);
            t1 = t1.next;
            i++;
        }
        i = 0;
        while (t2 != null) {
            num2 += t2.val * (int) Math.pow(10, i);
            t2 = t2.next;
            i++;
        }
        int ans = num1 + num2;
        ListNode head = null, temp = null;
        while (ans != 0) {
            if (head == null) {
                head = new ListNode(ans % 10);
                temp = head;
            } else {
                temp.next = new ListNode(ans % 10);
                temp = temp.next;
            }
            ans = ans / 10;
        }
        return head;
    }
}
