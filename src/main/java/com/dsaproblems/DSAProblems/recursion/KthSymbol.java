package com.dsaproblems.DSAProblems.recursion;

public class KthSymbol {

    public static void main(String[] args) {
        int A = 4;
        int B = 3;
        System.out.println(getBthSymbolInAthRowv1(A, B));
    }

    //1:0
    //2:01
    //3:0110
    //4:01101001
    //working code
    private static int getBthSymbolInAthRowv1(int A, int B) {
        if (A == 1) return 0; //when A is 1, there is only one symbol 0
        int halLen = 1 << (A - 2); //length of row A is 2^(A-1). Hence, half of that (the first half) is 2^(A-2)
        if (B <= halLen) {
            // The element at position B in row A is the same as the element at position B in row A-1
            return getBthSymbolInAthRowv1(A - 1, B);
        }
        // The element is in the second half.
        // The rule states that if the element in the previous row is 0, it becomes 1; and if it's 1, it becomes 0.
        // We get the element in previous row corresponding to the second half by adjusting the index.
        int prev = getBthSymbolInAthRowv1(A - 1, B - halLen);
        return prev == 0 ? 1 : 0;
    }
}
