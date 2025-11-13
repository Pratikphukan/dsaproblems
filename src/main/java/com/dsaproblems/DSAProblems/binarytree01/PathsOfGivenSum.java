package com.dsaproblems.DSAProblems.binarytree01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PathsOfGivenSum {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);        // Leaf node with value 7
        TreeNode node2 = new TreeNode(2);        // Leaf node with value 2
        TreeNode node11 = new TreeNode(11, node7, node2);  // Node with value 11 and children 7 and 2
        TreeNode node13 = new TreeNode(13);      // Leaf node with value 13
        TreeNode node1 = new TreeNode(1);        // Leaf node with value 1
        TreeNode node4_right = new TreeNode(4, null, node1); // Node with value 4 and right child 1
        TreeNode node4_left = new TreeNode(4, node11, null);   // Node with value 4 and left child 11
        TreeNode node8 = new TreeNode(8, node13, node4_right); // Node with value 8 with children 13 and 4 (node4_right)
        TreeNode root1 = new TreeNode(5, node4_left, node8);   // Root of the tree
        // Expected output: true because path 5->4->11->2 sums up to 22
        System.out.println(hasPathSumv1(root1, 22)); // Should print true
        System.out.println(hasPathSumv2(root1, 22));
    }

    //working code
    private static boolean hasPathSumv2(TreeNode root, int target) {
        if (root == null) return false;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> sumStack = new ArrayDeque<>();
        stack.addFirst(root);
        sumStack.addFirst(root.val);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            int currSum = sumStack.pollFirst();
            if (curr.left == null && curr.right == null && currSum == target) return true;
            if (curr.right != null) {
                stack.addFirst(curr.right);
                sumStack.addFirst(currSum + curr.right.val);
            }
            if (curr.left != null) {
                stack.addFirst(curr.left);
                sumStack.addFirst(currSum + curr.left.val);
            }
        }
        return false;
    }

    //working code, better
    private static boolean hasPathSumv1(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == target;
        }
        boolean leftHasPath = hasPathSumv1(root.left, target - root.val);
        boolean rightHasPath = hasPathSumv1(root.right, target - root.val);
        return leftHasPath || rightHasPath;
    }


    public List<List<Integer>> findPathsWithSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.getVal());
        if (node.getLeft() == null && node.getRight() == null && target == node.getVal()) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.getLeft(), target - node.getVal(), path, result);
            dfs(node.getRight(), target - node.getVal(), path, result);
        }
        path.remove(path.size() - 1); // backtrack
    }
}
