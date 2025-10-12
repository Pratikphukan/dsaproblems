package com.dsaproblems.DSAProblems.binarytree02;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.*;

public class SerializeBT {

    public static void main(String[] args) {
        com.dsaproblems.DSAProblems.binarytree01.TreeNode root = new com.dsaproblems.DSAProblems.binarytree01.TreeNode(1);
        root.left = new com.dsaproblems.DSAProblems.binarytree01.TreeNode(2);
        root.left.left = new com.dsaproblems.DSAProblems.binarytree01.TreeNode(4);
        root.left.right = new com.dsaproblems.DSAProblems.binarytree01.TreeNode(5);
        root.right = new com.dsaproblems.DSAProblems.binarytree01.TreeNode(3);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);

        root.right.right = new TreeNode(6);

        System.out.println(serializeBTv1(root));
        System.out.println(serializeBTv2(root));
    }

    //ArrayDeque:
    //Backed by a resizable array.
    //Faster for most queue operations (add, remove, peek) due to better cache locality.
    //Does not support null elements.
    //Not thread-safe.
    //No memory overhead for node objects.

    //LinkedList:
    //Doubly-linked list implementation.
    //Supports all List operations in addition to Deque.
    //Allows null elements.
    //Each element has extra memory overhead (node objects with pointers).
    //Slower for random access and some queue operations due to pointer chasing.
    private static ArrayList<Integer> serializeBTv2(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(A);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.pollFirst();
                result.add(cur == null ? -1 : cur.val);
                if (cur != null) {
                    queue.addLast(cur.left);
                    queue.addLast(cur.right);
                }
            }
        }
        return result;
    }

    private static ArrayList<Integer> serializeBTv1(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode emptyNode = new TreeNode(-1);
        queue.addLast(A);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.pollFirst();
            ans.add(currentNode.val);
            if (currentNode.val != -1) {
                queue.addLast(currentNode.left != null ? currentNode.left : emptyNode);
                queue.addLast(currentNode.right != null ? currentNode.right : emptyNode);
            }
        }
        return ans;
    }
}
