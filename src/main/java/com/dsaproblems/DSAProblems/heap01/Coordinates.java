package com.dsaproblems.DSAProblems.heap01;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
public class Coordinates implements Comparable<Coordinates> {

    @Setter
    private Integer x;

    @Setter
    private Integer y;

    private Long distance;

    public Coordinates(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
        this.distance = (long) x * x + (long) y * y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + distance + "]";
    }

    @Override
    public int compareTo(Coordinates c) {
        return Long.compare(this.distance, c.distance);
    }
}
