package com.dsaproblems.DSAProblems.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

public class BSTClient {

	public static void main(String[] args) {
		BinarySearchTreeService bst = new BinarySearchTreeService();
		TreeNode root = new TreeNode(8);
		bst.insertIntoBST(root, 3);
		bst.insertIntoBST(root, 10);
		bst.insertIntoBST(root, 6);
		bst.insertIntoBST(root, 14);
		bst.insertIntoBST(root, 1);
		bst.insertIntoBST(root, 4);
		bst.insertIntoBST(root, 7);
		bst.insertIntoBST(root, 13);

		bst.deleteNode(root, 4);

		System.out.println(bst.getFloorCeilOfBST(root, 5));

		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 10));
		TreeNode root1 = BinarySearchTreeService.createBBST(input);

		// 10, 4, 2, 8, 5, 9, 15, 12, 20
		List<Integer> pre_order = new ArrayList<>(Arrays.asList(1, 5, 6, 4));
		TreeNode root2 = bst.generateBSTFromPreOrder(pre_order, 0, pre_order.size() - 1);

		List<Integer> post_order = new ArrayList<>(Arrays.asList(2, 5, 9, 8, 4, 12, 20, 15, 10));
		TreeNode root3 = bst.generateBSTFromPostOrder(pre_order, 0, pre_order.size() - 1);
		
		
	}

}
