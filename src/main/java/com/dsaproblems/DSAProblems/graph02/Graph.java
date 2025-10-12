package com.dsaproblems.DSAProblems.graph02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {

	private Map<Node<T>, List<Node<T>>> map;

	public Graph(Integer nodes) {
		map = new HashMap<>();
	}

//	public void addEdge(int start, int end) {
//		graph.get(start).add(end);
//		graph.get(end).add(start);
//	}

	public void addNode(T t) {

		if (!map.containsKey(t)) {
			map.put(new Node<>(t), new ArrayList<>());
		}
	}

	public void addEdge(T start, T end) {
		Node<T> startNode = new Node<>(start);
		Node<T> endNode = new Node<>(end);
		map.get(startNode).add(endNode);
		map.get(endNode).add(startNode);
	}

}
