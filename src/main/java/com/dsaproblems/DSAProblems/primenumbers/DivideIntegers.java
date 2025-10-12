package com.dsaproblems.DSAProblems.primenumbers;

public class DivideIntegers {

    public static void main(String[] args) {
        System.out.println(dividev1(5, 2));
    }

    private static int dividev1(int A, int B) {
        if (A == Integer.MIN_VALUE && B == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result.
        // If either (but not both) A or B is negative, then the result should be negative.
        boolean negative = (A < 0) ^ (B < 0);

        // Convert A and B to positive long values to handle edge cases and avoid overflow.
        long dividend = Math.abs((long) A);
        long divisor = Math.abs((long) B);

        // Initialize quotient as 0.
        int quotient = 0;

        // Loop until the dividend is less than the divisor.
        // Each iteration subtracts the largest multiple of the divisor that can be subtracted.
        while (dividend >= divisor) {
            // This variable stores the current value of divisor multiplied by a power of 2.
            long temp = divisor;
            // This variable stores the corresponding multiple.
            int multiple = 1;

            // Find the largest double of divisor (using bit shift) that is less than or equal to dividend.
            while (dividend >= (temp << 1)) {
                // Double the temporary divisor value.
                temp <<= 1; // Equivalent to temp = temp * 2
                // Double the multiple count.
                multiple <<= 1; // Equivalent to multiple = multiple * 2
            }

            // Subtract the largest found multiple from the dividend.
            dividend -= temp;
            // Add the multiple to our quotient.
            quotient += multiple;
        }

        // Apply the sign to the quotient.
        return negative ? -quotient : quotient;
    }
}
