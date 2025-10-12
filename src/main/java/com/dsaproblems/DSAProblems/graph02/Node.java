package com.dsaproblems.DSAProblems.graph02;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node<T> {

	@NonNull
	private T label;

	private boolean isVisited;

}
