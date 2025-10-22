package com.dsaproblems.DSAProblems.binarytree02;

public class SymmetricBinaryTree {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println(checkSymmetricTreev1(root, root));
        System.out.println(checkSymmetricTreev2(root, root));
    }

    //working code
    //Time complexity: O(N), where N is the number of nodes.
    //Space complexity: O(H), where H is the tree height (due to recursion stack).
    private static boolean checkSymmetricTreev2(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 == null || head2 == null) return false;
        return head1.val == head2.val &&
                checkSymmetricTreev2(head1.left, head2.right) &&
                checkSymmetricTreev2(head1.right, head2.left);
    }

    //not correct code, it only checks one side of the tree recursively
    public static boolean checkSymmetricTreev1(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null || head2 == null) {
            return false;
        }
        return head1.val == head2.val && checkSymmetricTreev1(head1.left, head2.right);
    }
}
