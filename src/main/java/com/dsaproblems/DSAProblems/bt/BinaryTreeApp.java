package com.dsaproblems.DSAProblems.bt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BinaryTreeApp {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        Node head = new Node(2);
        head.left = new Node(7);
        head.right = new Node(5);
        head.left.left = new Node(10);
        head.left.right = new Node(6);
        head.left.right.left = new Node(3);
        head.left.right.right = new Node(11);
        head.right.right = new Node(9);
        head.right.right.left = new Node(4);

        System.out.println("Inorder:");
        bt.inorder(head);
        System.out.println("\nPreorder:");
        bt.preorder(head);
        System.out.println("\nPostorder:");
        bt.postorder(head);

        System.out.println("\n" + bt.getSumOfNodes(head));

        System.out.println(bt.getNumberOfNodes(head));

        System.out.println(bt.getHeightOfTree(head));

        System.out.println(bt.getIterativeHeightOfTree(head));

        System.out.println(bt.findMaxValue(head));

        System.out.println("iterativePostorderTraversal: " + bt.iterativePostorderTraversal(head));

        System.out.println("iterativePreorderTraversal: " + bt.iterativePreorderTraversal(head));

        System.out.println("iterativeInorderTraversal: " + bt.iterativeInorderTraversal(head));

        System.out.println("iterativeLevelOrderTraversalv1: " + bt.iterativeLevelOrderTraversalv1(head));

        System.out.println("iterativeLevelOrderTraversalv2: " + bt.iterativeLevelOrderTraversalv2(head));

        System.out.println(bt.iterativeReverseLevelOrderTraversal(head));

        System.out.println(bt.checkRootToLeafSum(head, 20));

        bt.maxSumfromRootToLeaf(head, 0);
        System.out.println(bt.maxSum);

        System.out.println(bt.sumOfLeafNodes(head));

        System.out.println(bt.sumOfLeftLeafNodes(head, false));

        bt.deepestLeftLeafNode(head, 1, false);
        System.out.println(bt.deepestLeftLeafNode.data);

        System.out.println(bt.differenceOfSumOfOddEvenLevels(head));

        Node parent = bt.getParent(head, 9);
        if (parent == null) {
            System.out.println("No value is present");
        } else {
            System.out.println(parent.data);
        }

        Node sibling = bt.getSibling(head, 3);
        if (sibling == null) {
            System.out.println("No sibling");
        } else {
            System.out.println(sibling.data);
        }

        Node lca = bt.lowestCommonAncestor2(head, 10, 11);
        if (!(bt.recursiveSearch(head, 10) && bt.recursiveSearch(head, 11))) {
            System.out.println("No LCA possible");
            ;
        }
        if (lca == null) {
            System.out.println("No LCA possible");
        } else {
            System.out.println(lca.data);
        }

        System.out.println(bt.isHeightBalanced(head) != -1);

        System.out.println(bt.getAncestors(head, 4));

//		bt.printBoundaryLeft(head);
//		bt.printLeaves(head);
//		bt.printBoundaryRight(head);
//		System.out.println();
        System.out.println(bt.printBoundary(head));

        System.out.println(bt.getVerticalOrder1(head));

        Map<Integer, ArrayList<Integer>> m1 = new HashMap<>();
        bt.getVerticalOrder(head, 0, m1);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : m1.entrySet()) {
            System.out.println(entry.getValue());
        }

        Map<Integer, ArrayList<Integer>> m2 = new TreeMap<>();
        bt.getVerticalOrder(head, 0, m2);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : m2.entrySet()) {
            System.out.println(entry.getValue());
        }

//		TreeMap<Integer, Integer> m2 = new TreeMap<>();
//		bt.getVerticalOrderSum(head, 0, m2);
//		for (Integer key : m2.keySet()) {
//			System.out.print(m2.get(key) + " ");
//		}
        System.out.println();

        System.out.println(bt.recursiveSearch(head, 11));

        System.out.println(bt.getLevelOfNode(head, 11, 1));

        System.out.println(bt.printBetweenLevels(head, 2, 3));

        System.out.println(bt.getMaximumWidth(head));

        System.out.println(bt.printSpiralOrder(head));
    }
}
