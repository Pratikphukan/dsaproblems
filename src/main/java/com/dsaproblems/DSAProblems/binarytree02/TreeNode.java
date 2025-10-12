package com.dsaproblems.DSAProblems.binarytree02;

//public class TreeNode {
//	Node node;
//	int height;
//
//	public TreeNode(Node node, int h) {
//		this.node = node;
//		this.height = h;
//	}
//}
/*
 * hierarchical data structure, not linear
 * trie: efficient way to search in a string
 * suggestions in a dating application is an application of tree
 * deque can be developed using a doubly linked list
 * no way to go from child to a parent, connections are edges
 * if there are n nodes, then there will be n-1 edges
 * height of a node: distance(no of edges) from the given node to the farthest reachable leaf node
 * depth of a node: distance(no of edges) from the root node to the given node
 * height of a binary tree: distance from the root to the farthest reachable leaf node
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int val) {
        this.val = val;
        this.height = 0;
    }
}
