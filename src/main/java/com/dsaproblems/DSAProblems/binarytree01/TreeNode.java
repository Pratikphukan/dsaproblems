package com.dsaproblems.DSAProblems.binarytree01;

import lombok.Data;
import lombok.NonNull;

@Data
public class TreeNode {

    @NonNull
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this(val);
        this.left = left;
        this.right = right;
    }
}
