package com.dsaproblems.DSAProblems.aqr.p2;

import com.dsaproblems.DSAProblems.aqr.p1.Vehicle;

public class Car extends Vehicle {

    void props() {
        Vehicle v = new Vehicle();
        //v.name = "BMW";
    }

    public static void main(String args[]) {
        Car c = new Car();
        c.name = "Ferrari";
    }
}
