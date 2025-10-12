package com.dsaproblems.DSAProblems.bt;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeApp1 {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		List<Integer> pre = List.of(1, 2, 4, 8, 5, 3, 6, 7);
		ArrayList<Integer> PRE = new ArrayList<>(pre);
		List<Integer> in = List.of(4, 8, 2, 5, 1, 6, 3, 7);
		ArrayList<Integer> IN = new ArrayList<>(in);
		List<Integer> pos = List.of(8, 4, 5, 2, 6, 7, 3, 1);
		ArrayList<Integer> POS = new ArrayList<>(pos);
		
		Node head1 = bt.buildBinaryTree1(PRE, IN);
		System.out.println(bt.iterativePostorderTraversal(head1));
		
		Node head2 = bt.buildBinaryTree2(POS, IN);
		System.out.println(bt.iterativePreorderTraversal(head2));
		
		Node head3 = bt.buildBinaryTreePart1(PRE, IN);
		System.out.println(bt.iterativePostorderTraversal(head3));
		
		BinaryTree a = new BinaryTree();
		head1 = new Node(2);
		head1.left = new Node(7);
		head1.right = new Node(5);
		head1.left.left = new Node(2);
		head1.left.right = new Node(6);
		head1.left.right.left = new Node(5);
		head1.left.right.right = new Node(11);
		head1.right.right = new Node(9);
		head1.right.right.left = new Node(4);
		
		head2 = new Node(2);
		head2.left = new Node(7);
		head2.right = new Node(5);
		head2.left.left = new Node(2);
		head2.left.right = new Node(6);
		head2.left.right.left = new Node(3);
		head2.left.right.right = new Node(11);
		head2.right.right = new Node(9);
		head2.right.right.left = new Node(4);
		
		System.out.println(a.checkIdenticalTrees(head1, head2));
		
		System.out.println(a.checkIdenticalStructure(head1, head2));
		
		System.out.println(a.checkMirrorTrees(head1, head2));
		
		System.out.println(a.checkMirrorStructure(head1, head2));
	}
}
