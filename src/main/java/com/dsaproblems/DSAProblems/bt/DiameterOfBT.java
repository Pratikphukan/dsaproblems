package com.dsaproblems.DSAProblems.bt;

public class DiameterOfBT {

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class TreeInfo {
        int height;
        int diameter;

        TreeInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    static int diameter = 0;

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
        System.out.println(getDiameterOfBinaryTreev1(head));
        System.out.println(diameter);

        System.out.println(getDiameterOfBinaryTreev2(head));

        System.out.println(getDiameterOfBinaryTreev3(head).diameter);
    }

    // max distance between any two leaf nodes
    // diameter may include the root node
    // it may be in left sub tree/ right sub tree
    //working code
    public static int getDiameterOfBinaryTreev1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        int lh = getDiameterOfBinaryTreev1(head.left);// get max height of left subtree
        int rh = getDiameterOfBinaryTreev1(head.right);// get max height of right subtree
        diameter = Math.max(lh + rh, diameter);
        return Math.max(lh, rh) + 1; // returns the height of the particular node(left/right+1(root))
    }

    public static int getDiameterOfBinaryTreev2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }
        int lh = getHeightOfTree(head.left);// get max height of left subtree
        int rh = getHeightOfTree(head.right);// get max height of right subtree
        int ld = getDiameterOfBinaryTreev2(head.left);
        int rd = getDiameterOfBinaryTreev2(head.right);
        return Math.max(Math.max(ld, rd), (lh + rh + 1)); // returns the height of the particular //
        // node(left/right+1(root))
    }

    public static int getHeightOfTree(TreeNode node) {
        if (node == null) {
            return 0;// you can return -1 if you consider edges to be height
        }
        return Math.max(getHeightOfTree(node.left), getHeightOfTree(node.right)) + 1;
    }

    // make use of postorder traversal to have TC as O(n)
    //working code
    public static TreeInfo getDiameterOfBinaryTreev3(TreeNode head) {
        if (head == null) {
            return new TreeInfo(0, 0);
        }
        TreeInfo left = getDiameterOfBinaryTreev3(head.left);// get max height of left subtree
        TreeInfo right = getDiameterOfBinaryTreev3(head.right);// get max height of right subtree
        int height = Math.max(left.height, right.height) + 1;
        int diameter = Math.max(Math.max(left.diameter, right.diameter),
                left.height + right.height);
        return new TreeInfo(height, diameter);
    }
}
