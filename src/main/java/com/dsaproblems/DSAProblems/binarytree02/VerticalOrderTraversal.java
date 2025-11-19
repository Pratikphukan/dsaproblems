package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

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
            this(node);
            this.breadth = breadth;
        }
    }

    public static void main(String[] args) {
//        TreeNode head = new TreeNode(2);
//        head.left = new TreeNode(7);
//        head.left.left = new TreeNode(2);
//        head.left.right = new TreeNode(6);
//        head.left.right.right = new TreeNode(11);
//        head.left.right.left = new TreeNode(5);
//        head.right = new TreeNode(5);
//        head.right.right = new TreeNode(9);
//        head.right.right.left = new TreeNode(4);

        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(1);
        head.left.left = new TreeNode(0);
        head.left.right = new TreeNode(2);
        head.right = new TreeNode(4);
        head.right.left = new TreeNode(2);

        System.out.println(getVerticalOrderOfBTv1(head));
        System.out.println(getVerticalOrderOfBTv2(head));
        System.out.println(getVerticalOrderOfBTv3(head));
    }

    //different version, leetcode question
    private static List<List<Integer>> getVerticalOrderOfBTv3(TreeNode head) {
        Map<Integer, Queue<Integer>> map = new TreeMap<>();
        if (head == null) return new ArrayList<>();
        Deque<Node> queue = new LinkedList<>();
        queue.addLast(new Node(head));
        while (!queue.isEmpty()) {
            Node temp = queue.pollFirst();
            int currbreadth = temp.breadth;
            map.putIfAbsent(currbreadth, new PriorityQueue<>());
            map.get(currbreadth).add(temp.node.val);
            if (temp.node.left != null) queue.addLast(new Node(temp.node.left, currbreadth - 1));
            if (temp.node.right != null) queue.addLast(new Node(temp.node.right, currbreadth + 1));
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Queue<Integer> pq : map.values()) {
            List<Integer> col = new ArrayList<>();
            while (!pq.isEmpty()) col.add(pq.poll());
            result.add(col);
        }
        return result;
    }

    //working code
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
            map.putIfAbsent(currbreadth, new ArrayList<>());
            map.get(currbreadth).add(temp.node.val);
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
