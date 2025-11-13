package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BTInorderTraversal {

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
        System.out.println(iterativeInorderTraversalv1(head));
        System.out.println(iterativeInorderTraversalv2(head));
    }

    public static ArrayList<Integer> iterativeInorderTraversalv1(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (A != null) {
            stack.addFirst(A);
            A = A.left;
        }
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            ans.add(temp.val);
            if (temp.right != null) {
                TreeNode node = temp.right;
                while (node != null) {
                    stack.addFirst(node);
                    node = node.left;
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> iterativeInorderTraversalv2(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) {
            return ans;
        }
        TreeNode temp = A;// not necessary if we are returning an arraylist
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.addFirst(temp);
                temp = temp.left;
            }
            temp = stack.pollFirst();
            ans.add(temp.val);
            temp = temp.right;
        }
        return ans;
    }
}

