package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(3);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(21);
        root.right.right.right = new TreeNode(50);
        System.out.println(recoverBSTv1(root));
        System.out.println(recoverBSTv2(root));
    }

    //working code
    private static ArrayList<Integer> recoverBSTv2(TreeNode root) {
        ArrayList<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        TreeNode curr = root;// not necessary if we are returning an arraylist
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }
            curr = stack.pollFirst();
            nodes.add(curr.val);
            curr = curr.right;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int n = nodes.size();
        for (int i = n - 1; i >= 1; i--) {
            if (nodes.get(i) < nodes.get(i - 1)) {
                ans.add(nodes.get(i));
                break;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (nodes.get(i) > nodes.get(i + 1)) {
                ans.add(nodes.get(i));
                break;
            }
        }
        return ans;
    }

    //working code
    private static ArrayList<Integer> recoverBSTv1(TreeNode root) {
        ArrayList<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        addLeftMostToStack(root, stack);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            nodes.add(temp.val);
            if (temp.right != null) {
                TreeNode node = temp.right;
                addLeftMostToStack(node, stack);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int n = nodes.size();
        for (int i = n - 1; i >= 1; i--) {
            if (nodes.get(i) < nodes.get(i - 1)) {
                ans.add(nodes.get(i));
                break;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (nodes.get(i) > nodes.get(i + 1)) {
                ans.add(nodes.get(i));
                break;
            }
        }
        return ans;
    }

    private static void addLeftMostToStack(TreeNode curr, Deque<TreeNode> stack) {
        while (curr != null) {
            stack.addFirst(curr);
            curr = curr.left;
        }
    }
}
