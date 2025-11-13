package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class KthSmallestElementInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        System.out.println(findKthSmallestElementv1(root, 4));
        System.out.println(findKthSmallestElementv2(root, 4));
        System.out.println(findKthSmallestElementv3(root, 4));
        System.out.println(findKthSmallestElementv4(root, 4));
    }

    //working code
    private static int findKthSmallestElementv4(TreeNode A, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (A != null) {
            stack.addFirst(A);
            A = A.left;
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            if (--k == 0) return curr.val;
            if (curr.right != null) {
                TreeNode temp = curr.right;
                while (temp != null) {
                    stack.addFirst(temp);
                    temp = temp.left;
                }
            }
        }
        return -1;
    }

    //working code
    private static int findKthSmallestElementv3(TreeNode A, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = A;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }
            curr = stack.pollFirst();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        return -1;
    }

    static int k = 0;

    private static int findKthSmallestElementv2(TreeNode A, int B) {
        k = B;
        return findKthSmallestElementv2(A);
    }

    private static int findKthSmallestElementv2(TreeNode A) {
        if (A == null)
            return -1;
        // We do an inorder traversal here.
        int k1 = findKthSmallestElementv2(A.left);
        if (k == 0)
            return k1; // left subtree has k or more elements.
        k--;
        if (k == 0)
            return A.val; // root is the kth element.
        return findKthSmallestElementv2(A.right); // answer lies in the right node.
    }

    //working code
    private static int findKthSmallestElementv1(TreeNode A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans = inorder(ans, A);
        return ans.get(B - 1);
    }

    private static ArrayList<Integer> inorder(ArrayList<Integer> ans, TreeNode A) {
        if (A != null) {
            ans = inorder(ans, A.left);
            ans.add(A.val);
            ans = inorder(ans, A.right);
        }
        return ans;
    }
}
