package com.dsaproblems.DSAProblems.binarytree02;

public class HeightBalancedBT {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(7);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(10);
        head.left.right = new TreeNode(6);
        head.left.right.left = new TreeNode(3);
        head.left.right.right = new TreeNode(11);
        head.right.right = new TreeNode(9);
        head.right.right.left = new TreeNode(4);
        System.out.println(isHeightBalancedv1(head) != -1);

        System.out.println(isHeightBalancedv2(head) != -1);

    }

    //It returns -1 for any unbalanced subtree and the subtree height otherwise, enabling early exits.
    //Correctness: computes left/right heights, checks for -1 (propagates failure), then checks |lh - rh| > 1.
    //Complexity: O(n) time, O(h) stack space.
    //Minor cleanup: the explicit leaf check is unnecessary — recursion handles it.
    private static int isHeightBalancedv2(TreeNode head) {
        if (head == null) return 0;
        int lh = isHeightBalancedv2(head.left);
        if (lh == -1) return -1;
        int rh = isHeightBalancedv2(head.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return Math.max(lh, rh) + 1;
    }

    // TC to search anything in a height balanced BT is O(n)
    public static int isHeightBalancedv1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        int lh = isHeightBalancedv1(head.left);
        int rh = isHeightBalancedv1(head.right);
        if (lh == -1 || rh == -1) {
            return -1;
        }
        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh) + 1;
    }
}
