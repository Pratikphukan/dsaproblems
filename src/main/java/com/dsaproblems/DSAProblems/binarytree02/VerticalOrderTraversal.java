package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class VerticalOrderTraversal {

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        Integer breadth;

        public TreeNode(int val) {
            this.val = val;
            this.breadth = 0;
        }
    }

    static class Node {
        int breadth;
        TreeNode node;

        Node(TreeNode node) {
            this.breadth = 0;
            this.node = node;
        }

        Node(TreeNode node, int breadth) {
            this.breadth = breadth;
            this.node = node;
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
        head.right.right.left = new TreeNode(4);

        System.out.println(getVerticalOrderOfBTv1(head));
        System.out.println(getVerticalOrderOfBTv2(head));
    }

    private static ArrayList<ArrayList<Integer>> getVerticalOrderOfBTv2(TreeNode head) {
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        if (head == null) {
            return new ArrayList<>(map.values());
        }
        Deque<Node> queue = new LinkedList<>();
        queue.addLast(new Node(head));
        while (!queue.isEmpty()) {
            Node temp = queue.pollFirst();
            int currbreadth = temp.breadth;
            if (!map.containsKey(currbreadth)) {
                map.put(currbreadth, new ArrayList<>(Arrays.asList(temp.node.val)));
            } else {
                map.get(currbreadth).add(temp.node.val);
            }
            if (temp.node.left != null) {
                queue.addLast(new Node(temp.node.left, currbreadth - 1));
            }
            if (temp.node.right != null) {
                queue.addLast(new Node(temp.node.right, currbreadth + 1));
            }
        }
        return new ArrayList<>(map.values());
    }

    private static ArrayList<ArrayList<Integer>> getVerticalOrderOfBTv1(TreeNode head) {
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        if (head == null) {
            return new ArrayList<>(map.values());
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.pollFirst();
            int currbreadth = temp.breadth; // get the parent height and assign the height to the children
            if (!map.containsKey(currbreadth)) {
                map.put(currbreadth, new ArrayList<>(Arrays.asList(temp.val)));
            } else {
                map.get(currbreadth).add(temp.val);
            }
            if (temp.left != null) {
                temp.left.breadth = currbreadth - 1;
                queue.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.breadth = currbreadth + 1;
                queue.add(temp.right);
            }
        }
        return new ArrayList<>(map.values());
    }

}
