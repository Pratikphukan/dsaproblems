package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

public class BTPreorderTraversal {

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

        System.out.println(iterativePreorderTraversalv1(head));
        System.out.println(iterativePreorderTraversalv2(head));
    }

    //Time: O(n) — each node visited once.
    //Space: O(h) auxiliary (stack depth = tree height); worst-case O(n) for a skewed tree.
    public static List<Integer> iterativePreorderTraversalv1(TreeNode node) {
        List<Integer> preorder = new ArrayList<>();
        if (node == null) return preorder;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            preorder.add(temp.val);
            if (temp.right != null) stack.addFirst(temp.right);
            if (temp.left != null) stack.addFirst(temp.left);
        }
        return preorder;
    }

    // it is depth first search
    // any algorithm that works in a fail fast manner
    // moving in top to bottom manner
    //v2 is slightly better micro‑optimizations (uses ArrayDeque and direct field access)
    public static ArrayList<Integer> iterativePreorderTraversalv2(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.removeFirst();
            if (temp.right != null) stack.addFirst(temp.right);
            if (temp.left != null) stack.addFirst(temp.left);
            ans.add(temp.val);
        }
        return ans;
    }
}
