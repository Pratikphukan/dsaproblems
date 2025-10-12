package com.dsaproblems.DSAProblems.linkedlist;

public class LinkedListFunctions {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static ListNode head = null;

    public static void insert_node(int position, int value) {
        ListNode newNode = new ListNode(value);
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }
        ListNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) return; // Position out of bounds
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public static void delete_node(int position) {
        if (head == null) return;
        if (position == 1) {
            head = head.next;
            return;
        }
        ListNode temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) return; // Position out of bounds
        temp.next = temp.next.next;
    }

    public static Integer size(ListNode node) {
        ListNode temp = node;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void print_ll() {
        ListNode temp = head;
        if (temp == null) return;
        while (temp.next != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.print(temp.val);
    }


    public static void main(String[] args) {
        insert_node(1, 23);
        insert_node(2, 24);
        insert_node(3, 25);
        print_ll();
        delete_node(3);
        print_ll();
        insert_node(3, 27);
        print_ll();
    }
}
