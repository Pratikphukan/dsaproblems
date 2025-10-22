package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidBST {

    static class TreeInfo {

        int min;
        int max;
        boolean isBST;

        public TreeInfo(int a, int b, boolean isBST) {
            this.min = a;
            this.max = b;
            this.isBST = isBST;
        }

    }

    public static void main(String[] args) {
        BinarySearchTreeService bst = new BinarySearchTreeService();
//        TreeNode root = new TreeNode(8);
//        bst.insertIntoBST(root, 3);
//        bst.insertIntoBST(root, 10);
//        bst.insertIntoBST(root, 6);
//        bst.insertIntoBST(root, 14);
//        bst.insertIntoBST(root, 1);
//        bst.insertIntoBST(root, 4);
//        bst.insertIntoBST(root, 7);
//        bst.insertIntoBST(root, 13);

//        TreeNode root = new TreeNode(4);
//        root.right = new TreeNode(5);
//        root.right.left = new TreeNode(3);

//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(5);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(6);

        //-2147483648
        TreeNode root = new TreeNode(-2147483648);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);


//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(4);
//        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(6);

        System.out.println(checkBSTv1(root, 0L, (long) Math.pow(2, 32) - 1));
        System.out.println(checkBSTv2(root));
        System.out.println(checkBSTv3(root, 0L, (long) Math.pow(2, 32) - 1));
        System.out.println(checkBSTv4(root));
    }

    //working code, inorder traversal
    private static boolean checkBSTv4(TreeNode A) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = A;
        long min = Long.MIN_VALUE;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }
            curr = stack.pollFirst();
            if (curr.val <= min) return false;
            min = curr.val;
            curr = curr.right;
        }
        return true;
    }

    //working code, faster
    private static boolean checkBSTv3(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val <= min || node.val >= max)
            return false;
        return checkBSTv3(node.left, min, node.val) && checkBSTv3(node.right, node.val, max);
    }

    //working code
    private static boolean checkBSTv1(TreeNode node, long min, long max) {
        if (node == null)
            return true;
        if (node.val > min && node.val < max)
            return checkBSTv1(node.left, min, (long) node.val) && checkBSTv1(node.right, (long) node.val, max);
        return false;
    }

    private static int checkBSTv2(TreeNode node) {
        TreeInfo ans = checkBST(node);
        if (ans.isBST) {
            return 1;
        }
        return 0;
    }

    //working code
    private static TreeInfo checkBST(TreeNode root) {
        if (root == null) {
            return new TreeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        TreeInfo left = checkBST(root.left);
        TreeInfo right = checkBST(root.right);
        if (left.isBST && right.isBST && (long) root.val > left.max && (long) root.val < right.min) {
            return new TreeInfo(
                    Math.min(root.val, Math.min(left.min, right.min)),
                    Math.max(root.val, Math.max(left.max, right.max)),
                    true);
        }
        return new TreeInfo(
                Math.min(root.val, Math.min(left.min, right.min)),
                Math.max(root.val, Math.max(left.max, right.max)),
                false);
    }

    //working code
//    private static boolean checkBSTv1(TreeNode node, long min, long max) {
//        if (node.left == null && node.right == null) {
//            return true;
//        }
//        if (node.left == null) {
//            if (node.right.val > node.val && node.right.val < max) {
//                return checkBSTv1(node.right, (long) node.val, max);
//            }
//            return false;
//        }
//        if (node.right == null) {
//            if (node.left.val < node.val && node.left.val > min) {
//                return checkBSTv1(node.left, min, (long) node.val);
//            }
//            return false;
//        }
//        if (node.left.val < node.val && node.right.val > node.val && node.left.val > min && node.right.val < max) {
//            return checkBSTv1(node.left, min, (long) node.val) && checkBSTv1(node.right, (long) node.val, max);
//        }
//        return false;
//    }

}
