package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class RightViewOfBT {

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "[" + val + "," + left + "," + right + "]";
        }
    }

    static class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
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

        System.out.println(getRightViewOfBTv1(head));
        System.out.println(getRightViewOfBTv2(head));
        System.out.println(getRightViewOfBTv3(head));
    }

    // follows BFS
    //Time Complexity (TC): O(N), where N is the number of nodes in the binary tree. Each node is visited once
    //Space Complexity (SC): O(W), where W is the maximum width of the tree (maximum number of nodes at any level).
    // The queue can hold up to W nodes at a time.
    private static ArrayList<Integer> getRightViewOfBTv1(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
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
                if (temp.right != null) {
                    queue.addLast(temp.right);
                }
                if (temp.left != null) {
                    queue.addLast(temp.left);
                }
            }
        }
        return ans;
    }

    //working code
    public static ArrayList<Integer> getRightViewOfBTv2(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(queue.peekLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return ans;
    }

    //working code
    private static ArrayList<Integer> getRightViewOfBTv3(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Pair> queue = new LinkedList<>();
        int l = 0;
        queue.addLast(new Pair(head, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.pollFirst();
            if (pair.node.right != null) {
                queue.addLast(new Pair(pair.node.right, pair.level + 1));
            }
            if (pair.node.left != null) {
                queue.addLast(new Pair(pair.node.left, pair.level + 1));
            }
            if (pair.level == l) {
                ans.add(pair.node.val);
                l++;
            }
        }
        return ans;
    }

}
