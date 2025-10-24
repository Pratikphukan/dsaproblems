package com.dsaproblems.DSAProblems.binarytree02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructBTPreIn {

    public static void main(String[] args) {
        ArrayList<Integer> PRE = new ArrayList<>(List.of(1, 2, 4, 8, 5, 3, 6, 7));
        ArrayList<Integer> IN = new ArrayList<>(List.of(4, 8, 2, 5, 1, 6, 3, 7));
        System.out.println(buildBTPreInv1(PRE, IN));
        System.out.println(buildBTPreInv2(PRE, IN));
    }

    //Use a single mutable preorder index (avoid passing spr/epr) and the inorder index map to
    // build the tree in one pass — O(n) time and O(n) extra space for the map plus recursion
    // stack.
    private static TreeNode buildBTPreInv2(ArrayList<Integer> pre, ArrayList<Integer> in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            map.put(in.get(i), i);
        }
        int[] preIndex = new int[]{0}; // mutable container for current preorder index
        return buildBTPreInv2(pre, 0, in.size() - 1, preIndex, map);
    }

    private static TreeNode buildBTPreInv2(ArrayList<Integer> pre,
                                           int inStart,
                                           int inEnd,
                                           int[] preIndex,
                                           Map<Integer, Integer> map) {
        if (inStart > inEnd) return null;
        int rootVal = pre.get(preIndex[0]++);
        TreeNode root = new TreeNode(rootVal);
        int idx = map.get(rootVal);
        root.left = buildBTPreInv2(pre, inStart, idx - 1, preIndex, map);
        root.right = buildBTPreInv2(pre, idx + 1, inEnd, preIndex, map);
        return root;
    }

    private static TreeNode buildBTPreInv1(ArrayList<Integer> pre, ArrayList<Integer> in) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            int item = in.get(i);
            map.put(item, i);
        }
        int size = in.size() - 1; // size of both the arrays will be the same
        return buildTreeUsingPreIn(pre, in, map, 0, size, 0, size);
    }

    private static TreeNode buildTreeUsingPreIn(ArrayList<Integer> pre,
                                                ArrayList<Integer> in,
                                                HashMap<Integer, Integer> map,
                                                int sin, int ein, int spr, int epr) {
        if (sin > ein) {
            return null;
        }
        TreeNode head = new TreeNode(pre.get(spr)); // root will be the start of the preorder array
        // search for the head in the inorder array
        int idx = map.get(head.val); // index of the root in the inorder array
        int count = idx - sin; // count of elements in the left subtree
        head.left = buildTreeUsingPreIn(pre, in, map, sin, idx - 1, spr + 1, spr + count);
        head.right = buildTreeUsingPreIn(pre, in, map, idx + 1, ein, spr + count + 1, epr);
        return head;
    }
}
