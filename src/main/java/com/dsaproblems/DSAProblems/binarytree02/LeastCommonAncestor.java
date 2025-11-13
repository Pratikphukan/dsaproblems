package com.dsaproblems.DSAProblems.binarytree02;

public class LeastCommonAncestor {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(7);
        head.right = new TreeNode(5);
        head.left.left = new TreeNode(10);
        head.left.right = new TreeNode(6);
        head.left.right.left = new TreeNode(3);
        head.left.right.right = new TreeNode(11);
        head.right.right = new TreeNode(9);
        head.right.right.left = new TreeNode(4);

        System.out.println(lowestCommonAncestorv1(head, 10, 11));

        System.out.println(lowestCommonAncestorv2(head, 10, 11));

//        com.dsaproblems.DSAProblems.bt.Node lca = bt.lowestCommonAncestor2(head, 10, 11);
//
//        if (!(bt.recursiveSearch(head, 10) && bt.recursiveSearch(head, 11))) {
//            System.out.println("No LCA possible");
//        }
//
//        if (lca == null) {
//            System.out.println("No LCA possible");
//        } else {
//            System.out.println(lca.data);
//        }
    }

    //lowestCommonAncestorv1 is already optimal in time and space asymptotically: it runs in O(n) time and uses O(h) stack space (h = tree height)
    public static TreeNode lowestCommonAncestorv1(TreeNode head, int n1, int n2) {
        if (head == null || head.val == n1 || head.val == n2) { // if one of them is found, then it will return the node
            return head;
        }
        TreeNode leftlca = lowestCommonAncestorv1(head.left, n1, n2);
        TreeNode rightlca = lowestCommonAncestorv1(head.right, n1, n2);
        if (leftlca != null && rightlca != null) {
            return head;
        } else if (leftlca != null) {
            return leftlca;
        } else {
            return rightlca;
        }
    }

    public static TreeNode lowestCommonAncestorv2(TreeNode head, int n1, int n2) {
        if (head == null || head.val == n1 || head.val == n2) {
            return head;
        }
        boolean checkn1 = recursiveSearch(head.left, n1);
        boolean checkn2 = recursiveSearch(head.left, n2);
        if (checkn1 && checkn2) {
            return lowestCommonAncestorv2(head.left, n1, n2);
        } else if (checkn1 || checkn2) {
            return head;
        } else {
            return lowestCommonAncestorv2(head.right, n1, n2);
        }
    }

    public static boolean recursiveSearch(TreeNode head, int key) {
        if (head == null) {
            return false;
        }
        if (key == head.val) {
            return true;
        }
        return recursiveSearch(head.left, key) || recursiveSearch(head.right, key);
    }

    static boolean checkn1 = false;
    static boolean checkn2 = false;

    public static TreeNode lowestCommonAncestorv3(TreeNode head, int n1, int n2) {
        TreeNode lca = lowestCommonAncestorUtil(head, n1, n2);
        if (checkn1 && checkn2) {
            return lca;
        }
        return null;
    }

    public static TreeNode lowestCommonAncestorUtil(TreeNode head, int n1, int n2) {
        if (head == null) {
            return null;
        }
        if (head.val == n1) {
            checkn1 = true;
            return head;
        }
        if (head.val == n2) {
            checkn2 = true;
            return head;
        }
        TreeNode leftlca = lowestCommonAncestorUtil(head.left, n1, n2);
        TreeNode rightlca = lowestCommonAncestorUtil(head.right, n1, n2);
        if (leftlca != null && rightlca != null) {
            return head;
        }
        return leftlca != null ? leftlca : rightlca;
    }
}
