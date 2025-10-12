package com.dsaproblems.DSAProblems.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

public class BinarySearchTreeService {

	public static TreeNode createBBST(List<Integer> input) {
		// return createBBSTv1(input, 0, input.size() - 1);
		return createBBSTv2(input, 0, input.size() - 1);
	}

	private static TreeNode createBBSTv2(List<Integer> input, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new TreeNode(input.get(start));
		}
		int mid = (end + start) / 2;
		TreeNode node = new TreeNode(input.get(mid));
		node.setLeft(createBBSTv1(input, start, mid - 1));
		node.setRight(createBBSTv1(input, mid + 1, end));
		return node;
	}

	private static TreeNode createBBSTv1(List<Integer> input, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (end + start) / 2;
		TreeNode node = new TreeNode(input.get(mid));
		node.setLeft(createBBSTv1(input, start, mid - 1));
		node.setRight(createBBSTv1(input, mid + 1, end));
		return node;
	}

	public TreeNode insertIntoBST(TreeNode node, int val) {
		if (node == null) {
			return new TreeNode(val);
		}
		if (node.getVal() >= val) {
			node.setLeft(insertIntoBST(node.getLeft(), val));
		} else {
			node.setRight(insertIntoBST(node.getRight(), val));
		}
		return node;
	}

	public TreeNode deleteNode(TreeNode node, int val) {
		if (node == null) {
			return node;
		}
		if (val > node.getVal()) {
			node.setRight(deleteNode(node.getRight(), val));
		} else if (val < node.getVal()) {
			node.setLeft(deleteNode(node.getLeft(), val));
		} else {
			if (node.getRight() == null) {
				return node.getLeft();
			}
			if (node.getLeft() == null) {
				return node.getRight();
			}
			if (node.getLeft() != null && node.getRight() != null) {
//				TreeNode successor = getSuccessor(node.getRight());
//				node.setVal(successor.getVal());
//				node.setRight(deleteNode(node.getRight(), successor.getVal()));

				TreeNode predecessor = getPredecessor(node.getLeft());
				node.setVal(predecessor.getVal());
				node.setLeft(deleteNode(node.getLeft(), predecessor.getVal()));
			}
		}
		return node;
	}

	private TreeNode getPredecessor(TreeNode node) {
		if (node == null) {
			return node;
		}
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}

	private TreeNode getSuccessor(TreeNode node) {
		if (node == null) {
			return node;
		}
		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

	public List<Integer> getFloorCeilOfBST(TreeNode node, int val) {
		int ceil = -1;
		int floor = -1;
		TreeNode temp = node;
		while (temp != null) {
			if (val < node.getVal()) {
				ceil = temp.getVal();
				temp = temp.getLeft();
			} else if (val > node.getVal()) {
				floor = temp.getVal();
				temp = temp.getRight();
			} else {
				floor = ceil = temp.getVal();
				break;
			}
		}
		List<Integer> ans = new ArrayList<>();
		ans.add(floor);
		ans.add(ceil);
		return ans;
	}

	public TreeNode generateBSTFromPreOrder(List<Integer> pre_order, int start, int end) {
		if (start > end) {
			return null;
		}
		TreeNode node = new TreeNode(pre_order.get(start));
		int idx = 0;
		for (int i = start + 1; i <= end; i++) {
			if (pre_order.get(i) > pre_order.get(start)) {
				idx = i;
				break;
			}
		}
		node.setLeft(generateBSTFromPreOrder(pre_order, start + 1, idx - 1));
		node.setRight(generateBSTFromPreOrder(pre_order, idx, end));
		return node;
	}

	public TreeNode generateBSTFromPostOrder(List<Integer> post_order, int start, int end) {
		if (start > end) {
			return null;
		}
		TreeNode node = new TreeNode(post_order.get(end));
		int idx = 0;
		for (int i = start; i < end; i++) {
			if (post_order.get(i) > post_order.get(end)) {
				idx = i;
				break;
			}
		}
		node.setLeft(generateBSTFromPostOrder(post_order, start, idx - 1));
		node.setRight(generateBSTFromPostOrder(post_order, idx, end - 1));
		return node;
	}

}
