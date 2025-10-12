package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

public class BSTSearch {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        System.out.println(searchInBSTv1(root, 7));
        System.out.println(searchInBSTv2(root, 22));
    }

    //Time complexity: O(h), where h is the height of the tree.
    //Space complexity: O(1), since it is iterative and does not use the call stack.
    private static boolean searchInBSTv2(TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == val) {
                return true;
            } else if (curr.val > val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    //Time complexity: O(h), where h is the height of the tree.
    //Space complexity: O(h), due to the recursion stack.
    //For very deep trees, an iterative version can avoid stack overflow, but the algorithm itself is optimal for a standard BST search.
    private static boolean searchInBSTv1(TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        return root.val > val ?
                searchInBSTv1(root.left, val) :
                searchInBSTv1(root.right, val);
    }
}
