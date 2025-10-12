package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

public class LeftViewOfBT {

    static class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(7);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(6);
        head.left.right.right = new TreeNode(11);
        head.left.right.left = new TreeNode(5);
        head.right = new TreeNode(5);
        head.right.right = new TreeNode(9);
        // head.right.right.left = new TreeNode(4);

        System.out.println(getLeftViewOfBTv1(head));
        System.out.println(getLeftViewOfBTv2(head));
    }

    // follows BFS
    //working code
    //Time Complexity (TC): O(N), where N is the number of nodes in the binary tree. Each node is visited once
    //Space Complexity (SC): O(W), where W is the maximum width of the tree (maximum number of nodes at any level). The queue can hold up to W nodes at a time.
    private static List<Integer> getLeftViewOfBTv1(TreeNode head) {
        List<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(queue.peekFirst().val);
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (temp.left != null) {
                    queue.addLast(temp.left);
                }
                if (temp.right != null) {
                    queue.addLast(temp.right);
                }
            }
        }
        return ans;
    }

    //working code
    private static ArrayList<Integer> getLeftViewOfBTv2(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Pair> queue = new LinkedList<>();
        int l = 0;
        queue.addLast(new Pair(head, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.pollFirst();
            if (pair.node.left != null) {
                queue.addLast(new Pair(pair.node.left, pair.level + 1));
            }
            if (pair.node.right != null) {
                queue.addLast(new Pair(pair.node.right, pair.level + 1));
            }
            if (pair.level == l) {
                ans.add(pair.node.val);
                l++;
            }
        }
        return ans;
    }
}
