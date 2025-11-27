package com.dsaproblems.DSAProblems.binarytree01;

import com.dsaproblems.DSAProblems.binarytree02.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumDepthOfBT {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(10);
        head.setLeft(new TreeNode(5));
        head.getLeft().setLeft(new TreeNode(2));
        head.getLeft().setRight(new TreeNode(7));
        head.getLeft().getRight().setLeft(new TreeNode(6));
        head.getLeft().getRight().setRight(new TreeNode(8));
        head.setRight(new TreeNode(20));
        head.getRight().setRight(new TreeNode(22));
        head.getRight().getRight().setLeft(new TreeNode(21));
        head.getRight().getRight().setRight(new TreeNode(50));
        System.out.println(getMinDepthOfBTv1(head));
        System.out.println(getMinDepthOfBTv2(head));
    }

    private static int getMinDepthOfBTv2(TreeNode head) {
        if (head == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.pollFirst();
                if (curr.left == null && curr.right == null) return depth;
                if (curr.left != null) queue.addLast(curr.left);
                if (curr.right != null) queue.addLast(curr.right);
            }
            depth++;
        }
        return depth;
    }

    private static int getMinDepthOfBTv1(TreeNode head) {
        if (head == null) return 0;
        if (head.left == null) return getMinDepthOfBTv1(head.right) + 1;
        if (head.right == null) return getMinDepthOfBTv1(head.left) + 1;
        return Math.min(getMinDepthOfBTv1(head.left),
                getMinDepthOfBTv1(head.right)) + 1;
    }
}
