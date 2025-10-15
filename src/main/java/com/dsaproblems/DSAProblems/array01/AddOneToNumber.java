package com.dsaproblems.DSAProblems.array01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddOneToNumber {

    public static void main(String[] args) {
        // 1, 1, 1, 3, 2, 1, 1, 2, 5, 9, 6, 5
        // 0,3,7,6,4,0,5,5,5
        // 0, 9, 9, 9, 9, 9
        // 1,9,9,9,9,9,9
        Integer[] digits = {0, 3, 7, 6, 4, 0, 5, 5, 5};
        System.out.println(plusOnev1(new ArrayList<>(Arrays.asList(digits))));

        int[] A = {0, 0, 3, 7, 6, 4, 0, 5, 5, 5};
        System.out.println(plusOnev2(A));

    }

    private static void generateModifiedArray(int[] digits) {
        long result = 0L;
        for (Integer digit : digits) {
            result = result * 10 + digit;
        }
        result += 1;
        List<Integer> list = new ArrayList<>();
        while (result > 0) {
            list.add(0, (int) (result % 10));
            result /= 10;
        }
        System.out.println(list);
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        long number = 0L;
        for (Integer digit : A) {
            number = number * 10 + digit;
        }
        number += 1;
        ArrayList<Integer> ans = new ArrayList<>();
        while (number > 0) {
            ans.add(0, (int) (number % 10));
            number /= 10;
        }
        return ans;
    }

    public static ArrayList<Integer> plusOne2(ArrayList<Integer> A) {
        Integer number = 0;
        for (Integer digit : A) {
            number = number * 10 + digit;
            number %= 10000007;
        }
        number += 1;
        ArrayList<Integer> ans = new ArrayList<>();
        while (number > 0) {
            ans.add(0, (number % 10));
            number /= 10;
        }
        return ans;
    }

    // partially correct
    public static ArrayList<Integer> plusOne3(ArrayList<Integer> A) {
        BigInteger number = BigInteger.ZERO;
        for (Integer digit : A) {
            number = number.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        }
        number = number.add(BigInteger.valueOf(1));
        ArrayList<Integer> ans = new ArrayList<>();
        while (!number.equals(BigInteger.ZERO)) {
            ans.add(0, number.mod(BigInteger.TEN).intValue());
            number = number.divide(BigInteger.TEN);
        }
        return ans;
    }

    // working code
    public static ArrayList<Integer> plusOne4(ArrayList<Integer> A) {
        int nonZeroIndex = 0;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != 0) {
                nonZeroIndex = i;
                break;
            }
        }
        ArrayList<Integer> trimmedList = new ArrayList<>(A.subList(nonZeroIndex, A.size()));
        Collections.reverse(trimmedList);
        if (trimmedList.get(0) != 9) {
            trimmedList.set(0, trimmedList.get(0) + 1);
            Collections.reverse(trimmedList);
            return trimmedList;
        }
        int lastIndex = -1;
        for (int i = 0; i < trimmedList.size(); i++) {
            if (trimmedList.get(i) == 9) {
                lastIndex = i;
            } else {
                break;
            }
        }
        for (int i = 0; i <= lastIndex; i++) {
            trimmedList.set(i, 0);
        }
        if ((lastIndex + 1) == trimmedList.size()) {
            trimmedList.add(1);
        } else {
            trimmedList.set(lastIndex + 1, trimmedList.get(lastIndex + 1) + 1);
        }
        Collections.reverse(trimmedList);
        return trimmedList;
    }

    // working code
    public static ArrayList<Integer> plusOnev1(ArrayList<Integer> A) {
        int n = A.size();
        int nonZeroIndex = 0;
        for (int i = 0; i < n; i++) {
            if (A.get(i) != 0) {
                nonZeroIndex = i;
                break;
            }
        }
        ArrayList<Integer> trimmedList = new ArrayList<>(A.subList(nonZeroIndex, n));
        int carry = 1;
        for (int i = trimmedList.size() - 1; i >= 0; i--) {
            int sum = trimmedList.get(i) + carry;
            trimmedList.set(i, sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            trimmedList.add(0, carry);
        }

        return trimmedList;
    }

    public static int[] plusOnev2(int[] A) {
        int n = A.length;
        int nonZeroIndex = 0;
        while (nonZeroIndex < n && A[nonZeroIndex] == 0) {
            nonZeroIndex++;
        }
        List<Integer> trimmed = new ArrayList<>();
        for (int i = nonZeroIndex; i < n; i++) {
            trimmed.add(A[i]);
        }
        int carry = 1;
        for (int i = trimmed.size() - 1; i >= 0; i--) {
            int sum = trimmed.get(i) + carry;
            trimmed.set(i, sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            trimmed.add(0, carry);
        }
        int[] result = new int[trimmed.size()];
        for (int i = 0; i < trimmed.size(); i++) {
            result[i] = trimmed.get(i);
        }
        return result;
    }
}
