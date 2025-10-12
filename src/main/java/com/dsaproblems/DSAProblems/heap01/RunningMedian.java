package com.dsaproblems.DSAProblems.heap01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class RunningMedian {

    public static void main(String[] args) {
        // 1,2,5,4,3
        // 3, 7, 11, 5, 13, 23, 20, 17
        // 5,4,1,3,2
        // 9,6,3,10
        // 59,64,10,39
        ArrayList<Integer> A = new ArrayList<>(List.of(1, 2, 5, 4, 3));
        // System.out.println(heapSort1(A));
        // System.out.println(findMediansv1(A));
        System.out.println(findMediansv2(A));
//		System.out.println(findMediansv3(A));
//		System.out.println(findMediansv4(A));
//		System.out.println(findMediansv5(A));
//		System.out.println(findMediansv6(A));
        System.out.println(findMediansv7(A));//cannot add a null element to heap
    }

    private static ArrayList<Integer> findMediansv2(ArrayList<Integer> input) {
        // maxHeap for lower half, minHeap for upper half
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> medians = new ArrayList<>();
        int median = input.get(0);
        maxHeap.offer(median);
        medians.add(median);
        for (int i = 1; i < input.size(); i++) {
            int num = input.get(i);
            if (minHeap.size() == maxHeap.size()) {
                if (num < median) {
                    maxHeap.offer(num);
                    median = maxHeap.peek();
                } else {
                    minHeap.offer(num);
                    median = minHeap.peek();
                }
            } else {
                if (maxHeap.size() > minHeap.size()) {
                    if (num < median) {
                        minHeap.offer(maxHeap.poll());
                        maxHeap.offer(num);
                    } else {
                        minHeap.offer(num);
                    }
                } else {
                    if (num > median) {
                        maxHeap.offer(minHeap.poll());
                        minHeap.offer(num);
                    } else {
                        maxHeap.offer(num);
                    }
                }
                median = maxHeap.size() > minHeap.size() ? minHeap.peek() : maxHeap.peek();
            }
            medians.add(median);
        }
        return medians;
    }

    private static ArrayList<Integer> findMediansv7(ArrayList<Integer> input) {
        Queue<Integer> s = new PriorityQueue<>(new IntegerCompare());
        Queue<Integer> g = new PriorityQueue<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int median = input.get(0);
        s.offer(median);
        ans.add(median);
        int x = 0;
        for (int i = 1; i < input.size(); i++) {
            x = input.get(i);
            if (s.size() > g.size()) {
                if (x < median) {
                    g.offer(s.peek());
                    s.poll();
                    s.offer(x);
                } else {
                    g.offer(x);
                }
                median = s.size() > g.size() ? g.peek() : s.peek();
            } else if (s.size() < g.size()) {
                if (x > median) {
                    s.offer(g.peek());
                    g.poll();
                    g.offer(x);
                } else {
                    s.offer(x);
                }
                median = s.size() > g.size() ? g.peek() : s.peek();
            } else {
                if (x < median) {
                    s.offer(x);
                    median = s.peek();
                } else {
                    g.offer(x);
                    median = g.peek();
                }
            }
            ans.add(median);
        }
        return ans;

//		Queue<Integer> max_heap = new PriorityQueue<Integer>(new IntegerCompare());// insert the smaller elements in the
//																					// max heap
//		Queue<Integer> min_heap = new PriorityQueue<Integer>();// insert the larger elements in the min heap
//		ArrayList<Integer> ans = new ArrayList<Integer>();
//		int median = input.get(0);
//		max_heap.offer(median);
//		ans.add(median);
//		int currLength = ans.size();
//		Integer currElement = null;
//		for (int i = 1; i < input.size(); i++) {
//			currLength = i + 1;
//			currElement = input.get(i);
//			if (currLength % 2 == 0) {
//				if (max_heap.peek() > currElement) {
//					min_heap.offer(max_heap.poll());
//					max_heap.offer(currElement);
//				} else {
//					min_heap.offer(currElement);
//				}
//				ans.add((min_heap.peek() + max_heap.peek()) / 2);
//			} else {
//				if (max_heap.peek() > currElement) {
//					min_heap.offer(max_heap.poll());
//					max_heap.offer(currElement);
//				} else {
//					min_heap.offer(currElement);
//				}
//				ans.add(min_heap.peek());
//			}
//		}
//		return ans;
    }

    // working solution, but throws TLE, O(n^2log(n))
    private static List<Integer> findMediansv6(ArrayList<Integer> input) {
        List<Integer> ans = new ArrayList<>();
        ans.add(input.get(0));
        Integer temp = null;
        Integer prev = null;
        int currLength = ans.size();
        for (int i = 1; i < input.size(); i++) {
            prev = i - 1;
            while (prev >= 0 && input.get(prev) > input.get(prev + 1)) {
                temp = input.get(prev);
                input.set(prev, input.get(prev + 1));
                input.set(prev + 1, temp);
                prev--;
            }
            currLength = i + 1;
            if (currLength % 2 == 1) {
                ans.add(input.get(currLength / 2));
            } else {
                ans.add(input.get(currLength / 2 - 1));
            }
        }
        return ans;
    }

    // working solution, but throws TLE, O(n^2log(n))
    private static List<Integer> findMediansv5(ArrayList<Integer> input) {
        List<Integer> inputStream = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        inputStream.add(input.get(0));
        ans.add(input.get(0));
        int currLength = inputStream.size();
        for (int i = 1; i < input.size(); i++) {
            inputStream.add(input.get(i));
            Collections.sort(inputStream);
            currLength = inputStream.size();
            if (currLength % 2 == 1) {
                ans.add(inputStream.get(currLength / 2));
            } else {
                ans.add(inputStream.get(currLength / 2 - 1));
            }
        }
        return ans;
    }

    private static List<Integer> findMediansv4(ArrayList<Integer> input) {
        List<Integer> inputStream = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        inputStream.add(input.get(0));
        ans.add(input.get(0));
        int currLength = inputStream.size();
        for (int i = 1; i < input.size(); i++) {
            inputStream.add(input.get(i));
            Collections.sort(inputStream);
            currLength = inputStream.size();
            if (currLength % 2 == 1) {
                ans.add(inputStream.get(currLength / 2));
            } else {
                ans.add((inputStream.get(currLength / 2) + inputStream.get(currLength / 2 - 1)) / 2);
            }
        }
        return ans;
    }

    private static ArrayList<Integer> findMediansv3(ArrayList<Integer> input) {
        Queue<Integer> max_heap = new PriorityQueue<Integer>(new IntegerCompare());
        Queue<Integer> min_heap = new PriorityQueue<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < input.size(); ++i) {
            add(input.get(i), min_heap, max_heap);
            ans.add(get_median(min_heap, max_heap));
        }
        return ans;
    }

    private static Integer get_median(Queue<Integer> min_heap, Queue<Integer> max_heap) {
        int total = min_heap.size() + max_heap.size();
        int ret;
        if (total % 2 == 1) {
            if (max_heap.size() > min_heap.size())
                ret = max_heap.peek();
            else
                ret = min_heap.peek();
        } else {
            ret = Integer.MAX_VALUE;
            if (max_heap.size() != 0)
                ret = Math.min(ret, max_heap.peek());
            if (min_heap.size() != 0)
                ret = Math.min(ret, min_heap.peek());
        }
        return ret;
    }

    private static void add(Integer a, Queue<Integer> min_heap, Queue<Integer> max_heap) {
        if (max_heap.size() != 0 && (a >= max_heap.peek()))
            min_heap.offer(a);
        else
            max_heap.offer(a);

        if (Math.abs(max_heap.size() - min_heap.size()) > 1) {
            if (max_heap.size() > min_heap.size()) {
                int temp = max_heap.peek();
                max_heap.poll();
                min_heap.offer(temp);
            } else {
                int temp = min_heap.peek();
                min_heap.poll();
                max_heap.offer(temp);
            }
        }
    }

    private static ArrayList<Integer> findMediansv1(ArrayList<Integer> A) {
        PriorityQueue<Integer> leftheap = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> rightheap = new PriorityQueue<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int len = A.size();
        for (int i = 0; i < len; i++) {
            if (!ans.isEmpty() || i != 0) {
                if (A.get(i) > ans.get(i - 1)) {
                    rightheap.add(A.get(i));
                } else {
                    leftheap.add(A.get(i));
                }
            } else {
                leftheap.add(A.get(i));
            }
            if ((leftheap.size() - rightheap.size()) > 1) {
                int item = leftheap.poll();
                rightheap.add(item);
            }
            if ((i + 1) % 2 == 0) {
                ans.add((leftheap.poll() + rightheap.poll()) / 2);
            } else {
                if (leftheap.size() > rightheap.size()) {
                    ans.add(leftheap.poll());
                } else {
                    ans.add(rightheap.poll());
                }
            }
        }
        return ans;
    }

}
