package com.dsaproblems.DSAProblems.array03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RightRotation {

//	public class Main {
//	    public static void reverse(int[] A, int start, int end) {
//	        int i, j;
//	        for (i = start, j = end; i < j; i++, j--) {
//	            int temp = A[i];
//	            A[i] = A[j];
//	            A[j] = temp;
//	        }
//	    }
//	    public static void main(String[] args) {
//	        Scanner sc = new Scanner(System.in);
//	        int n = sc.nextInt();
//	        int[] A = new int[n];
//	        for (int i = 0; i < n; i++) {
//	            A[i] = sc.nextInt();
//	        }
//	        int B = sc.nextInt() % n;
//	        reverse(A, 0, n - 1);
//	        reverse(A, 0, B - 1);
//	        reverse(A, B, n - 1);
//
//	        for (int i = 0; i < n; i++) {
//	            System.out.print(A[i] + " ");
//	        }
//	        System.out.println("");
//
//	    }
//	}

    public static void main(String[] args) {

        // List<Integer> arr0 = List.of(-1,4,7,6,-2,7,8,10);
        // ArrayList<Integer> arr1 = new ArrayList<>(arr0);
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of values: ");
        int n = input.nextInt();
        System.out.println("Enter the values:");
        while (n-- > 0) {
            int item = input.nextInt();
            arr.add(item);
        }
        System.out.print("Enter the rotation value: ");
        int k = input.nextInt();
        System.out.println(rightRotate(arr, k));
        System.out.println(leftRotate(arr, k));
        input.close();
    }

    public static ArrayList<Integer> leftRotate(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> rotatedArray = new ArrayList<>(arr);
        int len = arr.size();
        k = k % len;
        reverseSubArray(rotatedArray, 0, len - 1);
        reverseSubArray(rotatedArray, 0, len - k - 1);
        reverseSubArray(rotatedArray, len - k, len - 1);
        return rotatedArray;
    }

    public static ArrayList<Integer> rightRotate(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> rotatedArray = new ArrayList<>(arr);
        int len = arr.size();
        k = k % len;
        reverseSubArray(rotatedArray, 0, len - 1);
        reverseSubArray(rotatedArray, 0, k - 1);
        reverseSubArray(rotatedArray, k, len - 1);
        return rotatedArray;
    }

    public static void reverseSubArray(ArrayList<Integer> arr, int i, int j) {
        while (i < j) {
            arr.set(i, arr.get(i) + arr.get(j));
            arr.set(j, arr.get(i) - arr.get(j));
            arr.set(i, arr.get(i) - arr.get(j));
            i++;
            j--;
        }
    }

}
