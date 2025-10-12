package com.dsaproblems.DSAProblems.memorymanagement01;

import java.util.List;

/*
 * a final list means that the value of the reference to the list cannot change, so the 
 * list cannot be replaced with a new list, but the list itself is mutable, so the values 
 * on the list, they can change. They can either be added or removed, or the values, the 
 * objects on the list, they can change their selves, so careful with these types of classes 
 * because this is not really an immutable class.
 */
public class NotReallyImmutableObject {

	private final int x;

	private final String y;

	private final List<Integer> list;

	public NotReallyImmutableObject(int x, String y, List<Integer> list) {
		this.x = x;
		this.y = y;
		this.list = list;
	}

}
