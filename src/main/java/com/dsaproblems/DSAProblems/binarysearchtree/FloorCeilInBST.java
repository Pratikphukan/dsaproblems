package com.dsaproblems.DSAProblems.binarysearchtree;

import com.dsaproblems.DSAProblems.binarytree01.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FloorCeilInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);

        ArrayList<Integer> B = new ArrayList<>(List.of(4, 19));

        System.out.println(findFloorCeilInBST(root, B));

    }

    private static ArrayList<ArrayList<Integer>> findFloorCeilInBST(TreeNode A, ArrayList<Integer> B) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (A == null) {
            return ans;
        }
        for (Integer num : B) {
            ans.add(getFloorCeil(A, num));
        }
        return ans;
    }

    private static ArrayList<Integer> getFloorCeil(TreeNode A, Integer val) {
        int ceil = -1, floor = -1;
        ArrayList<Integer> ans = new ArrayList<>();
        while (A != null) {
            if (A.val > val) {
                ceil = A.val;
                A = A.left;
            } else if (A.val < val) {
                floor = A.val;
                A = A.right;
            } else {
                ceil = floor = A.val;
                break;
            }
        }
        ans.add(floor);
        ans.add(ceil);
        return ans;
    }
}
