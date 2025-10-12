package com.dsaproblems.DSAProblems.binarytree01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathsOfGivenSum {

    public static void main(String[] args) {

    }


    public List<List<Integer>> findPathsWithSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.getVal());
        if (node.getLeft() == null && node.getRight() == null && target == node.getVal()) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.getLeft(), target - node.getVal(), path, result);
            dfs(node.getRight(), target - node.getVal(), path, result);
        }
        path.remove(path.size() - 1); // backtrack
    }
}
