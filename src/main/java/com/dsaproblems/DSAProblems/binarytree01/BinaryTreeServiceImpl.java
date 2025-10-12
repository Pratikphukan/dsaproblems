package com.dsaproblems.DSAProblems.binarytree01;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dsaproblems.DSAProblems.bt.Node;

public class BinaryTreeServiceImpl implements BinaryTreeService {

    @Override
    public Integer getHeightOfTreev1(TreeNode node) { // considering edges to be height
        if (node == null) {
            return -1;
        }
        return Math.max(this.getHeightOfTreev1(node.getLeft()), this.getHeightOfTreev1(node.getRight())) + 1;
    }

    @Override
    public int getHeightOfTreev2(TreeNode node) { // considering nodes to be height
        if (node == null) {
            return 0;
        }
        return Math.max(this.getHeightOfTreev2(node.getLeft()), this.getHeightOfTreev2(node.getRight())) + 1;
    }

    // it is a DFS traversal
    // you are traveling one depth at a time
    // you are going from top to bottom
    // this algorithm works in a fail fast manner
    // TC is O(n) and SC is O(height of tree)
    @Override
    public void getPreorderTraversal(List<Integer> traversal, TreeNode root) {
        if (root != null) {
            traversal.add(root.getVal());
            getPreorderTraversal(traversal, root.getLeft());
            getPreorderTraversal(traversal, root.getRight());
        }
    }

    @Override
    public void getInorderTraversal(List<Integer> traversal, TreeNode root) {
        if (root != null) {
            getInorderTraversal(traversal, root.getLeft());
            traversal.add(root.getVal());
            getInorderTraversal(traversal, root.getRight());
        }
    }

    // it is a DFS traversal
    // you are traveling one depth at a time
    // you are going from bottom to top
    // this algorithm works in a fail fast manner
    // TC is O(n) and SC is O(height of tree)
    @Override
    public void getPostorderTraversal(List<Integer> traversal, TreeNode root) {
        if (root != null) {
            getPostorderTraversal(traversal, root.getLeft());
            getPostorderTraversal(traversal, root.getRight());
            traversal.add(root.getVal());
        }
    }

    public int getHeightOfTree(TreeNode node) { // considering edges to be height
        if (node == null) {
            return -1;
        }
        int left = getHeightOfTree(node.getLeft());
        int right = getHeightOfTree(node.getRight());
        return Math.max(left, right) + 1;
    }

    public int getNumberOfNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getNumberOfNodes(node.getLeft());
        int right = getNumberOfNodes(node.getRight());
        return left + right + 1;
    }

    @Override
    public List<Integer> getIterativePreorderTraversal(TreeNode node) {
        List<Integer> preorder = new ArrayList<>();
        if (node == null) {
            return preorder;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            preorder.add(temp.getVal());
            if (temp.getRight() != null) {
                stack.addFirst(temp.getRight());
            }
            if (temp.getLeft() != null) {
                stack.addFirst(temp.getLeft());
            }
        }
        return preorder;
    }

    @Override
    public List<Integer> getIterativePostorderTraversal(TreeNode node) {
        List<Integer> postorder = new ArrayList<>();
        if (node == null) {
            return postorder;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.addFirst(node);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pollFirst();
            postorder.add(0, temp.getVal());
            if (temp.getLeft() != null) {
                stack.addFirst(temp.getLeft());
            }
            if (temp.getRight() != null) {
                stack.addFirst(temp.getRight());
            }
        }
        return postorder;
    }

    // SC is O(breadth of tree)
    // worst case SC will be a complete where all the levels are completely filled
    // except the last level
    // nodes in the last level are left aligned
    // 2^0+2^1+2^2+2^3=2^4-1/2^(h+1)-1
    // in the last level we are having 2^h nodes in a complete binary tree
    // No of nodes in a completes BT where the last level is completely filled =
    // 2^(h+1)-1
    // h = log(n+1)-1
    @Override
    public List<Integer> getIterativeLevelorderTraversal(TreeNode node) {
        List<Integer> levelorder = new ArrayList<>();
        if (node == null) {
            return levelorder;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(node);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.pollFirst();
            levelorder.add(temp.getVal());
            if (temp.getLeft() != null) {
                queue.addLast(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.addLast(temp.getRight());
            }
        }
        return levelorder;
    }

    @Override
    public List<List<Integer>> getIterativeLevelorderTraversalAlternative(TreeNode head) {
        List<List<Integer>> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            List<Integer> aux = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                aux.add(temp.getVal());
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
            ans.add(aux);
        }
        return ans;
    }

    @Override
    public List<List<Integer>> getIterativeLevelorderTraversalAlternative1(TreeNode head) {
        List<List<Integer>> ans = new ArrayList<>();
        if (head == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            List<Integer> aux = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.pollFirst();
                if (leftToRight)
                    aux.add(temp.getVal());
                else
                    aux.add(0, temp.getVal());
                if (temp.getLeft() != null) queue.addLast(temp.getLeft());
                if (temp.getRight() != null) queue.addLast(temp.getRight());
            }
            ans.add(aux);
            leftToRight = !leftToRight; // toggle the direction for next level
        }
        return ans;
    }

    @Override
    public Integer getNumberOfNodesv1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight()) + 1;
    }

    @Override
    public Boolean recursiveSearch(TreeNode node, int data) { // following a preorder traversal
        if (node == null) {
            return false;
        }
        if (node.getVal() == data) {
            return true;
        }
        return recursiveSearch(node.getLeft(), data) || recursiveSearch(node.getRight(), data);
    }

    @Override
    public TreeNode buildTreeUsingPreIn(List<Integer> pre, List<Integer> in, Map<Integer, Integer> map, int sin,
                                        int ein, int spr, int epr) {
        if (sin > ein) {
            return null;
        }
        TreeNode head = new TreeNode(pre.get(spr));
        int idx = map.get(head.getVal());
        int count = idx - sin;
        head.setLeft(buildTreeUsingPreIn(pre, in, map, sin, idx - 1, spr + 1, spr + count));
        head.setRight(buildTreeUsingPreIn(pre, in, map, idx + 1, ein, spr + count + 1, epr));
        return head;
    }

    @Override
    public TreeNode buildTreeUsingPosIn(List<Integer> pos, List<Integer> in, Map<Integer, Integer> map, int sin,
                                        int ein, int spo, int epo) {
        if (sin > ein) {
            return null;
        }
        TreeNode head = new TreeNode(pos.get(epo));
        int idx = map.get(head.getVal());
        int count = idx - sin;
        head.setLeft(buildTreeUsingPosIn(pos, in, map, sin, idx - 1, spo, spo + count - 1));
        head.setRight(buildTreeUsingPosIn(pos, in, map, idx + 1, ein, spo + count, epo - 1));
        return head;
    }
}
