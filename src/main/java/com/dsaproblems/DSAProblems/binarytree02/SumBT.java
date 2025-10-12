package com.dsaproblems.DSAProblems.binarytree02;

public class SumBT {

    public static void main(String[] args) {
        SumBT solution = new SumBT();

        // Test case 1: Tree with a single leaf node.
        // Tree: 10
        // Expected Output: 1 (it is a Sum Binary Tree)
        TreeNode test1 = new TreeNode(10);
        System.out.println("Test Case 1: " + solution.solve(test1)); // Should output 1

        // Test case 2: Valid Sum Binary Tree.
        //      26
        //     /  \
        //    10   3
        //   / \    \
        //  4   6    3
        // Sum check: 26 == 10 + 3, 10 == 4 + 6, and leaf nodes are valid.
        // Expected Output: 1
        TreeNode test2 = new TreeNode(26);
        test2.left = new TreeNode(10);
        test2.right = new TreeNode(3);
        test2.left.left = new TreeNode(4);
        test2.left.right = new TreeNode(6);
        test2.right.right = new TreeNode(3);
        System.out.println("Test Case 2: " + solution.solve(test2)); // Should output 1

        System.out.println(solvev1(test2));

        // Test case 3: Invalid Sum Binary Tree.
        //       26
        //      /  \
        //    10    3
        //   /  \    \
        //  4    5    3
        // The left subtree at node with value 10: 4+5=9 which is not equal to 10.
        // Expected Output: 0
        TreeNode test3 = new TreeNode(26);
        test3.left = new TreeNode(10);
        test3.right = new TreeNode(3);
        test3.left.left = new TreeNode(4);
        test3.left.right = new TreeNode(5);
        test3.right.right = new TreeNode(3);
        System.out.println("Test Case 3: " + solution.solve(test3)); // Should output 0

        System.out.println(solvev1(test3));

        // Test case 4: Empty Tree.
        // Expected Output: 1 (Empty tree is considered a Sum Binary Tree)
        System.out.println("Test Case 4: " + solution.solve(null)); // Should output 1

        // Test case 5: A more complex tree.
        //       15
        //      /  \
        //     7    8
        //    / \  / \
        //   3  4 2  6
        // Check: 7 == 3 + 4, and 8 == 2 + 6, and 15 == 7 + 8.
        // Expected Output: 1
        TreeNode test5 = new TreeNode(15);
        test5.left = new TreeNode(7);
        test5.right = new TreeNode(8);
        test5.left.left = new TreeNode(3);
        test5.left.right = new TreeNode(4);
        test5.right.left = new TreeNode(2);
        test5.right.right = new TreeNode(6);
        System.out.println("Test Case 5: " + solution.solve(test5)); // Should output 1


        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        System.out.println(checkSumBinaryTree(root));
    }

    public static int checkSumBinaryTree(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 1;
        }
        int leftSum = getSumOfNodes(node.left);
        int rightSum = getSumOfNodes(node.right);
        if (node.val == (leftSum + rightSum) && checkSumBinaryTree(node.left) == 1
                && checkSumBinaryTree(node.right) == 1) {
            return 1;
        }
        return 0;
    }

    public static int getSumOfNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.val + getSumOfNodes(node.left) + getSumOfNodes(node.right);
    }

    //working code
    private static int solvev1(TreeNode A) {
        return checkSumTreev1(A) == -1 ? 0 : 1;
    }

    private static int checkSumTreev1(TreeNode A) {
        if (A == null) {
            return 0;
        }
        if (A.left == null && A.right == null) {
            return A.val;
        }
        int leftSum = checkSumTreev1(A.left);
        if (leftSum == -1) return -1;
        int rightSum = checkSumTreev1(A.right);
        if (rightSum == -1) return -1;
        if ((leftSum + rightSum) == A.val)
            return A.val + leftSum + rightSum;
        return -1;
    }

    // The function to check whether the given binary tree is a Sum Binary Tree.
    public int solve(TreeNode A) {
        // Call the helper function and store the result
        int sum = checkSumTree(A);

        // If the helper returns -1, it indicates the tree is not a Sum Binary Tree, so return 0.
        if (sum == -1) {
            return 0;
        }
        // Otherwise, the tree satisfies the property and we return 1.
        return 1;
    }

    // Helper function that calculates the sum of the tree if it's a Sum Binary Tree.
    // If not, it returns -1 as an error indicator.
    private int checkSumTree(TreeNode node) {
        // Base case: If the node is null, return 0 (sum of an empty tree is 0)
        if (node == null) {
            return 0; // Empty subtree contributes a sum of 0
        }

        // If the node is a leaf node (no left and right children),
        // it is by definition a Sum Binary Tree
        if (node.left == null && node.right == null) {
            return node.val; // Return the node's value, which is the sum of the subtree
        }

        // Compute the sum of values in the left subtree recursively
        int leftSum = checkSumTree(node.left);
        // If left subtree is not a Sum Binary Tree, propagate the error value (-1)
        if (leftSum == -1) {
            return -1;
        }

        // Compute the sum of values in the right subtree recursively
        int rightSum = checkSumTree(node.right);
        // If right subtree is not a Sum Binary Tree, propagate the error value (-1)
        if (rightSum == -1) {
            return -1;
        }

        // Check if the current node's value equals the sum of its left and right subtree sums.
        if (node.val != leftSum + rightSum) {
            // If not, then the current subtree does not satisfy the Sum Binary Tree property.
            return -1;
        }

        // If the property is satisfied,
        // return total sum of the subtree rooted at the current node.
        // This includes the current node's value and the sums of its left and right subtrees.
        return node.val + leftSum + rightSum;
    }
}
