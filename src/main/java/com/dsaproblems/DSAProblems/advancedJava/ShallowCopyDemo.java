package com.dsaproblems.DSAProblems.advancedJava;


import lombok.Getter;
import lombok.Setter;

@Getter
class Address {

    @Setter
    String city;  // Field to store the city name

    // Constructor to initialize the city
    Address(String city) {
        this.city = city;
    }

    // Copy constructor for Address performing a deep copy.
    Address(Address other) {
        // Copy the street value from the other Address.
        // Even though String assignment is safe because of immutability,
        // we want to explicitly copy the value.
        this.city = other.city;
    }

    // toString method to easily print Address details
    @Override
    public String toString() {
        return city;
    }
}

// Define the Person class containing both primitive and object fields
@Getter
@Setter
class Person {

    String name;      // Field to store the person's name (primitive reference)

    Address address;  // Field to store the person's address (nested object reference)

    // Constructor to initialize Person with name and address
    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Copy constructor that performs a shallow copy.
    // This copies the reference of the existing address rather than creating a new Address object.
//    Person(Person other) {
//        this.name = other.name;           // Copy the primitive (or immutable) value
//        this.address = other.address;     // Only the reference to the Address object is copied (shallow copy)
//    }

    // Copy constructor for Person performing a deep copy.
    public Person(Person other) {
        // Copy the name field directly.
        // Although this is technically a shallow copy,
        // it's acceptable because String objects are immutable in Java.
        this.name = other.name;

        // Perform a deep copy of the address field by using Address's copy constructor.
        this.address = new Address(other.address);
    }

    // Method to display the person's details
    public void displayPerson() {
        System.out.println("Name: " + this.name + ", City: " + this.address);
    }
}

public class ShallowCopyDemo {

    public static void main(String[] args) {
        // Create an Address object with city name "New York"
        Address addr = new Address("New York");

        // Create a Person object with a name and the Address object
        Person originalPerson = new Person("Alice", addr);

        // Create a shallow copy of originalPerson using the copy constructor
        Person shallowCopiedPerson = new Person(originalPerson);

        // Display both person's details before any changes
        System.out.println("Before modifying the address:");
        originalPerson.displayPerson();         // Expected output: Name: Alice, City: New York
        shallowCopiedPerson.displayPerson();      // Expected output: Name: Alice, City: New York

        // Modify the address of the copied person
        // Since it's a shallow copy, the modification will reflect in the original person's Address as well.
        shallowCopiedPerson.address.city = "San Francisco";

        // Display both person's details after modifying the address in the shallow copy
        System.out.println("\nAfter modifying the address in shallow copy:");
        originalPerson.displayPerson();         // Expected output: Name: Alice, City: San Francisco
        shallowCopiedPerson.displayPerson();      // Expected output: Name: Alice, City: San Francisco

        // Explanation: Changing the city in the shallow copy affects the original because both objects share the same Address instance.
    }
}
