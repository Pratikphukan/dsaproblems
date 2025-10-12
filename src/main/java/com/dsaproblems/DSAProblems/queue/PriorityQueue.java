package com.dsaproblems.DSAProblems.queue;

public class PriorityQueue {

	private int MAX; // maximum elements that can be in the array
	private int[] arr;
	private int size;

	public PriorityQueue(int n) {
		MAX = n;
		this.arr = new int[MAX];
		this.size = 0;
	}

	public void insertDesc(int val) {
		if (size == 0) {
			arr[0] = val;
			size++;
			return;
		}
		int i = size - 1;
		for (; i >= 0; i--) {
			if (val > arr[i]) {
				arr[i + 1] = arr[i];
			} else {
				break;
			}
		}
		arr[i + 1] = val;
		size++;
	}

	public void insertAsc(int val) {
		if (size == 0) {
			arr[0] = val;
			size++;
			return;
		}
		int i = size - 1;
		for (; i >= 0; i--) {
			if (val < arr[i]) {
				arr[i + 1] = arr[i];
			} else {
				break;
			}
		}
		arr[i + 1] = val;
		size++;
	}

	public int[] insertionSort() {
		for (int i = 1; i < size; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j + 1] < arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}

	public void printPriorityQueue() {
		for (int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public int remove() {
		return arr[--size];
	}

	public boolean isFull() {
		return size == MAX;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
