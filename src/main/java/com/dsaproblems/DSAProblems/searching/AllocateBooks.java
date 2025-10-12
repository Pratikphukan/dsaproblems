package com.dsaproblems.DSAProblems.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllocateBooks {

	public static void main(String[] args) {
		List<Integer> books = new ArrayList<>(Arrays.asList(31, 14, 19, 75));
		// 31,14,19,75->12
		// 12, 34, 67, 90->2
		int students = 1;
		System.out.println(findMinimumTimeAllocatedToStudents(books, students));
	}

	private static int findMinimumTimeAllocatedToStudents(List<Integer> books, int students) {
		int maxPagesAllocated = 0; // considering one student
		int minPagesAllocated = 0; // considering students equal to the size of array
		for (Integer pages : books) {
			minPagesAllocated = Math.max(minPagesAllocated, pages);
			maxPagesAllocated += pages;
		}
		if (students > books.size()) {
			return -1;
		}
		int ans = maxPagesAllocated; // considering students==size of books, has to read all the books
		int possiblePagesAllocated = 0;
		while (minPagesAllocated <= maxPagesAllocated) {
			possiblePagesAllocated = minPagesAllocated + (maxPagesAllocated - minPagesAllocated) / 2;
			if (findStudents(books, possiblePagesAllocated) <= students) {
				ans = possiblePagesAllocated;
				maxPagesAllocated = possiblePagesAllocated - 1;
			} else {
				minPagesAllocated = possiblePagesAllocated + 1;
			}
		}
		return ans;
	}

	private static int findStudents(List<Integer> books, int possiblePagesAllocated) {
		int sum = 0;
		int count = 0;
		int i = 0;
		for (; i < books.size(); i++) {
			sum += books.get(i);
			if (sum > possiblePagesAllocated) {
				count++;
				sum = books.get(i);
			}
		}
		if (sum > 0) {
			count++;
		}
		return count;
	}

}
