package com.dsaproblems.DSAProblems.greedy;

public class Seats {
    private static final int MOD = 10000003;

    // Method to calculate the minimum number of jumps required
    public static int seats(String A) {
        // List to store indices of occupied seats
        // We use an array (or List) here to store the positions where the character is 'x'
        java.util.ArrayList<Integer> positions = new java.util.ArrayList<>();

        // Traverse the string to find all positions with 'x'
        for (int i = 0; i < A.length(); i++) {
            // Check if current seat is occupied
            if (A.charAt(i) == 'x') {
                // Add the index if it's an occupied seat
                positions.add(i);
            }
        }

        // If there are no people or only one person, there's no move needed.
        if (positions.size() <= 1) {
            return 0;
        }

        // Determine the median index from the list of occupied seat indices
        int n = positions.size();
        int midIndex = n / 2;  // integer division gives the median index

        // The ideal anchor (median seat position) for aligning people
        int medianSeat = positions.get(midIndex);

        // Variable to accumulate the total number of moves needed
        long totalMoves = 0;

        // For each occupied seat compute the move needed to place each person into correct contiguous order.
        // The ideal target for the i-th person (based on sorted order) is: medianSeat - (midIndex - i)
        for (int i = 0; i < n; i++) {
            // Current person's target position to achieve contiguous seating arrangement
            int targetPosition = medianSeat - (midIndex - i);
            // Calculate the difference between the current position and the target position
            totalMoves = (totalMoves + Math.abs(positions.get(i) - targetPosition)) % MOD;
        }

        // Return the final result modulo MOD
        return (int) totalMoves;
    }

    // Main method to run tests
    public static void main(String[] args) {

        // Test Case 1: Basic example with scattered 'x'
        // Example from problem statement: "....x..xx...x.."
        // Explanation: Moves = 2 (to bring person from index 4 to 6) + 3 (to bring person from index 12 to 9)
        // Expected output = 5
        String test1 = "....x..xx...x..";
        System.out.println("Test Case 1 Output: " + seats(test1)); // Expected 5

        // Test Case 2: Already contiguous seats so no moves needed.
        String test2 = "xx.xx";  // Even though it looks like there is a gap, let’s use contiguous input "xxxx" for clarity.
        test2 = "xxxx";  // All are already together.
        System.out.println("Test Case 2 Output: " + seats(test2)); // Expected 0

        // Test Case 3: No one is seated.
        String test3 = "....."; // No 'x' anywhere.
        System.out.println("Test Case 3 Output: " + seats(test3)); // Expected 0

        // Test Case 4: Random scattered but not contiguous.
        // Example: positions: 1, 3, 6 -> We want them to be in positions 1,2,3 or any contiguous block with minimal moves.
        // Optimal block is 1,2,3 leading to moves = 0 (for index 1) + 0 (for index 3 already) + (6-3) = 3.
        String test4 = ".x.x..x";
        System.out.println("Test Case 4 Output: " + seats(test4)); // Expected 3

        // Test Case 5: Single person (no moves needed).
        String test5 = "......x......";
        System.out.println("Test Case 5 Output: " + seats(test5)); // Expected 0
    }
}
