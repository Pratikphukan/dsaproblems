package com.dsaproblems.DSAProblems.advancedJava;

import java.util.concurrent.ThreadLocalRandom;

public class OnePercentFilter {

    public static void main(String[] args) {
        OnePercentFilter filter = new OnePercentFilter();
        int numberOfRuns = 2000;
        int trueCount = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            if (filter.filter()) {
                trueCount++;
            }
        }

        System.out.println("Out of " + numberOfRuns + " calls, filter returned true " + trueCount);

        // Output the result of the simulation
        System.out.println("Out of " + numberOfRuns + " calls, filter returned true " + trueCount + " times.");

        // Optional: Validate if the true count is within the allowed range (approx. 1% ± 5% error)
        // Expected true count is around 20 (1% of 2000). With a 5% tolerance, allowed values: 19 to 21.
        int expectedCount = numberOfRuns / 100;  // 1% expected count, here it is 20 for 2000 runs
        int lowerBound = (int) (expectedCount - expectedCount * 0.05); // 5% error tolerance lower bound
        int upperBound = (int) (expectedCount + expectedCount * 0.05); // 5% error tolerance upper bound

        // Print the bounds for clarity
        System.out.println("Expected true returns: " + expectedCount +
                " (allowed range: " + lowerBound + " to " + upperBound + ")");

        // Inform whether the test is passed or not
        if (trueCount >= lowerBound && trueCount <= upperBound) {
            System.out.println("Test passed within the allowed error margin.");
        } else {
            System.out.println("Test failed. True count is outside the allowed error margin.");
        }
    }

    private boolean filter() {
        double randomValue = ThreadLocalRandom.current().nextDouble();
        return randomValue < 0.01;
    }
}
