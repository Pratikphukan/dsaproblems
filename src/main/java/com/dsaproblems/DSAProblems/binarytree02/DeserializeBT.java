package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DeserializeBT {

    public static void main(String[] args) {
        ArrayList<Integer> test4 = new ArrayList<>();
        test4.add(1);
        test4.add(2);
        test4.add(3);
        test4.add(4);
        test4.add(-1);
        test4.add(-1);
        test4.add(6);
        test4.add(-1);
        test4.add(-1);
        test4.add(-1);
        test4.add(-1);
        TreeNode root4 = deserializeBTv1(test4);
        System.out.println(root4);
    }

    //working code
    private static TreeNode deserializeBTv1(ArrayList<Integer> A) {
        // Check if the input list is empty, returning null if so
        if (A == null || A.isEmpty()) {
            return null;
        }

        // Check the first element, return null if it represents an empty tree (-1)
        if (A.get(0) == -1) {
            return null;
        }

        // Create the root node with the first item in A
        TreeNode root = new TreeNode(A.get(0));

        // Initialize a queue to perform level order construction of the tree
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);  // Add root node to the queue

        // Index pointer to traverse the list from the second element onwards
        int index = 1;

        // Process the queue until all nodes are visited or traversal is complete
        while (!queue.isEmpty() && index < A.size()) {
            // Extract the node at the front of the queue
            TreeNode current = queue.pollFirst();

            // Process left child if within bounds of the list
            if (index < A.size()) {
                int leftVal = A.get(index); // Get next value for left child
                index++; // Move index forward
                if (leftVal != -1) { // If value is not -1, create a left child node
                    TreeNode leftChild = new TreeNode(leftVal);
                    current.left = leftChild; // Set left child of current node
                    queue.addLast(leftChild);   // Add to queue for further processing
                }
            }

            // Process right child if within bounds of the list
            if (index < A.size()) {
                int rightVal = A.get(index); // Get next value for right child
                index++; // Move index forward
                if (rightVal != -1) { // If value is not -1, create a right child node
                    TreeNode rightChild = new TreeNode(rightVal);
                    current.right = rightChild; // Set right child of current node
                    queue.addLast(rightChild);    // Add to queue for further processing
                }
            }
        }

        // Return the root of the constructed binary tree
        return root;
    }
}
