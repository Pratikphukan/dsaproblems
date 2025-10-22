package com.dsaproblems.DSAProblems.binarytree01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeClient {

    public static void main(String[] args) {
        BinaryTreeService binaryTreeService = new BinaryTreeServiceImpl();
        TreeNode head = new TreeNode(10);
        head.setLeft(new TreeNode(5));
        head.getLeft().setLeft(new TreeNode(2));
        head.getLeft().setRight(new TreeNode(7));
        head.getLeft().getRight().setLeft(new TreeNode(6));
        head.getLeft().getRight().setRight(new TreeNode(8));
        head.setRight(new TreeNode(20));
        head.getRight().setRight(new TreeNode(22));
        head.getRight().getRight().setLeft(new TreeNode(21));
        head.getRight().getRight().setRight(new TreeNode(50));

        System.out.println(binaryTreeService.getHeightOfTreev1(head));
        System.out.println(binaryTreeService.getHeightOfTreev2(head));

        List<Integer> traversal = new ArrayList<>();
        binaryTreeService.getPreorderTraversal(traversal, head);
        System.out.println(traversal);

        traversal.clear();
        binaryTreeService.getInorderTraversal(traversal, head);
        System.out.println(traversal);

        traversal.clear();
        binaryTreeService.getPostorderTraversal(traversal, head);
        System.out.println(traversal);

        System.out.println(binaryTreeService.getHeightOfTree(head));
        System.out.println(binaryTreeService.getNumberOfNodes(head));

        System.out.println(binaryTreeService.getIterativePostorderTraversal(head));

        System.out.println(binaryTreeService.getIterativePreorderTraversal(head));

        System.out.println(binaryTreeService.getNumberOfNodesv1(head));

        System.out.println(binaryTreeService.recursiveSearch(head, 22));

        List<Integer> pre = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 5, 3, 6, 7));
        List<Integer> in = new ArrayList<>(Arrays.asList(4, 8, 2, 5, 1, 6, 3, 7));
        List<Integer> pos = new ArrayList<>(Arrays.asList(8, 4, 5, 2, 6, 7, 3, 1));

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            int item = in.get(i);
            map.put(item, i);
        }

        TreeNode head1 = binaryTreeService.buildTreeUsingPreIn(pre, in, map, 0, in.size() - 1, 0, pre.size() - 1);
        System.out.println(binaryTreeService.getIterativePostorderTraversal(head1));
        System.out.println(binaryTreeService.getIterativePreorderTraversal(head1));

        TreeNode head2 = binaryTreeService.buildTreeUsingPosIn(pos, in, map, 0, in.size() - 1, 0, pos.size() - 1);
        System.out.println(binaryTreeService.getIterativePostorderTraversal(head2));
        System.out.println(binaryTreeService.getIterativePreorderTraversal(head2));
    }

}
