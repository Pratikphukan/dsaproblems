package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

public class NodeDistanceCinBT {

    static class Pair {
        TreeNode node;      // The current tree node
        int distance;       // Distance from the target node

        // Constructor for the pair with node and its associated distance.
        Pair(TreeNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);              // Root node with value 3
        root.left = new TreeNode(5);                  // Left child of root with value 5
        root.right = new TreeNode(1);                 // Right child of root with value 1
        root.left.left = new TreeNode(6);             // Left child of node 5 with value 6
        root.left.right = new TreeNode(2);            // Right child of node 5 with value 2
        root.right.left = new TreeNode(0);            // Left child of node 1 with value 0
        root.right.right = new TreeNode(8);           // Right child of node 1 with value 8
        root.left.right.left = new TreeNode(7);       // Left child of node 2 with value 7
        root.left.right.right = new TreeNode(4);      // Right child of node 2 with value 4

        System.out.println(getNodesWithDistCFromBv1(root, 7, 2));
    }

    private static ArrayList<Integer> getNodesWithDistCFromBv1(TreeNode A, int B, int C) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null) return result;
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(A, null, parentMap);
        TreeNode targetNode = findTargetNode(A, B);
        if (targetNode == null) return result;
        return bfsDistanceNodes(targetNode, parentMap, C);
    }

    private static ArrayList<Integer> bfsDistanceNodes(TreeNode target, Map<TreeNode, TreeNode> parentMap, int C) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Pair> queue = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.addLast(new Pair(target, 0));
        visited.add(target);
        while (!queue.isEmpty()) {
            Pair current = queue.pollFirst();
            TreeNode currNode = current.node;
            int currDist = current.distance;
            // If the current distance matches C, add the node's value to result.
            if (currDist == C) result.add(currNode.val);
            // If current distance is already C, no need to traverse its neighbors further.
            if (currDist >= C) continue;
            if (currNode.left != null && !visited.contains(currNode.left)) {
                visited.add(currNode.left);
                queue.addLast(new Pair(currNode.left, currDist + 1));
            }
            if (currNode.right != null && !visited.contains(currNode.right)) {
                visited.add(currNode.right);
                queue.addLast(new Pair(currNode.right, currDist + 1));
            }

            // Get the parent from the parentMap.
            TreeNode parent = parentMap.get(currNode);
            if (parent != null && !visited.contains(parent)) {
                visited.add(parent);
                queue.addLast(new Pair(parent, currDist + 1));
            }
        }
        return result;
    }

    //A non-recursive (iterative) approach using a stack avoids deep recursion and is easier to read
    private static TreeNode findTargetNode(TreeNode A, int B) {
        if (A == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(A);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            if (curr.val == B) return curr;
            if (curr.right != null) stack.addFirst(curr.right);
            if (curr.left != null) stack.addFirst(curr.left);
        }
        return null;
    }

    private static void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) return;
        parentMap.put(node, parent);
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }
}
