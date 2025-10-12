package com.dsaproblems.DSAProblems.graph01;

import java.util.ArrayDeque;
import java.util.Deque;

public class BreadthFirstSearchImpl {

	private Deque<Integer> queue;

	private Graph<Character> graph;

	public BreadthFirstSearchImpl(Graph<Character> graph) {
		this.queue = new ArrayDeque<>();
		this.graph = graph;
	}

	public void breadthFirstSearch() {
		graph.getNode(0).setVisited(true);
		queue.addLast(0);
		while (!queue.isEmpty()) {
			int v1 = queue.removeFirst();
			while (graph.getAdjUnvisitedVertex(v1) != -1) {
				int adj = graph.getAdjUnvisitedVertex(v1);
				graph.getNodes().get(adj).setVisited(true);
				queue.addLast(adj);
			}

		}
	}
}
