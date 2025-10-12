package com.dsaproblems.DSAProblems.binarytree01;

import lombok.Data;
import lombok.NonNull;

@Data
public class TreeNode {

    @NonNull
    public Integer val;
    public TreeNode left;
    public TreeNode right;

}
