package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RotatedSortedArraySearch {

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(Arrays.asList(5, 1, 3));
        // 5, 1, 3->5
        // 4, 5, 6, 7, 0, 1, 2, 3->6
        // 11, 14, 19, 23, 27, -20, -14, -8, -4, 1, 2, 4, 7->5
        // 11, 14, 19, 23, 27, 29, 34, 38, -4, 1, 2, 4, 7->5
        //180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202, 203, 204, 3, 4, 5, 6, 7, 8,
        // 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36, 37, 38, 39, 41, 42, 43, 45, 48, 51, 52, 53, 54,
        // 56, 62, 63, 64, 67, 69, 72, 73, 75, 77, 78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101, 102, 104, 105,
        // 106, 107, 108, 109, 111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130, 135, 137, 138, 139,
        // 143, 144, 145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177->42


        //9, 10, 11, 12, 14, 15, 17, 19, 24, 25, 30, 39, 40, 44, 46, 48, 51, 53, 54, 56, 59, 60, 69, 70, 73, 75, 80, 87, 88,
        //89, 92, 93, 97, 99, 104, 107, 109, 110, 111, 117, 123, 124, 125, 126, 127, 128, 135, 136, 137, 141, 148, 153, 154,
        //161, 166, 167, 169, 175, 177, 180, 181, 182, 185, 186, 189, 193, 198, 202, 1, 2, 6, 7->198
        int B = 5;
        System.out.println(searchInRotatedSortedArrayv1(A, B));
        System.out.println(searchInRotatedSortedArrayv2(A, B));
        System.out.println(searchInRotatedSortedArrayv3(A, B));
    }

    //working code
    private static int searchInRotatedSortedArrayv3(List<Integer> input, int B) {
        int low = 0, high = input.size() - 1;
        while (low <= high) {
            int mid = high - (high - low) / 2;
            if (input.get(mid) == B) return mid;
            //you can neglect one side based on low, high and mid
            if (input.get(low) <= input.get(mid)) { // left side is sorted
                if (input.get(low) <= B && input.get(mid) > B) {
                    high = mid - 1; // search in left side
                } else {
                    low = mid + 1;
                }
            } else {// right side is sorted
                if (input.get(high) >= B && input.get(mid) < B) {
                    low = mid + 1; // search in right side
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //working code
    private static int searchInRotatedSortedArrayv1(List<Integer> input, int B) {
        int low = 0, high = input.size() - 1;
        int k = 0;
        while (low <= high) {
            int mid = high - (high - low) / 2;
            if (input.get(mid) > input.get(input.size() - 1)) {
                low = mid + 1; //try to move to the right sorted segment
            } else {
                k = mid; //in the right sorted segment, k is the possible start
                high = mid - 1;
            }
        }
        //k is the start index of right sorted segment
        if (B < input.get(0) || k == 0) { //check where B lies
            low = k;
            high = input.size() - 1;
        } else {
            low = 0;
            high = k - 1;
        }
        while (low <= high) {
            int mid = high - (high - low) / 2;
            if (input.get(mid) > B) {
                high = mid - 1; //move to the left of mid
            } else if (input.get(mid) < B) {
                low = mid + 1; //move to the right of mid
            } else {
                return mid;
            }
        }
        return -1;
    }

    //working code
    private static int searchInRotatedSortedArrayv2(List<Integer> input, int B) {
        int low = 0, high = input.size() - 1;
        int k = 0;
        while (low <= high) {
            int mid = high - (high - low) / 2;
            if (input.get(mid) < input.get(0)) {
                high = mid - 1; //try to move to the left sorted segment
            } else {
                k = mid; //in the left sorted segment, k is a possible mid
                low = mid + 1;
            }
        }
        //k is the end of the left sorted segment
        if (B < input.get(0)) {
            low = k + 1;
            high = input.size() - 1;
        } else {
            low = 0;
            high = k;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (input.get(mid) > B) {
                high = mid - 1; //move to the left of mid
            } else if (input.get(mid) < B) {
                low = mid + 1; //move to the right of mid
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int searchInRotatedSortedArrayv4(List<Integer> input, int B) {
        int low = 0, high = input.size() - 1;
        int k = 0;
        while (low <= high) {
            int mid = high - (high - low) / 2;
            if (input.get(mid) < input.get(0)) {
                k = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (B < input.get(0) || k == 0) {
            low = k;
            high = input.size() - 1;
        } else {
            low = 0;
            high = k - 1;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (input.get(mid) > B) {
                high = mid - 1;
            } else if (input.get(mid) < B) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
