package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

public class BTPostorderTraversal {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(10);
        head.setLeft(new TreeNode(5));
        head.getLeft().setLeft(new TreeNode(2));
        head.getLeft().setRight(new TreeNode(7));
        head.getLeft().getRight().setLeft(new TreeNode(6));
        head.getLeft().getRight().setRight(new TreeNode(8));
        head.setRight(new TreeNode(20));
        head.getRight().setRight(new TreeNode(22));
        head.getRight().getRight().setLeft(new TreeNode(21));
        head.getRight().getRight().setRight(new TreeNode(50));

        System.out.println(iterativePostorderTraversalv1(head));
        System.out.println(iterativePostorderTraversalv2(head));
    }

    // it is depth first search
    // process the left subtree, right subtree, root
    // moving in bottom to top manner
    public static ArrayList<Integer> iterativePostorderTraversalv2(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (A == null) return ans;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.removeFirst();
            if (temp.left != null) stack.addFirst(temp.left);
            if (temp.right != null) stack.addFirst(temp.right);
            ans.add(0, temp.val); //inserting at the front of the list (ans.add(0, ...)) is (O(n)) per operation
        }
        return ans;
    }

    //use an ArrayDeque as the stack and a LinkedList for output
    public static List<Integer> iterativePostorderTraversalv1(TreeNode node) {
        LinkedList<Integer> postorder = new LinkedList<>();
        if (node == null) return postorder;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            postorder.addFirst(temp.val);
            if (temp.left != null) stack.addFirst(temp.left);
            if (temp.right != null) stack.addFirst(temp.right);
        }
        return postorder;
    }
}
