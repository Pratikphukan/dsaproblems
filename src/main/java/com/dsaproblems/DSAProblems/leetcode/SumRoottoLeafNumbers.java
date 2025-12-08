package com.dsaproblems.DSAProblems.leetcode;

import com.dsaproblems.DSAProblems.binarytree02.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumRoottoLeafNumbers {

    static class Node {
        TreeNode treeNode;
        int val;

        Node(TreeNode treeNode, int val) {
            this.treeNode = treeNode;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(7);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(6);
        head.left.right.left = new TreeNode(3);
        head.left.right.right = new TreeNode(1);
        head.right.right = new TreeNode(9);
        head.right.right.left = new TreeNode(4);
        System.out.println(sumOfAllRootToLeafNodesv1(head));
        System.out.println(sumOfAllRootToLeafNodesv2(head));
    }

    static int ans = 0;

    private static int sumOfAllRootToLeafNodesv2(TreeNode head) {
        dfs(head, 0);
        return ans;
    }

    private static void dfs(TreeNode head, int sum) {
        if (head.left == null && head.right == null) {
            ans += sum * 10 + head.val;
        }
        if (head.left != null) dfs(head.left, sum * 10 + head.val);
        if (head.right != null) dfs(head.right, sum * 10 + head.val);
    }

    //iterative DFS: O(n) time and O(h) space
    private static int sumOfAllRootToLeafNodesv1(TreeNode head) {
        if (head == null) return 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(new Node(head, head.val));
        int sum = 0;
        while (!stack.isEmpty()) {
            Node curr = stack.pollFirst();
            TreeNode treeNode = curr.treeNode;
            int currVal = curr.val;
            if (treeNode.left == null && treeNode.right == null) {
                sum += currVal;
            }
            if (treeNode.right != null) {
                stack.addFirst(new Node(treeNode.right, currVal * 10 + treeNode.right.val));
            }
            if (treeNode.left != null) {
                stack.addFirst(new Node(treeNode.left, currVal * 10 + treeNode.left.val));
            }
        }
        return sum;
    }
}
