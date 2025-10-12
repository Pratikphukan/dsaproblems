package com.dsaproblems.DSAProblems.binarytree01;

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
        System.out.println(connect(head));
    }

    private static TreeLinkNode connect(TreeLinkNode head) {
        if (head == null) return head;
        Deque<TreeLinkNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode temp = queue.pollFirst();
                temp.next = (i + 1) < size ? queue.peekFirst() : null;
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return head;
    }
}
