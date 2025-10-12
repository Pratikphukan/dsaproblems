package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DistancebetweenNodesofBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(11);

        int B = 6;
        int C = 11;

//        TreeNode root = new TreeNode(32);
//        root.left = new TreeNode(25);
//        root.right = new TreeNode(46);
//        root.left.left = new TreeNode(17);
//        root.left.right = new TreeNode(27);
//        root.right.left = new TreeNode(40);
//        root.right.right = new TreeNode(49);
//        root.left.left.left = new TreeNode(9);
//
//        int B = 32;
//        int C = 9;

        System.out.println(distanceBetweenNodesv1(root, B, C));
        System.out.println(distanceBetweenNodesv2(root, B, C));


        //learning
        TreeNode node = new TreeNode(4);
        changeRoot(node);
        System.out.println(node);
        modifyRoot(node);
        System.out.println(node);

        List<Integer> arr = new ArrayList<>(List.of(3, 4));
        modifyList(arr);
        System.out.println(arr);
        changeList(arr);
        System.out.println(arr);
    }

    /*
    TreeNode Example:
    When you pass a TreeNode to a method, you pass a copy of the reference.
    If you change the fields of the object (e.g., root.val = 10;), the change is visible outside.
    If you reassign the reference (e.g., root = root.left;), it only affects the local copy inside the method.
    ArrayList Example:
    When you pass an ArrayList to a method, you also pass a copy of the reference.
    If you modify the contents (e.g., arr.add(9);), the change is visible outside.
    If you reassign the reference (e.g., arr = new ArrayList<>();), it only affects the local copy inside the method.
     */

    private static void changeList(List<Integer> arr) {
        arr = new ArrayList<>();
    }

    private static void modifyRoot(TreeNode node) {
        node.val = 76;
    }

    private static void modifyList(List<Integer> arr) {
        arr.add(9);
    }

    private static void changeRoot(TreeNode root) {
        root = root.left;
    }

    //working code
    private static int distanceBetweenNodesv2(TreeNode root, int B, int C) {
        TreeNode lca = findLCA(root, B, C);
        return findDistance(lca, B) + findDistance(lca, C);
    }

    private static TreeNode findLCA(TreeNode root, int B, int C) {
        //In Java, when you pass an object (like TreeNode root) to a method,
        // you are passing a copy of the reference to that object, not the actual object itself.
        //Modifying the root reference in findLCA is safe because Java passes object references by value.
        // The changes to root inside findLCA do not affect the original root in the caller.
        // You will not lose the original tree root; only the local copy of the reference is changed.
        while (root != null) {
            if (B < root.val && C < root.val) {
                root = root.left;
            } else if (B > root.val && C > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    //working code
    private static int distanceBetweenNodesv1(TreeNode root, int B, int C) {
        TreeNode temp = root;
        int count = 0;
        while (temp != null) {
            if (B == temp.val) {
                return findDistance(temp, C);
            }
            if (C == temp.val) {
                return findDistance(temp, B);
            }
            if (B < temp.val && C > temp.val ||
                    B > temp.val && C < temp.val) {
                count += findDistance(temp, B) + findDistance(temp, C);
                return count;
            } else if (B > temp.val) {//just check either B or C(only one check), because both will align to one direction only
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        return count;
    }

    private static int findDistance(TreeNode node, int x) {
        TreeNode temp = node;
        int count = 0;
        while (temp != null) {
            if (x > temp.val) {
                count++;
                temp = temp.right;
            } else if (x < temp.val) {
                count++;
                temp = temp.left;
            } else {
                break;
            }
        }
        return count;
    }
}
