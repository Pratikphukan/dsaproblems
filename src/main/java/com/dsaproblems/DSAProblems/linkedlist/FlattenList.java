package com.dsaproblems.DSAProblems.linkedlist;

public class FlattenList {

    static class ListNode {
        int val;               // Value of the node
        ListNode right;        // Pointer to the next node in the main (horizontal) list
        ListNode down;         // Pointer to the next node in the vertical (down) list

        // Constructor for ListNode initializing the value and pointers
        ListNode(int x) {
            this.val = x;
            this.right = null;
            this.down = null;
        }
    }

    private ListNode mergeLists(ListNode h1, ListNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        ListNode result = null;
        if (h1.val > h2.val) {
            result = h2;
            result.down = mergeLists(h1, h2.down);
        } else {
            result = h1;
            result.down = mergeLists(h1.down, h2);
        }
        return result;
    }

    private ListNode flatten(ListNode A) {
        if (A == null || A.right == null) {
            return A;
        }
        A.right = flatten(A.right);
        A = mergeLists(A, A.right);
        return A;
    }

    public void printFlattenedList(ListNode head) {
        // Iterate through the down list and print node values
        while (head != null) {
            // Print the current node's value followed by a space for readability
            System.out.print(head.val + " ");
            // Move to the next node in the flattened list using down pointer
            head = head.down;
        }
        System.out.println(); // Move to a new line after printing the list
    }

    public static void main(String[] args) {

        // Create an instance of Solution to call member methods
        FlattenList sol = new FlattenList();

        // ---------------------------------------------------------------------------------
        // Test Case 1: A single node list
        // Expected Output: The single node value.
        ListNode singleNode = new ListNode(5);
        // Flatten it (here, it's already flat) and print the result
        ListNode flatSingle = sol.flatten(singleNode);
        System.out.print("Test Case 1 (Single Node): ");
        sol.printFlattenedList(flatSingle);

        // ---------------------------------------------------------------------------------
        // Test Case 2: Vertical list only (no right pointers)
        // Create a vertical list: 3 -> 7 -> 8 -> 30
        ListNode verticalHead = new ListNode(3);
        verticalHead.down = new ListNode(7);
        verticalHead.down.down = new ListNode(8);
        verticalHead.down.down.down = new ListNode(30);
        // Flatten it; since there's no right pointer, result remains same
        ListNode flatVertical = sol.flatten(verticalHead);
        System.out.print("Test Case 2 (Vertical List only): ");
        sol.printFlattenedList(flatVertical);

        // ---------------------------------------------------------------------------------
        // Test Case 3: Multiple nodes with right and down pointers
        // Create first vertical list: 5 -> 7 -> 8 -> 30
        ListNode head1 = new ListNode(5);
        head1.down = new ListNode(7);
        head1.down.down = new ListNode(8);
        head1.down.down.down = new ListNode(30);

        // Create second vertical list: 10 -> 20
        ListNode head2 = new ListNode(10);
        head2.down = new ListNode(20);
        // Link the two lists using right pointer
        head1.right = head2;

        // Create third vertical list: 19 -> 22 -> 50
        ListNode head3 = new ListNode(19);
        head3.down = new ListNode(22);
        head3.down.down = new ListNode(50);
        // Link the second list's right pointer to the third list
        head2.right = head3;

        // Create fourth vertical list: 28 -> 35 -> 40 -> 45
        ListNode head4 = new ListNode(28);
        head4.down = new ListNode(35);
        head4.down.down = new ListNode(40);
        head4.down.down.down = new ListNode(45);
        // Link the third list's right pointer to the fourth list
        head3.right = head4;

        // Flatten the entire list of lists
        ListNode flattenedList = sol.flatten(head1);
        System.out.print("Test Case 3 (Multiple Nodes with Right and Down): ");
        sol.printFlattenedList(flattenedList);

        // ---------------------------------------------------------------------------------
        // Test Case 4: Edge Case - Empty list (root is null)
        // Expected Output: (no output, or a printed message if modified accordingly)
        ListNode emptyList = null;
        ListNode flatEmpty = sol.flatten(emptyList);
        System.out.print("Test Case 4 (Empty List): ");
        if (flatEmpty == null) {
            // Print message if the flattened list is empty
            System.out.println("List is empty");
        } else {
            sol.printFlattenedList(flatEmpty);
        }

    }
}
