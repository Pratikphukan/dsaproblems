package com.dsaproblems.DSAProblems.advancedJava;

class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Woof");
    }
}

public class PolymorphExample {

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.makeSound();
    }

}
