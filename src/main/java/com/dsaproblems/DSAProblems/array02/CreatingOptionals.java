package com.dsaproblems.DSAProblems.array02;

import java.util.Optional;

public class CreatingOptionals {

	public static void main(String[] args) {
		Optional<User> optionalUser = Optional.empty();
		System.out.println(optionalUser.isPresent());

		Optional<User> optionalUser1 = Optional.of(new User("Pratik", "8638335821"));
		System.out.println(optionalUser1.isPresent());

		Optional<User> optionalUser3 = Optional.ofNullable(null);
		System.out.println(optionalUser3.isPresent());

//		Optional<User> optionalUser2 = Optional.of(null);
//		System.out.println(optionalUser2.isPresent());

		// System.out.println(optionalUser3.get());
		System.out.println(optionalUser3.orElse(new User("Prajapati", "8725262112")));
		// System.out.println(optionalUser3.orElseThrow(() -> new
		// NullPointerException("No value present!!")));
		System.out.println(optionalUser3.orElseGet(() -> new User("Ila", "9435101212")));

		Optional<String> optional1 = Optional.empty();
		Optional<Integer> optional2 = Optional.ofNullable(34);
	}

}
