package main.java.com.intermediate1;

import java.util.Optional;

public class CreatingOptionals {
    public static void main(String[] args) {
        Optional<User> optionalUser = Optional.empty();
        System.out.println("Empty optional: " + optionalUser.isPresent());
        System.out.println(optionalUser.orElseGet(() -> new User("Marina")));

        Optional<User> optionalUser1 = Optional.of(new User("Lisa"));
        System.out.println("Lisa optional: " + optionalUser1.isPresent());

        User user = null;
        Optional<User> optionalUser2 = Optional.ofNullable(user);
        System.out.println("Null optional: " + optionalUser2.isPresent());

        System.out.println(optionalUser1.filter(u -> u.getName().startsWith("X")).orElse(new User("Luke")));

        Optional<Integer> integerOptional = Optional.of(30);
        System.out.println("mapped integer: " + integerOptional.map(i -> i * 2).get());
    }
}
