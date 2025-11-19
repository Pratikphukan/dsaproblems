package com.dsaproblems.DSAProblems.leetcode;

import com.dsaproblems.DSAProblems.binarytree02.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class FlattenBTToLinkedList {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(10);
        head.setLeft(new TreeNode(5));
        head.getLeft().setLeft(new TreeNode(2));
        head.getLeft().setRight(new TreeNode(7));
        head.getLeft().getRight().setLeft(new TreeNode(6));
        head.getLeft().getRight().setRight(new TreeNode(8));
        head.setRight(new TreeNode(20));
        head.getRight().setRight(new TreeNode(22));
        head.getRight().getRight().setLeft(new TreeNode(21));
        head.getRight().getRight().setRight(new TreeNode(50));

        //System.out.println(flattenBTToLLv1(head));
        System.out.println(flattenBTToLLv2(head));
    }

    private static TreeNode flattenBTToLLv2(TreeNode head) {
        if (head == null) return null;
        TreeNode curr = head;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode pred = curr.left;
                while (pred.right != null) pred = pred.right;
                pred.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
        return head;
    }

    private static TreeNode flattenBTToLLv1(TreeNode head) {
        TreeNode dummy = new TreeNode(0);
        TreeNode curr = dummy;
        if (head == null) return null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(head);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            curr.right = temp;
            curr = curr.right;
            if (temp.right != null) stack.addFirst(temp.right);
            if (temp.left != null) stack.addFirst(temp.left);
            curr.left = null;
        }
        return dummy.right;
    }
}
