package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BoundaryTraversalOfBT {

    public static void main(String[] args) {
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.left.left = new TreeNode(4); // leaf from left boundary duplicate check
        root4.left.left.right = new TreeNode(7);
        root4.left.left.right.left = new TreeNode(8);
        root4.left.left.right.right = new TreeNode(9);
        root4.right.left = new TreeNode(6);
        root4.right.left.right = new TreeNode(10);
        System.out.println(getBoundaryTraversalOfBTv1(root4));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.left = new TreeNode(5);
        root2.right.right = new TreeNode(6);
        root2.right.right.right = new TreeNode(7);
        // Expected boundary: [1, 2, 4, 5, 7, 6, 3]
        System.out.println(getBoundaryTraversalOfBTv1(root2));
    }

    //working code
    private static ArrayList<Integer> getBoundaryTraversalOfBTv1(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null) return result;
        if (isLeaf(A)) {
            result.add(A.val);
            return result;
        }
        result.add(A.val);
        if (A.left != null) {
            addLeftBoundary(A.left, result);
        }
        addLeaves(A, result);
        if (A.right != null) {
            addRightBoundary(A.right, result);
        }
        return result;
    }

    private static void addRightBoundary(TreeNode A, ArrayList<Integer> result) {
        Deque<Integer> stack = new ArrayDeque<>();
        while (A != null) {
            // Only push the node's value if it's not a leaf.
            if (!isLeaf(A)) {
                stack.addFirst(A.val);
            }
            if (A.right != null) A = A.right;// Prefer right child if available, otherwise use left child
            else A = A.left;
        }
        // Add the nodes from the stack into the result list to reverse the order.
        while (!stack.isEmpty()) {
            result.add(stack.pollFirst());
        }
    }

    private static void addLeaves(TreeNode A, ArrayList<Integer> result) {
        if (A == null) return;
        // If current node is a leaf, add its value.
        if (isLeaf(A)) result.add(A.val);
        else {
            addLeaves(A.left, result);// Otherwise, recursively process the left subtree.
            addLeaves(A.right, result);// And then process the right subtree.
        }
    }

    private static void addLeftBoundary(TreeNode A, ArrayList<Integer> result) {
        while (A != null) {
            // Only add the node if it is not a leaf to avoid duplicate leaves.
            if (!isLeaf(A)) {
                result.add(A.val);
            }
            // Prefer left child if available, otherwise use right child.
            if (A.left != null) A = A.left;
            else A = A.right;
        }
    }

    private static boolean isLeaf(TreeNode A) {
        // A node is a leaf if it has no left child and no right child.
        return A.left == null && A.right == null;
    }
}