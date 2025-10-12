package com.dsaproblems.DSAProblems.array01;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSumSquareSubMatrix {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        A.add(new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2)));
        A.add(new ArrayList<>(Arrays.asList(3, 8, 6, 7, 3)));
        A.add(new ArrayList<>(Arrays.asList(4, 4, 4, 4, 4)));
        A.add(new ArrayList<>(Arrays.asList(5, 5, 5, 5, 5)));
        int B = 3;

        //System.out.println(maximumSumSquareSubMatrix(A, B));

        System.out.println(maximumSumSquareSubMatrixv1(A, B));
//		System.out.println(A);
//		for (int i = 0; i < A.get(0).size(); i++) {
//			for (int j = 1; j < A.size(); j++) {
//				A.get(j).set(i, A.get(j).get(i) + A.get(j - 1).get(i));
//			}
//		}
//		System.out.println(A);
//		int ans = Integer.MIN_VALUE;
//		for (int srow = 0; srow <= A.size() - B; srow++) {
//			ArrayList<Integer> intarr = new ArrayList<Integer>();
//			for (int j = 0; j < B; j++) {
//				int item;
//				if (srow == 0) {
//					item = A.get(srow + B - 1).get(j);
//				} else {
//					item = A.get(srow + B - 1).get(j) - A.get(srow - 1).get(j);
//				}
//				intarr.add(item);
//			}
//			System.out.println(intarr);
//			for (int j = 1; j <= A.get(0).size() - B; j++) {
//				int item;
//				if (srow == 0) {
//					item = A.get(srow + B - 1).get(j);
//				} else {
//					item = A.get(srow + B - 1).get(j) - A.get(srow - 1).get(j);
//				}
//				intarr.add(item);
//			}
//			System.out.println(intarr);
//			int sum = 0;
//			for (int i = 0; i < intarr.size(); i++) {
//				sum += intarr.get(i);
//				if (sum > ans) {
//					ans = sum;
//				}
//				if (sum < 0) {
//					sum = 0;
//				}
//			}
//		}
//		System.out.println(ans);

    }

    private static int maximumSumSquareSubMatrixv1(ArrayList<ArrayList<Integer>> A, int B) {
        int n = A.size();
        int[] sum = new int[n];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < B; i++) {
            for (int j = 0; j < n; j++) {
                sum[j] += A.get(i).get(j);
            }
        }
        max = Math.max(max, slidingwindow(sum, B));
        for (int i = B; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[j] += A.get(i).get(j) - A.get(i - B).get(j);
            }
            max = Math.max(slidingwindow(sum, B), max);
        }
        return max;
    }

    private static int maximumSumSquareSubMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        for (int i = 0; i < A.get(0).size(); i++) {
            for (int j = 1; j < A.size(); j++) {
                A.get(j).set(i, A.get(j).get(i) + A.get(j - 1).get(i));
            }
        }
        System.out.println(A);
        int max = Integer.MIN_VALUE;
        for (int srow = B - 1; srow < A.size(); srow++) {
            ArrayList<Integer> intarr = new ArrayList<Integer>();
            for (int j = 0; j < A.get(srow).size(); j++) {
                if (srow == B - 1) {
                    intarr.add(A.get(srow).get(j));
                } else {
                    intarr.add(A.get(srow).get(j) - A.get(srow - B).get(j)); // fail point earlier
                }
            }
            System.out.println(intarr);
            int sum = 0;
            for (int i = 0; i < B; i++) {
                sum += intarr.get(i);
            }
            max = sum;
            for (int i = 1; i <= intarr.size() - B; i++) {
                sum += intarr.get(i + B - 1) - intarr.get(i - 1);
                if (sum > max) {
                    max = sum;
                }
            }

        }
        return max;
    }

    public static int solve(int[][] A, int B) {
        int n = A.length;

// initial array (window) prep from 0 to B-1.
        int sum[] = new int[n];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < B; i++) {
            for (int j = 0; j < n; j++) {
                sum[j] += A[i][j];
            }
        }

        max = Math.max(max, slidingwindow(sum, B));

// remaining array (windows) ie adding the Bth row and removing the zero row.

        for (int i = B; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[j] += A[i][j] - A[i - B][j];
            }
            max = Math.max(slidingwindow(sum, B), max);
        }

        return max;
    }

    public static int slidingwindow(int A[], int B) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < B; i++) {
            sum += A[i];
        }
        max = Math.max(sum, max);

        for (int i = B; i < A.length; i++) {
            sum += A[i] - A[i - B];
            max = Math.max(max, sum);
        }
        return max;
    }

}
