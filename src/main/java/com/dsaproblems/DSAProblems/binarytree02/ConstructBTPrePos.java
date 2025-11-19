package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructBTPrePos {

    public static void main(String[] args) {
        ArrayList<Integer> PRE = new ArrayList<>(List.of(1, 2, 4, 5, 3, 6, 7));
        ArrayList<Integer> POS = new ArrayList<>(List.of(4, 5, 2, 6, 7, 3, 1));
        System.out.println(buildBTPrePosv1(PRE, POS));
    }

    private static TreeNode buildBTPrePosv1(ArrayList<Integer> pre, ArrayList<Integer> pos) {
        Map<Integer, Integer> posIdx = new HashMap<>();
        for (int i = 0; i < pos.size(); i++) posIdx.put(pos.get(i), i);
        return build(pre, pos, posIdx, 0, pre.size() - 1, 0, pos.size() - 1);
    }

    private static TreeNode build(ArrayList<Integer> pre,
                                  ArrayList<Integer> pos,
                                  Map<Integer, Integer> posIdx,
                                  int spre, int epre, int spos, int epos) {
        if (spre > epre) return null;
        TreeNode root = new TreeNode(pre.get(spre));
        if (spre == epre) return root; // leaf node

        // left subtree root is next in preorder
        int leftRootVal = pre.get(spre + 1);
        int leftRootPos = posIdx.get(leftRootVal);

        // size of left subtree in nodes
        int leftSize = leftRootPos - spos + 1;

        // preorder ranges
        int leftPreStart = spre + 1;
        int leftPreEnd = spre + leftSize;
        int rightPreStart = leftPreEnd + 1;
        int rightPreEnd = epre;

        // postorder ranges
        int leftPostStart = spos;
        int leftPostEnd = leftRootPos;
        int rightPostStart = leftRootPos + 1;
        int rightPostEnd = epos - 1; // exclude root

        root.left = build(pre, pos, posIdx, leftPreStart, leftPreEnd, leftPostStart, leftPostEnd);
        root.right = build(pre, pos, posIdx, rightPreStart, rightPreEnd, rightPostStart, rightPostEnd);
        return root;
    }
}
