package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class LevelWiseSum {

    static class TreeNode {
        int val;
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
        System.out.println(levelWiseSum(head));
    }

    public static ArrayList<Integer> levelWiseSum(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            int size = q.size();
            int sum = 0;
            while (size > 0) {
                TreeNode temp = q.pollFirst();
                sum += temp.val;
                if (temp.left != null) {
                    q.addLast(temp.left);
                }
                if (temp.right != null) {
                    q.addLast(temp.right);
                }
                size--;
            }
            ans.add(sum);
        }
        return ans;
    }
}
