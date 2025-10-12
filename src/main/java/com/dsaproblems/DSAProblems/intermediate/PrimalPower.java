package com.dsaproblems.DSAProblems.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class PrimalPower {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(-11, 7, 8, 9, 10, 11));
        System.out.println(getCountOfPrimesv1(input));
        System.out.println(getCountOfPrimesv2(input));
    }

    private static int getCountOfPrimesv2(ArrayList<Integer> input) {
        //return Long.valueOf(input.stream().filter(this::isPrime).count()).intValue();//instance
        return Long.valueOf(input.stream().filter(PrimalPower::isPrimev1).count()).intValue(); //static
    }

    private static boolean isPrimev1(Integer num) {
        return num >= 2 && IntStream.rangeClosed(2, (int) Math.sqrt(num)).noneMatch(i -> num % i == 0);
    }

    private static int getCountOfPrimesv1(ArrayList<Integer> input) {
        int count = 0;
        for (int num : input) {
            count += isPrime(num) ? 1 : 0;
        }
        return count;
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
