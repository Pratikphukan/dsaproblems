package com.dsaproblems.DSAProblems.graph01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CheckPathBetweenTwoNodes {

	public static void main(String[] args) {
		List<Integer> directedEdges = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
		int src = 1;
		int dest = 1;
		System.out.println(checkPathFromSourceToDestination(directedEdges, src, dest));
	}

	private static boolean checkPathFromSourceToDestination(List<Integer> directedEdges, int src, int dest) {
		if (src == dest) {
			return true;
		}
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= directedEdges.size(); i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i < directedEdges.size(); i++) {
			graph.get(directedEdges.get(i)).add(i + 1);
		}
		Set<Integer> visitedNodes = new HashSet<>();
		Deque<Integer> stack = new LinkedList<>();
		stack.addFirst(src);
		visitedNodes.add(src);
		while (!stack.isEmpty()) {
			Integer node = stack.pollFirst();
			List<Integer> childList = graph.get(node);
			for (Integer child : childList) {
				if (!visitedNodes.contains(child)) {
					stack.addFirst(child);
					visitedNodes.add(child);
					if (child == dest) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
