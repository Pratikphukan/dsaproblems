package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructBTInPos {

    public static void main(String[] args) {
        ArrayList<Integer> IN = new ArrayList<>(List.of(4, 8, 2, 5, 1, 6, 3, 7));
        ArrayList<Integer> POS = new ArrayList<>(List.of(8, 4, 5, 2, 6, 7, 3, 1));

        System.out.println(buildBTPosInv1(POS, IN));
        System.out.println(buildBTPosInv2(POS, IN));
    }

    private static TreeNode buildBTPosInv2(ArrayList<Integer> pos, ArrayList<Integer> in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) map.put(in.get(i), i);
        int[] postIndex = new int[]{pos.size() - 1}; // mutable container for current postorder index
        return buildBTPosInv2(pos, map, 0, in.size() - 1, postIndex);
    }

    private static TreeNode buildBTPosInv2(ArrayList<Integer> pos,
                                           Map<Integer, Integer> map,
                                           int inStart, int inEnd, int[] postIndex) {
        if (inStart > inEnd) return null;
        int rootVal = pos.get(postIndex[0]--);
        TreeNode root = new TreeNode(rootVal);
        int idx = map.get(rootVal);
        // consume postorder from end: root, then right subtree, then left subtree
        root.right = buildBTPosInv2(pos, map, idx + 1, inEnd, postIndex);
        root.left = buildBTPosInv2(pos, map, inStart, idx - 1, postIndex);
        return root;
    }

    private static TreeNode buildBTPosInv1(ArrayList<Integer> pos, ArrayList<Integer> in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            int item = in.get(i);
            map.put(item, i);
        }
        int size = in.size() - 1; // size of both the arrays will be the same
        return buildBTPosInv1(pos, in, map, 0, size, 0, size);
    }

    private static TreeNode buildBTPosInv1(ArrayList<Integer> pos,
                                           ArrayList<Integer> in,
                                           Map<Integer, Integer> map,
                                           int sin, int ein, int spo, int epo) {
        if (sin > ein) {
            return null;
        }
        TreeNode head = new TreeNode(pos.get(epo)); // last node is the root in the case of post order
        // search for the head in the inorder array
        int idx = map.get(head.val);
        int count = idx - sin; // count of elements in the left subtree
        head.left = buildBTPosInv1(pos, in, map, sin, idx - 1, spo, spo + count - 1);
        head.right = buildBTPosInv1(pos, in, map, idx + 1, ein, spo + count, epo - 1);
        return head;
    }
}
