package com.dsaproblems.DSAProblems.binarytree01;

import java.util.*;

public class PathToGivenNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(pathToGivenNodev1(root, 5));
        System.out.println(pathToGivenNodev2(root, 5));
    }

    //working code
    private static ArrayList<Integer> pathToGivenNodev2(TreeNode A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        findPath(A, B, ans);
        Collections.reverse(ans);
        return ans;
    }

    private static boolean findPath(TreeNode A, int B, ArrayList<Integer> ans) {
        if (A == null) {
            return false;
        }
        if (A.val == B) {
            ans.add(A.val);
            return true;
        }
//        boolean isPresentinLeft = findPath(A.left, B, ans);
//        boolean isPresentinRight = findPath(A.right, B, ans);

        if (findPath(A.left, B, ans) || findPath(A.right, B, ans)) {
            ans.add(A.val);
            return true;
        }
        return false;
    }

    private static ArrayList<Integer> pathToGivenNodev1(TreeNode root, int B) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        parentMap.put(root, null);
        TreeNode targetNode = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (node.val == B) {
                targetNode = node;
                break;
            }
            if (node.right != null) {
                stack.addFirst(node.right);
                parentMap.put(node.right, node);
            }
            if (node.left != null) {
                stack.addFirst(node.left);
                parentMap.put(node.left, node);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (targetNode != null) {
            ans.add(targetNode.val);
            targetNode = parentMap.get(targetNode);
        }
        Collections.reverse(ans);
        return ans;
    }
}
