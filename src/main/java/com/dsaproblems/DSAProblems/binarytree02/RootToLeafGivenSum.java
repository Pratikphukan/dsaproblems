package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RootToLeafGivenSum {

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

        System.out.println(checkRootToLeafSumv1(head, 18));
        System.out.println(checkRootToLeafSumv2(head, 18));
        System.out.println(checkRootToLeafSumv3(head, 18));
    }

    //Complexity: O(n) time, O(h) space.
    private static boolean checkRootToLeafSumv2(TreeNode head, int target) {
        if (head == null) return false;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> remainderSum = new ArrayDeque<>();
        stack.addFirst(head);
        remainderSum.addFirst(target - head.val);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            int rem = remainderSum.pollFirst();
            if (curr.left == null && curr.right == null && rem == 0) return true;
            if (curr.right != null) {
                stack.addFirst(curr.right);
                remainderSum.addFirst(rem - curr.right.val);
            }
            if (curr.left != null) {
                stack.addFirst(curr.left);
                remainderSum.addFirst(rem - curr.left.val);
            }
        }
        return false;
    }

    private static boolean checkRootToLeafSumv1(TreeNode head, int target) {
        if (head == null) {
            return false;
        }
        if (head.left == null && head.right == null && target == head.val) {
            return true;
        }
        return checkRootToLeafSumv1(head.left, target - head.val) ||
                checkRootToLeafSumv1(head.right, target - head.val);
    }

    private static void dfs(TreeNode head, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (head == null) return;
        currentPath.add(head.val);
        if (head.left == null && head.right == null && remainingSum == head.val) {
            result.add(new ArrayList<>(currentPath));
        }
        if (head.left != null || head.right != null) {
            dfs(head.left, remainingSum - head.val, currentPath, result);
            dfs(head.right, remainingSum - head.val, currentPath, result);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    private static List<List<Integer>> checkRootToLeafSumv3(TreeNode head, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // List to store the current path from root to leaf
        List<Integer> currentPath = new ArrayList<>();
        // Start DFS traversal from the root node
        dfs(head, target, currentPath, result);
        // Return the final list of valid paths
        return result;
    }
}
