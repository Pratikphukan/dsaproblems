package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MaximumRectangle {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix1 = new ArrayList<>();
        matrix1.add(new ArrayList<>() {{
            add(0);
            add(0);
            add(1);
        }});
        matrix1.add(new ArrayList<>() {{
            add(0);
            add(1);
            add(1);
        }});
        matrix1.add(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
        }});

        System.out.println(getLargestRectanglev1(matrix1));

        ArrayList<ArrayList<Integer>> matrix2 = new ArrayList<>();
        matrix2.add(new ArrayList<>() {{
            add(0);
            add(1);
            add(0);
            add(1);
        }});
        matrix2.add(new ArrayList<>() {{
            add(1);
            add(0);
            add(1);
            add(0);
        }});

        System.out.println(getLargestRectanglev1(matrix2));


        ArrayList<ArrayList<Integer>> matrix5 = new ArrayList<>();
        matrix5.add(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
        }});
        matrix5.add(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
        }});
        matrix5.add(new ArrayList<>() {{
            add(1);
            add(1);
            add(1);
        }});
        System.out.println(getLargestRectanglev1(matrix5));
    }

    //working code
    private static int getLargestRectanglev1(ArrayList<ArrayList<Integer>> A) {
        // Number of rows in matrix
        int n = A.size();
        // Number of columns in matrix; assuming non-empty rows per constraints.
        int m = A.get(0).size();

        // This array will store the consecutive count of '1's for each column.
        int[] heights = new int[m];

        // Variable to store the maximum rectangle area found
        int maxArea = 0;

        // Loop through every row in the matrix
        for (ArrayList<Integer> list : A) {
            // For each column, update the running count of consecutive ones.
            for (int j = 0; j < m; j++) {
                // If the cell contains a 1, add to the previous height.
                if (list.get(j) == 1) {
                    heights[j] += 1;
                } else {
                    // If the cell is 0, reset the height as rectangle cannot extend upwards.
                    heights[j] = 0;
                }
            }
            // Calculate the maximum rectangle area for this histogram row using the helper function.
            int area = largestRectangleInHistogram(heights);
            // Check if this area is greater than our current maxArea and update it.
            maxArea = Math.max(maxArea, area);
        }
        // Return the maximum rectangle area found in the entire matrix.
        return maxArea;
    }

    private static int largestRectangleInHistogram(int[] heights) {
        // Create a stack to store indices of the histogram bars.
        Deque<Integer> stack = new ArrayDeque<>();
        // Variable to hold the maximum area computed.
        int maxArea = 0;
        // Number of bars in the histogram.
        int n = heights.length;

        // Loop through each bar in the histogram.
        for (int i = 0; i <= n; i++) {
            // For the last iteration, use height 0 as a sentinel to flush out the stack.
            int currentHeight = (i == n) ? 0 : heights[i];

            // While the current height is less than the height at the stack's top index, calculate area.
            while (!stack.isEmpty() && currentHeight < heights[stack.peekFirst()]) {
                // Pop the top index from the stack.
                int height = heights[stack.pollFirst()];
                // Determine the width of the rectangle.
                // If stack is empty means the bar at popped index is the smallest so far.
                int width = (stack.isEmpty()) ? i : i - stack.peekFirst() - 1;
                // Update maxArea if the computed area is larger.
                maxArea = Math.max(maxArea, height * width);
            }
            // Push the current index to the stack.
            stack.addFirst(i);
        }
        // Return the largest area found in the histogram.
        return maxArea;
    }
}
