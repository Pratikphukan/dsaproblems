package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

public class BSTInsert {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        System.out.println(insertIntoBSTv1(root, 3));
        System.out.println(insertIntoBSTv1(root, 1));


        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        System.out.println(insertIntoBSTv2(root, 6));
        System.out.println(insertIntoBSTv2(root, 8));
    }

    //Time complexity: O(h), where h is the height of the tree (same as the recursive version).
    //Space complexity: O(1), since it avoids the call stack used in recursion.
    //Use iterative approach to avoid stack overflow for very deep trees
    private static TreeNode insertIntoBSTv2(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode curr = root;
        while (true) {
            if (curr.val >= val) {
                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                }
                curr = curr.right;
            }
        }
        return root;
    }

    //Time complexity: O(h), where h is the height of the tree.
    //Space complexity: O(h), due to the recursion stack.
    //worst case time complexity is O(n)
    //maximum size of recursive stack is O(n) in case of skewed tree
    private static TreeNode insertIntoBSTv1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val >= val) {
            root.left = insertIntoBSTv1(root.left, val);
        } else {
            root.right = insertIntoBSTv1(root.right, val);
        }
        return root;
    }
}
