package com.dsaproblems.DSAProblems.binarytree01;

import java.util.*;

public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(invertBinaryTreev1(root));
        System.out.println(invertBinaryTreev2(root));
    }

    private static TreeNode invertBinaryTreev2(TreeNode A) {
        if (A == null) {
            return null;
        }
        //Recursively invert the left and right subtrees
        TreeNode left = invertBinaryTreev2(A.left);
        TreeNode right = invertBinaryTreev2(A.right);
        //swap the left and right children
        A.left = right;
        A.right = left;
        return A;
    }

    private static TreeNode invertBinaryTreev1(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (node.right != null || node.left != null) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if (node.right != null) stack.addFirst(node.right);
            if (node.left != null) stack.addFirst(node.left);
        }
        return root;
    }
}
