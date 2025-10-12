package com.dsaproblems.DSAProblems.binarytree02;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.*;

public class OddEvenLevels {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);

        root.right.right = new TreeNode(6);

        System.out.println(differenceBetweenOddEvenLevelsv1(root));
        System.out.println(differenceBetweenOddEvenLevelsv2(root));
    }

    //WORKING CODE
    private static int differenceBetweenOddEvenLevelsv2(TreeNode head) {
        if (head == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        int result = 0;
        boolean isOddLevel = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                result += isOddLevel ? temp.val : -temp.val;
                if (temp.left != null) queue.addLast(temp.left);
                if (temp.right != null) queue.addLast(temp.right);
            }
            isOddLevel = !isOddLevel; // toggle level
        }
        return result;
    }

    //TC is O(n) and SC is O(n), uses BFS
    //the queue can hold up to all nodes at the largest level of the binary tree (in the worst case,
    // for a complete binary tree, this is about n/2 nodes
    private static int differenceBetweenOddEvenLevelsv1(TreeNode head) {
        if (head == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(head);
        int level = 0, oddLevelSum = 0, evenLevelSum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level += 1;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (level % 2 == 1) {
                    oddLevelSum += temp.val;
                } else {
                    evenLevelSum += temp.val;
                }
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return oddLevelSum - evenLevelSum;
    }


}
