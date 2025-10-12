package com.dsaproblems.DSAProblems.graph02;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CheckCycleInAGraph {

	public static void main(String[] args) {
		int nodes = 5;
		List<List<Integer>> edges = new ArrayList<>();
		edges.add(new ArrayList<>(Arrays.asList(1, 2)));
		edges.add(new ArrayList<>(Arrays.asList(1, 3)));
		edges.add(new ArrayList<>(Arrays.asList(2, 3)));
		edges.add(new ArrayList<>(Arrays.asList(1, 4)));
		edges.add(new ArrayList<>(Arrays.asList(4, 5)));

		// System.out.println(checkCycleInUndirectedGraph(nodes, edges));

		System.out.println(checkCycleInUndirectedGraph1(nodes, edges));
	}

	// wrong solution
	private static boolean checkCycleInUndirectedGraph1(int nodes, List<List<Integer>> edges) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= nodes; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.size(); i++) {
			int node1 = edges.get(i).get(0);
			int node2 = edges.get(i).get(1);
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		Set<Integer> visitedNodes = new HashSet<>();
		for (int node = 1; node <= nodes; node++) {
			if (!visitedNodes.contains(node)) {
				visitedNodes.add(node);
				Deque<Integer> stack = new ArrayDeque<>();
				stack.addFirst(node);
				while (!stack.isEmpty()) {
					Integer parent = stack.removeFirst();
					List<Integer> neighbourList = graph.get(parent);
					for (Integer neighbour : neighbourList) {
						if (!visitedNodes.contains(neighbour)) {
							stack.addFirst(neighbour);
							visitedNodes.add(neighbour);
						}
						if (Objects.equals(parent, node)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private static boolean checkCycle(List<List<Integer>> graph, Set<Integer> visitedNodes, int parent, int node) {
		visitedNodes.add(node);
		List<Integer> neighbourList = graph.get(node);
		for (Integer neighbour : neighbourList) {
			if (!visitedNodes.contains(neighbour)) {
				checkCycle(graph, visitedNodes, node, neighbour);
			} else if (neighbour != parent) {
				return true;
			}
		}
		return false;
	}

	// check using DFS
	private static boolean checkCycleInUndirectedGraph(int nodes, List<List<Integer>> edges) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= nodes; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.size(); i++) {
			int node1 = edges.get(i).get(0);
			int node2 = edges.get(i).get(1);
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		Set<Integer> visitedNodes = new HashSet<>();
		for (int node = 1; node <= nodes; node++) {
			if (!visitedNodes.contains(node)) {
				return checkCycle(graph, visitedNodes, -1, node);
			}
		}
		return false;
	}

}
