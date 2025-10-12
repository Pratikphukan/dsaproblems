package com.dsaproblems.DSAProblems.advancedJava.revision;

public class ReverseLinkedList {

    static class ListNode {

        private Integer val;

        private ListNode next;

        public ListNode(Integer val) {
            super();
            this.val = val;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.setNext(new ListNode(5));
        head.getNext().setNext(new ListNode(8));
        head.getNext().getNext().setNext(new ListNode(9));
        printList(head);
        printList(reverseLinkedList(head));
    }

    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.getVal() + "->");
            node = node.getNext();
        }
        System.out.println();
    }

    public static ListNode reverseLinkedList(ListNode node) {
        if (node == null || node.getNext() == null) {
            return node;
        }
        ListNode h1 = node;
        ListNode h2 = null;
        while (h1 != null) {
            ListNode temp = h1;
            h1 = h1.next;
            temp.next = h2;
            h2 = temp;
        }
        return h2;
    }

}
