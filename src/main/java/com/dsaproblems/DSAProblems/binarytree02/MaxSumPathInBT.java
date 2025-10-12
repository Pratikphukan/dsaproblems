package com.dsaproblems.DSAProblems.binarytree02;

public class MaxSumPathInBT {

    static int max = Integer.MIN_VALUE;

    static int gMax = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        //System.out.println(maxSumPathv1(root));
        System.out.println(maxSumPathv2(root));
    }

    private static int maxSumPathv2(TreeNode A) {
//        if (A == null)
//            return 0;
//        int x = Math.max(0, maxSumPathv2(A.left));
//        int y = Math.max(0, maxSumPathv2(A.right));
//        max = Math.max(max, x + y + A.val);
//        return Math.max(x + A.val, y + A.val);

        if (A == null) return 0;
        int max = Integer.MIN_VALUE, nodeMax = 0;
        int l = maxSumPathv2(A.left);
        int r = maxSumPathv2(A.right);
        max = Math.max(A.val, Math.max(l + A.val, r + A.val));
        nodeMax = max;
        max = Math.max(l + r + A.val, max);
        gMax = Math.max(gMax, max);
        return nodeMax;
    }

    private static int maxSumPathv1(TreeNode A) {
        if (A == null) return 0;
        int left = maxSumPathv1(A.left);
        int right = maxSumPathv1(A.right);
        if (A.left != null && A.right != null) {
            max = Math.max(max, left + right + A.val);
            return Math.max(left, right) + A.val;
        }
        return (A.left == null) ? right + A.val : left + A.val;
    }
}
