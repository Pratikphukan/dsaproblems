package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTNodesInRange {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(14);
        root.left.left.left = new TreeNode(8);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(27);

        int B = 12;
        int C = 20;

        System.out.println(getCountOfNodesInRangev1(root, B, C));
        System.out.println(getCountOfNodesInRangev2(root, B, C));

    }

    //working code, using inorder traversal
    public static int getCountOfNodesInRangev2(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode temp = root;
        int count = 0;
        while (temp != null) {
            stack.addFirst(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            temp = stack.pollFirst();
            if (temp.val >= low && temp.val <= high) {
                count++;
            }
            if (temp.right != null) {
                TreeNode node = temp.right;
                while (node != null) {
                    stack.addFirst(node);
                    node = node.left;
                }
            }
        }
        return count;
    }

    //working code
    private static int getCountOfNodesInRangev1(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        if (node.val < low) {//discard the right subtree
            return getCountOfNodesInRangev1(node.right, low, high);
        }
        if (node.val > high) {//discard the left subtree
            return getCountOfNodesInRangev1(node.left, low, high);
        }
        return 1 + getCountOfNodesInRangev1(node.left, low, high) + getCountOfNodesInRangev1(node.right, low, high);
    }
}
