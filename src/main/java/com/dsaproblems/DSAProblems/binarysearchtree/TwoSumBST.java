package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.*;

public class TwoSumBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(12);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(14);
        root.left.left.left = new TreeNode(8);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(27);

        int targetSum = 28;

        System.out.println(findTargetSumInBSTv1(root, targetSum));
        System.out.println(findTargetSumInBSTv2(root, targetSum));
    }

    //working code if there are no repeated values in the BST
    private static int findTargetSumInBSTv2(TreeNode root, int target) {
        Set<Integer> seen = new HashSet<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            int complement = target - node.val;
            if (seen.contains(complement)) {
                return 1;
            }
            seen.add(node.val);
            if (node.left != null) stack.addFirst(node.left);
            if (node.right != null) stack.addFirst(node.right);
        }
        return 0;
    }

    //working code
    private static int findTargetSumInBSTv1(TreeNode A, int B) {
        int result = 0;
        // Use the HashMap to store the node values and their frequencies.
        Map<Integer, Integer> map = new HashMap<>();
        //Traverse the tree using stacks
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            //Find the compliment of B
            int comp = B - node.val;
            // Check the HashMap if we have a compliment
            if (map.containsKey(comp)) {
                return 1;
            }
            map.put(node.val, map.getOrDefault(node.val, 0) + 1);
            if (node.left != null) {
                stack.addFirst(node.left);
            }
            if (node.right != null) {
                stack.addFirst(node.right);
            }
        }

        return result;
    }
}
