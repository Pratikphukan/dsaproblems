package com.dsaproblems.DSAProblems.bt;

public class BinaryTreeApp2 {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		TreeNode head = new TreeNode(2);
		head.left = new TreeNode(7);
		head.right = new TreeNode(5);
		head.left.right = new TreeNode(6);
		head.right.right = new TreeNode(9);
		head.left.right.left = new TreeNode(5);
		head.left.right.right = new TreeNode(11);
		head.left.right.left.left = new TreeNode(10);
		
		System.out.println(bt.topView(head));
		
		System.out.println(bt.bottomView(head));
	}

}
