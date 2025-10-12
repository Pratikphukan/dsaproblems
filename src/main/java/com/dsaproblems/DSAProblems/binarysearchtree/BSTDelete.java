package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

public class BSTDelete {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(21);
        root.right.right.right = new TreeNode(50);

        System.out.println(deleteNodeFromBSTv2(root, 7));
    }

    //Time Complexity: O(h), where h is the height of the tree. In a balanced BST, h = O(log n); in the worst case (skewed tree), h = O(n).
    //Space Complexity: O(1) (iterative, no recursion stack)
    private static TreeNode deleteNodeFromBSTv2(TreeNode root, int val) {
        TreeNode curr = root, parent = null;

        // 1) Find node and its parent
        while (curr != null && curr.val != val) {
            parent = curr;
            if (curr.val > val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (curr == null) return root; // not found

        // 2) If node has two children, copy predecessor's value into curr,
        //    then delete that predecessor node (which has at most one child).
        if (curr.left != null && curr.right != null) {
            TreeNode predParent = curr, pred = curr.left;
            while (pred.right != null) {
                predParent = pred;
                pred = pred.right;
            }
            // copy predecessor value
            curr.val = pred.val;
            // now delete 'pred' instead
            curr = pred;
            parent = predParent;
        }

        // 3) Now 'curr' has at most one child. Splice it out.
        TreeNode child = (curr.left != null) ? curr.left : curr.right;

        if (parent == null) {
            // deleting the root
            return child;
        } else if (parent.left == curr) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return root;
    }

    //Time complexity: O(h), where h is the height of the tree.
    //Space complexity: O(h) due to recursion stack.
    private static TreeNode deleteNodeFromBSTv1(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            TreeNode predecessor = getPredecessor(node.left); //delete the maximum of the LST
            node.val = predecessor.val;
            node.left = deleteNodeFromBSTv1(node.left, predecessor.val);
//            TreeNode successor = getSuccessor(node.right); //delete the minimum of the RST
//            node.setVal(successor.val);
//            node.setRight(deleteNodeFromBSTv1(node.right, successor.val));
        } else if (node.val > val) {
            node.left = deleteNodeFromBSTv1(node.left, val);
        } else {
            node.right = deleteNodeFromBSTv1(node.right, val);
        }
        return node;
    }

    private static TreeNode getSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private static TreeNode getPredecessor(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
}
