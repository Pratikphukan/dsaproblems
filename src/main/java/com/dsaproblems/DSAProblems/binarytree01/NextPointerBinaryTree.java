package com.dsaproblems.DSAProblems.binarytree01;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class NextPointerBinaryTree {

    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode head = new TreeLinkNode(1);
        head.left = new TreeLinkNode(2);
        head.right = new TreeLinkNode(5);
        head.left.left = new TreeLinkNode(3);
        head.left.right = new TreeLinkNode(4);
        head.right.left = new TreeLinkNode(6);
        head.right.right = new TreeLinkNode(7);
        System.out.println(connectv1(head));
        System.out.println(connectv2(head));
    }

    //working code
    //works for a perfect binary tree where all leaves are on the same level, and every parent has two children
    private static TreeLinkNode connectv2(TreeLinkNode head) {
        if (head == null) return null;
        TreeLinkNode level = head;
        while (level.left != null) {
            TreeLinkNode curr = level;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            level = level.left;
        }
        return head;
    }

    //working code
    private static TreeLinkNode connectv1(TreeLinkNode head) {
        if (head == null) return head;
        Deque<TreeLinkNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode temp = queue.pollFirst();
                temp.next = (i + 1) < size ? queue.peekFirst() : null;
                if (temp.left != null) queue.addLast(temp.left);
                if (temp.right != null) queue.addLast(temp.right);
            }
        }
        return head;
    }
}
