package com.dsaproblems.DSAProblems.binarytree01;

import java.util.List;
import java.util.Map;

public interface BinaryTreeService {

    Integer getHeightOfTreev1(TreeNode head);

    int getHeightOfTreev2(TreeNode head);

    void getPreorderTraversal(List<Integer> traversal, TreeNode head);

    void getInorderTraversal(List<Integer> traversal, TreeNode head);

    void getPostorderTraversal(List<Integer> traversal, TreeNode head);

    int getHeightOfTree(TreeNode head);

    int getNumberOfNodes(TreeNode head);

    List<Integer> getIterativePreorderTraversal(TreeNode head);

    List<Integer> getIterativePostorderTraversal(TreeNode head);

    Integer getNumberOfNodesv1(TreeNode head);

    Boolean recursiveSearch(TreeNode head, int data);

    TreeNode buildTreeUsingPreIn(List<Integer> pre, List<Integer> in, Map<Integer, Integer> map, int sin, int ein,
                                 int spr, int epr);

    TreeNode buildTreeUsingPosIn(List<Integer> pos, List<Integer> in, Map<Integer, Integer> map, int sin, int ein,
                                 int spo, int epo);

    List<Integer> getIterativeLevelorderTraversal(TreeNode head);

    List<List<Integer>> getIterativeLevelorderTraversalAlternative(TreeNode head);

    List<List<Integer>> getIterativeLevelorderTraversalAlternative1(TreeNode head);
}
