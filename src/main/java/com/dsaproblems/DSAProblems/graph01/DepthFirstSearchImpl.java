package com.dsaproblems.DSAProblems.graph01;

import java.util.ArrayDeque;
import java.util.Deque;

public class DepthFirstSearchImpl {

	private Deque<Integer> stack;

	private Graph<Character> graph;

	public DepthFirstSearchImpl(Graph<Character> graph) {
		this.stack = new ArrayDeque<>();
		this.graph = graph;
	}

	public void depthFirstSearch() {
		graph.getNode(0).setVisited(true);
		stack.addFirst(0);
		while (!stack.isEmpty()) {
			int adj = graph.getAdjUnvisitedVertex(stack.peekFirst());
			if (adj == -1) {
				stack.removeFirst();
			} else {
				graph.getNodes().get(adj).setVisited(true);
				stack.addFirst(adj);
			}
		}
	}
}
