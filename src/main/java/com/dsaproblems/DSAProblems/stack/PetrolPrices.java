package com.dsaproblems.DSAProblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class PetrolPrices {

    public static void main(String[] args) {
        int[] A = {5, 6, 3, 4, 2, 1};
        int B = 2;

        System.out.println(minCostv1(A, B));
    }

    private static int minCostv1(int[] A, int B) {
        int n = A.length;
        int[] nextCheaper = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // We iterate from the last day to the first day to compute the next cheaper day for each day.
        for (int i = n - 1; i >= 0; i--) {
            // While stack is not empty and the price at the top of the stack is NOT less (i.e. >= current price),
            // pop from the stack since those days are not candidates for a "cheaper day"
            while (!stack.isEmpty() && A[stack.peekFirst()] >= A[i])
                stack.pollFirst();
            nextCheaper[i] = stack.isEmpty() ? n : stack.peekFirst();
            stack.addFirst(i);
        }
        int totalCost = 0, fuel = 0;
        for (int i = 0; i < n; i++) {
            int next = nextCheaper[i];
            int requiredFuel = next - i;

            // Calculate how much to buy.
            // He can only purchase at most B - fuel litres because B is the tank capacity.
            int toBuy = Math.min(B - fuel, Math.max(0, requiredFuel - fuel));
            totalCost += toBuy * A[i];
            fuel += (toBuy - 1);
            if (fuel < 0) fuel = 0;
        }
        return totalCost;
    }
}
