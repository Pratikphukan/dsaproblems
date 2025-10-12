package com.dsaproblems.DSAProblems.bitmanipulation01;

public class CountTotalSetBits {

    public static void main(String[] args) {
        System.out.println(countSetBitsv1(26));
    }

    public static int countSetBitsv1(int A) {
        int modulo = 0; // There are two values associated with every bit of the number, A.
        int quotient = 0; // Modulo and Quotient
        long ans = 0L;
        for (int i = 0; i < 32; i++) { // Iteration over the bits of the number
            if (A < (1 << i)) // To stop iteration beyond the value of A; 1 << i = 2 ^ i;
                break;
            if ((A & (1 << i)) != 0) // We dont need to find modulo if the ith bit is zero
                modulo = A % (1 << i) + 1; // Modulo represents the extra bits at ith bit
            else
                modulo = 0;
            quotient = ((A / (1 << (i + 1))) * (1 << i)); // Quotient represents the number of completed group and the contribution of 1's to each group (1 << i);
            ans += (long) modulo + (long) quotient;
        }
        ans = ans % 1000000007;
        return (int) ans;
    }
}
