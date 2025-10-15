package com.dsaproblems.DSAProblems.binarytree02;

public class MaxSumPathInBT {

    static int max = Integer.MIN_VALUE;

    static int gMax = Integer.MIN_VALUE;

    private static int maxSum;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        //System.out.println(maxSumPathv1(root));
        System.out.println(maxSumPathv2(root));


//        TreeNode root1 = new TreeNode(1);
//        root1.left = new TreeNode(2);
//        root1.right = new TreeNode(3);
//        System.out.println(maxPathSumv3(root1));

        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(10);
        root4.right.left = new TreeNode(20);
        root4.right.right = new TreeNode(1);
        System.out.println(maxPathSumv3(root4));
    }

    //working code
    private static int maxPathSumv3(TreeNode A) {
        maxSum = Integer.MIN_VALUE;
        dfs(A);
        return maxSum;
    }

    private static int dfs(TreeNode A) {
        if (A == null) return 0;
        // Recursively get the maximum branch sum from the left child.
        // If the left branch sum is negative, we ignore it by taking max with 0.
        int leftMax = Math.max(dfs(A.left), 0);

        // Recursively get the maximum branch sum from the right child.
        // Similarly, ignore negative contribution.
        int rightMax = Math.max(dfs(A.right), 0);
        int currentPathSum = A.val + leftMax + rightMax;
        maxSum = Math.max(maxSum, currentPathSum);

        // For recursion: return the maximum sum branch that can be extended to the parent.
        // We add the current node's value to the maximum of the left or right branch.
        // Only one branch can be chosen to extend upward.
        return A.val + Math.max(leftMax, rightMax);
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
