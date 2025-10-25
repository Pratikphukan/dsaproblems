package com.dsaproblems.DSAProblems.binarytree02;

import java.util.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(7);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(6);
        head.left.right.right = new TreeNode(11);
        head.left.right.left = new TreeNode(5);
        head.right = new TreeNode(5);
        head.right.right = new TreeNode(9);

        System.out.println(levelOrderTraversalv1(head));

        System.out.println(iterativeLevelOrderTraversalv1(head));
        System.out.println(iterativeLevelOrderTraversalv2(head));

        System.out.println(getIterativeLevelorderTraversalZigzag(head));
        System.out.println(getIterativeLevelorderTraversalZigzagv1(head));

        System.out.println(getBottomUpLOTv1(head));
        System.out.println(getBottomUpLOTv2(head));

        System.out.println(understandSomething());
    }

    // Java generics are invariant: a List<ArrayList<Integer>> is not a
    // List<List<Integer>> even though ArrayList<Integer> implements
    // List<Integer>. The method return type must be a subtype of
    // List<List<Integer>>. Constructions where the outer generic parameter
    // matches (List<List<Integer>> or ArrayList<List<Integer>>) are valid;
    // ones where it doesn't (ArrayList<ArrayList<Integer>> or
    // List<ArrayList<Integer>>) are not.

    // "Invariant" means: for a generic type G<T>, even if S is a subtype of T,
    // G<S> is NOT a subtype of G<T>. Java generics do not automatically
    // follow the subtype relation of their type arguments.
    private static List<List<Integer>> understandSomething() {
        List<List<Integer>> ans = new ArrayList<>();//this works
        return ans;

//        ArrayList<ArrayList<Integer>> ans = new ArrayList<>(); //this does not work
//        return ans;

//        ArrayList<List<Integer>> ans = new ArrayList<>(); //this works
//        return ans;

//        List<ArrayList<Integer>> ans = new ArrayList<>(); //this does not work
//        return ans;
    }

    private static List<List<Integer>> getBottomUpLOTv2(TreeNode head) {
        if (head == null) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> aux = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.removeFirst();
                aux.add(temp.val);
                if (temp.left != null) q.addLast(temp.left);
                if (temp.right != null) q.addLast(temp.right);
            }
            ans.add(aux);
        }
        Collections.reverse(ans);
        return ans;
    }

    //working code
    //time O(N) (each node visited once) and space O(N) (queue + result store)
    private static List<List<Integer>> getBottomUpLOTv1(TreeNode head) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (head == null) return ans;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            List<Integer> aux = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.removeFirst();
                aux.add(temp.val);
                if (temp.left != null) q.addLast(temp.left);
                if (temp.right != null) q.addLast(temp.right);
            }
            ans.addFirst(aux);
        }
        return ans;
    }

    //working code
    public static List<List<Integer>> getIterativeLevelorderTraversalZigzagv1(TreeNode head) {
        List<List<Integer>> ans = new ArrayList<>();
        if (head == null) return ans;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> aux = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (leftToRight)
                    aux.addLast(temp.val);
                else
                    aux.addFirst(temp.val);
                if (temp.left != null) queue.addLast(temp.left);
                if (temp.right != null) queue.addLast(temp.right);
            }
            ans.add(aux);
            leftToRight = !leftToRight;
        }
        return ans;
    }

    private static List<List<Integer>> levelOrderTraversalv1(TreeNode head) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderTraversalv1(head, 0, res);
        return res;
    }

    private static void levelOrderTraversalv1(TreeNode head, int level, List<List<Integer>> res) {
        if (head == null) return;

        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(head.val);

        levelOrderTraversalv1(head.left, level + 1, res);
        levelOrderTraversalv1(head.right, level + 1, res);
    }


    public static List<List<Integer>> getIterativeLevelorderTraversalZigzag(TreeNode head) {
        List<List<Integer>> ans = new ArrayList<>();
        if (head == null) return ans;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            List<Integer> aux = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (leftToRight)
                    aux.add(temp.val);
                else
                    aux.add(0, temp.val);
                if (temp.left != null) queue.addLast(temp.left);
                if (temp.right != null) queue.addLast(temp.right);
            }
            ans.add(aux);
            leftToRight = !leftToRight; // toggle the direction for next level
        }
        return ans;
    }

    // SC is O(breadth of tree or max width of tree), TC: O(N)
    // worst case SC will be a complete tree where all the levels are completely filled
    // except the last level
    // nodes in the last level are left aligned
    // 2^0+2^1+2^2+2^3=2^4-1/2^(h+1)-1
    // in the last level we are having 2^h nodes in a complete binary tree
    // No of nodes in a complete BT where the last level is completely filled =
    // 2^(h+1)-1
    // h = log(n+1)-1
    public static ArrayList<Integer> iterativeLevelOrderTraversalv1(TreeNode head) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (head == null) return ans;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            TreeNode temp = q.removeFirst(); // The element is removed from the beginning or head of the linked list
            ans.add(temp.val);
            if (temp.left != null) q.addLast(temp.left);
            if (temp.right != null) q.addLast(temp.right);
        }
        return ans;
    }

    //Time complexity: O(N), where N is the number of nodes (each node is visited once).
    //Space complexity: O(W), where W is the maximum width of the tree (queue size at the widest level).
    public static ArrayList<ArrayList<Integer>> iterativeLevelOrderTraversalv2(TreeNode head) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (head == null) return ans;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(head);
        while (!q.isEmpty()) {
            ArrayList<Integer> aux = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.removeFirst();
                aux.add(temp.val);
                if (temp.left != null) q.addLast(temp.left);
                if (temp.right != null) q.addLast(temp.right);
            }
            ans.add(aux);
        }
        return ans;
    }
}
