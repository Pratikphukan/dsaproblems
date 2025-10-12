package com.dsaproblems.DSAProblems.bt;

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
	int data;
	TreeNode left;
	TreeNode right;
	int height;

	public TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.height = 0;
	}
}
