package com.dsaproblems.DSAProblems.graph01;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NonNull;

/*
 * In Java, the keys in a HashMap are typically objects, 
 * and the keys themselves are immutable. This is because 
 * the keys are used to determine the hash code and equality 
 * for retrieving values from the HashMap. If you modify the 
 * key after it has been used to put a value into the HashMap, 
 * it can lead to unexpected behavior.
 */
@Data
class Key {

	@NonNull
	private String value;
}

public class Learning {

	public static void main(String[] args) {
		Map<Key, String> map = new HashMap<>();
		Key key = new Key("original");
		map.put(key, "Pratik");
		System.out.println(map.get(key));

		key.setValue("modified");
		System.out.println(map.get(key));
	}

}
