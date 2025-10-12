package com.dsaproblems.DSAProblems.interview.v0;

import java.util.Objects;

public class Player {

    String name;

    int age;

    Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player player)) return false;
        return age == player.age && Objects.equals(name, player.name);
    }
}
