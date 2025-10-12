package com.dsaproblems.DSAProblems.graph01;

public class Client {

	public static void main(String[] args) {
		Graph<Character> theGraph = new Graph<>();
		theGraph.addNode('A');
		theGraph.addNode('B');
		theGraph.addNode('C');
		theGraph.addNode('D');
		theGraph.addNode('E');
		theGraph.addNode('F');

		theGraph.addEdge(0, 1);
		theGraph.addEdge(1, 2);
		theGraph.addEdge(0, 3);
		theGraph.addEdge(3, 4);
		theGraph.addEdge(4, 5);
		theGraph.addEdge(1, 3);

		BreadthFirstSearchImpl bfsImpl = new BreadthFirstSearchImpl(theGraph);
		bfsImpl.breadthFirstSearch();

		DepthFirstSearchImpl dfsImpl = new DepthFirstSearchImpl(theGraph);
		dfsImpl.depthFirstSearch();

	}

}
