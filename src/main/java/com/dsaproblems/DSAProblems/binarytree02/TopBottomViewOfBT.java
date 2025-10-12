package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TopBottomViewOfBT {

	static class TreeNode {
		Integer val;
		TreeNode left;
		TreeNode right;
		Integer breadth;

		public TreeNode(int val) {
			this.val = val;
			this.breadth = 0;
		}
	}

	public static void main(String[] args) {
		TreeNode head = new TreeNode(2);
		head.left = new TreeNode(7);
		head.left.left = new TreeNode(2);
		head.left.right = new TreeNode(6);
		head.left.right.right = new TreeNode(11);
		head.left.right.left = new TreeNode(5);
		head.right = new TreeNode(5);
		head.right.right = new TreeNode(9);
		// head.right.right.left = new TreeNode(4);

		System.out.println(getTopViewOfBTv1(head));
		System.out.println(getTopViewOfBTv2(head));
		System.out.println(getBottomViewOfBTv1(head));
	}

	private static ArrayList<Integer> getBottomViewOfBTv1(TreeNode head) {
		Map<Integer, Integer> map = new TreeMap<>();
		if (head == null) {
			return new ArrayList<>(map.values());
		}
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.addLast(head);
		TreeNode temp = null;
		Integer currbreadth = null;
		while (!queue.isEmpty()) {
			temp = queue.pollFirst();
			currbreadth = temp.breadth; // get the parent height and assign the height to the children
			map.put(currbreadth, temp.val);
			if (temp.left != null) {
				temp.left.breadth = currbreadth - 1;
				queue.add(temp.left);
			}
			if (temp.right != null) {
				temp.right.breadth = currbreadth + 1;
				queue.add(temp.right);
			}
		}
		return new ArrayList<>(map.values());
	}

	private static ArrayList<Integer> getTopViewOfBTv2(TreeNode head) {
		Map<Integer, Integer> map = new TreeMap<>();
		if (head == null) {
			return new ArrayList<>(map.values());
		}
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(head);
		TreeNode temp = null;
		Integer currbreadth = null;
		while (!queue.isEmpty()) {
			temp = queue.pollFirst();
			currbreadth = temp.breadth;
			if (!map.containsKey(currbreadth)) {
				map.put(currbreadth, temp.val);
			}
			if (temp.left != null) {
				temp.left.breadth = currbreadth - 1;
				queue.addLast(temp.left);
			}
			if (temp.right != null) {
				temp.right.breadth = currbreadth + 1;
				queue.addLast(temp.right);
			}

		}
		return new ArrayList<>(map.values());
	}

	// not working code
	private static List<Integer> getTopViewOfBTv1(TreeNode head) {
		List<Integer> ans = new ArrayList<>();
		if (head == null) {
			return ans;
		}
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(head);
		TreeNode temp = null;
		while (!queue.isEmpty()) {
			int size = queue.size();
			ans.add(queue.getFirst().val);
			if (queue.size() > 1) {
				ans.add(queue.getLast().val);
			}
			for (int i = 0; i < size; i++) {
				temp = queue.pollFirst();
				if (temp.left != null) {
					queue.addLast(temp.left);
				}
				if (temp.right != null) {
					queue.addLast(temp.right);
				}
			}
		}
		return ans;
	}

}
