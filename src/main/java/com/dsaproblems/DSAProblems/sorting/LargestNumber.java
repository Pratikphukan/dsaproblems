package com.dsaproblems.DSAProblems.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestNumber {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(3, 30, 34, 5, 9));
        Collections.sort(A, new ElementComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < A.size(); i++) {
            sb.append(A.get(i));
        }
        System.out.println(sb.toString());
        int[] arr = {3, 30, 34, 5, 9};
        System.out.println(findLargestNumberv1(arr));
        System.out.println(findLargestNumberv2(arr));
        System.out.println(findLargestNumberv3(arr));
    }

    private static String findLargestNumberv2(int[] A) {
        String[] parts = Arrays.stream(A)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        Arrays.sort(parts, (a, b) -> (b + a).compareTo(a + b));

        String result = String.join("", parts);
        return result.length() > 0 && result.charAt(0) == '0' ? "0" : result;
    }

    private static String findLargestNumberv3(int[] A) {
        String[] parts = new String[A.length];
        for (int i = 0; i < A.length; i++) {
            parts[i] = Integer.toString(A[i]);
        }
        Arrays.sort(parts, (a, b) -> (b + a).compareTo(a + b));
        String result = String.join("", parts);
        return result.length() > 0 && result.charAt(0) == '0' ? "0" : result;
    }

    //Arrays.sort(T[] , Comparator<? super T>) requires an object array. Convert the int[] to Integer[]
    private static String findLargestNumberv1(int[] A) {
        Integer[] arr = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            arr[i] = A[i];
        }
        Arrays.sort(arr, (num1, num2) -> {
            String a = Integer.toString(num1) + Integer.toString(num2);
            String b = Integer.toString(num2) + Integer.toString(num1);
            return b.compareTo(a);
        });
        StringBuilder sb = new StringBuilder();
        for (Integer item : arr) {
            sb.append(item);
        }
        // handle all-zero result
        if (sb.length() > 0 && sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}
