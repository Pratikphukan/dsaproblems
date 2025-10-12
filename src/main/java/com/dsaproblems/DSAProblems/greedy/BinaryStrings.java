package com.dsaproblems.DSAProblems.greedy;

public class BinaryStrings {

    public static void main(String[] args) {
        String input = "00010110";
        int window = 3;
        System.out.println(minTimeToMakeStringToOne(input, window));
        System.out.println(minTimeToMakeStringToOnev1(input, window));
        System.out.println(minTimeToMakeStringToOnev2(input, window));
        System.out.println(minTimeToMakeStringToOnev3(input, window));
    }

    private static int minTimeToMakeStringToOnev3(String A, int B) {
        int n = A.length();
        int[] diff = new int[n];
        int flip = 0, operations = 0;
        for (int i = 0; i < n; i++) {
            if (i >= B) flip -= diff[i - B];
            char effective = (flip % 2 == 0) ? A.charAt(i) : (A.charAt(i) == '1' ? '0' : '1');
            if (effective == '1') continue;
            if (i + B > n) return -1;
            flip++;
            diff[i] = 1;
            operations++;
        }
        return operations;
    }

    //working code
    private static int minTimeToMakeStringToOnev2(String A, int B) {
        char[] chArr = A.toCharArray();
        int n = chArr.length, flip = 0;
        for (int i = 0; i <= n - B; i++) {
            if (chArr[i] == '0') {
                flip++;
                for (int j = 0; j < B; j++) {
                    chArr[i + j] = chArr[i + j] == '0' ? '1' : '0';
                }
            }
        }
        //The final check only inspects the tail of the array for remaining '0's,
        // improving efficiency
        for (int i = n - B; i < n; i++) {
            if (chArr[i] == '0') return -1;
        }
        return flip;
    }

    private static int minTimeToMakeStringToOnev1(String A, int B) {
        // Convert the input string to a character array for easier manipulation.
        char[] chArr = A.toCharArray();
        int n = chArr.length; // Get the length of the string

        // Initialize a difference array to keep track of flip operations
        // This will help us efficiently determine the cumulative flips affecting each index.
        int[] diff = new int[n];

        // Variable to maintain the cumulative flip count for the current position.
        int flip = 0;
        // Initialize counter for the number of operations performed.
        int operations = 0;

        // Iterate over each index in the array.
        for (int i = 0; i < n; i++) {
            // If we are at or past the index where a previous flip's effect expires,
            // subtract the expired flip effect from the 'flip' counter.
            if (i >= B) {
                flip -= diff[i - B];
            }

            // Determine the effective value at index i.
            // For a character chArr[i],
            // if flip count is even, the effective bit is the same as the original;
            // if odd, it is flipped.
            char effective = (flip % 2 == 0) ? chArr[i] : (chArr[i] == '1' ? '0' : '1');

            // If the effective value is already '1', no operation is needed.
            if (effective == '1') {
                continue;
            }

            // If we need to flip here (effective value is '0'), check if flipping is possible.
            if (i + B > n) {
                // Not enough characters to flip the substring of length B.
                return -1;
            }

            // Perform the flip operation:
            // Increase our flip counter by 1 since a new flip operation starts at index i.
            flip++;
            // Mark the start of the flip effect in the diff array.
            diff[i] = 1;
            // Increment our operations counter as an operation was used.
            operations++;
        }
        // Return the total number of operations required.
        return operations;
    }

    private static int minTimeToMakeStringToOne(String input, int B) {
        int len = input.length();
        StringBuilder sb = new StringBuilder(input);
        int count = 0;
        for (int i = 0; i <= len - B; i++) {
            if (sb.charAt(i) == '0') {
                count++;
                for (int j = i; j < i + B; j++) {
                    sb.setCharAt(j, '1');
                }
            } else {
                continue;
            }
        }
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '0')
                return 0;
        }
        return 1;
    }

}
