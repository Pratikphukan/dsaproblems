package com.dsaproblems.DSAProblems.graph02;

public class Client {

	public static void main(String[] args) {
		Graph<Character> theGraph = new Graph<>(6);
		theGraph.addNode('A');
		theGraph.addNode('B');
		theGraph.addNode('C');
		theGraph.addNode('D');
		theGraph.addNode('E');
		theGraph.addNode('F');

		theGraph.addEdge('A', 'B');
		theGraph.addEdge('A', 'D');
		theGraph.addEdge('B', 'C');
		theGraph.addEdge('B', 'D');
		theGraph.addEdge('D', 'E');
		theGraph.addEdge('E', 'F');
	}

}
