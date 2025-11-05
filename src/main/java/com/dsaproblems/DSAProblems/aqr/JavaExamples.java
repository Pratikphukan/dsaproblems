package com.dsaproblems.DSAProblems.aqr;

import java.util.*;

class Student {
    String name;
    int rollNo;

    Student(int num) {
        this.rollNo = num;
        this.name = "abc";
    }

    Student(int num, String str) {
        this.rollNo = num;
        this.name = str;
    }

    public void print() {
        System.out.print(this.name + " " + this.rollNo + " ");
    }
}

class Book {
    int price;
    static int count;

    public Book(int price) {
        this.price = price;
        count++;
    }
}

class Box {

    int width;
    int height;
    int length;

    void volume() {
        System.out.println(length * width * height);
    }
}

class IB {
    protected void getData() {
        System.out.println("Inside IB");
    }
}

class InterviewBit extends IB {
    protected void getData() {
        System.out.println("Inside InterviewBit");
    }
}

class Derived {
    protected void getDetails() {
        System.out.println("Derived class");
    }
}

class Test extends Derived {
    protected final void getDetails() {
        System.out.println("Test class");
    }
}

class Vehicle {
    int seats;
    int speed;

    Vehicle(int seats, int speed) {
        System.out.println("Vehicle ");
        this.seats = seats;
        this.speed = speed;
    }
}

class Car extends Vehicle {

    int id;

    //The Car class needs a constructor like Vehicle because Vehicle does not
    // have a no-argument (default) constructor. In Java, if a superclass
    // defines only parameterized constructors, subclasses must explicitly
    // call one of them using super(...) in their own constructors. Otherwise,
    // the code will not compile.
    Car(int seats, int speed) {
        super(seats, speed);
        System.out.println("Car ");
    }
}

interface Scaler {
    void myMethod();

    void getInfo();
}

abstract class InterviewReady implements Scaler {
    void getData() {
        System.out.println("IB");
    }
}

class InterviewReadyExt extends InterviewReady {
    public void myMethod() {
        System.out.println("InterviewBit");
    }

    public void getInfo() {
        System.out.println("Scaler");
    }
}

public class JavaExamples {

    public static void main(String[] args) {
        byte by = 127;
        by++;
        System.out.println(by);

        short sh = (short) 201000;
        System.out.println(sh);

        //Because Integer in Java is immutable and passed by value.
        // When you call modify(i), a copy of the reference to the
        // Integer object is passed. Inside modify, i = i + 1; creates
        // a new Integer object, but this change does not affect the
        // original reference in main. Thus, the value of i in main
        // remains unchanged.

        //With an ArrayList, the reference to the object is still
        // passed by value, but since ArrayList is mutable,
        // changes to its contents inside a method will affect
        // the original list. However, reassigning the reference
        // inside the method does not affect the original reference.

        Integer i = Integer.valueOf(12);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.print(i + " " + list);
        modify(i, list);
        System.out.println();
        System.out.print(i + " " + list);


        System.out.println();
        ArrayList obj = new ArrayList();
        obj.add("A");
        obj.add(0, "B");
        System.out.println(obj.size());

        Box b = new Box();
        b.height = 5;
        b.width = 4;
        b.volume();


        Student s1 = new Student(101);
        s1.print();
        Student s2 = new Student(150, "xyz");
        s2.print();

        System.out.print(Book.count + " ");
        Book b1 = new Book(500);
        Book b2 = new Book(600);
        System.out.println(Book.count);


        IB o = new InterviewBit();
        o.getData();

        Derived x = new Derived();
        x.getDetails();

        Derived y = new Test();
        y.getDetails();

        InterviewReady ir = new InterviewReadyExt();
        ir.getInfo();

        TreeSet<String> t = new TreeSet<>();
        t.add("Scaler");
        t.add("InterviewBit");
        t.add("Coders");
        t.add("Coders");
        for (String temp : t) System.out.printf(temp + " ");
        System.out.println("\n");


        List<String> list1 = new LinkedList<>();
        list1.add("Scaler");
        list1.add("For");
        list1.add("Coders");
        list1.add("InterviewBit");

        Iterator<String> iter = list1.iterator();
        while (iter.hasNext())
            System.out.printf(iter.next() + " ");
        System.out.println();


        list1.add("Coders");
        List<String> list2 = new LinkedList<>();
        list2.add("Coders");
        list1.removeAll(list2);
        for (String temp : list1)
            System.out.printf(temp + " ");
        System.out.println();

        List<Integer> nums = new ArrayList<>(List.of(1, 4, 3, 2));
        //nums.remove(3);
        //nums.remove(Integer.valueOf(2));
        System.out.println(nums);

        //Yes. java.util.HashSet allows one null element. It's backed by a HashMap
        // (which permits a single null key), so duplicate null inserts are ignored.
        // Note: ConcurrentHashMap-based sets and TreeSet (with natural ordering) do not allow null.


        Map<Integer, String> employeeMap = new HashMap<>();
        employeeMap.put(123, "Alex");
        employeeMap.put(342, "Ryan");
        employeeMap.put(143, "Joe");
        employeeMap.put(234, "Allen");
        employeeMap.put(432, "Roy");
        System.out.println(employeeMap);
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.putAll(employeeMap);
        System.out.println("Sorted map " + sortedMap);

        //HashMap: no predictable iteration order (buckets + hash).
        //LinkedHashMap: maintains a doubly-linked list of entries so iteration is in insertion order (or access order if constructed with accessOrder=true)
        //Both allow one null key and multiple null values.

        //Insertion order means iteration yields entries in the order you inserted them.
        //But you created the map with new LinkedHashMap<>(..., true) — that true
        //enables access-order, so calls like get(...) move the accessed entry to the end.
        // That explains why the printed order changed after get("Google") and get("BMW")
        HashMap<String, Integer> stocks = new LinkedHashMap<>(16, 0.75f, true);
        stocks.put("Apple", 123);
        stocks.put("BMW", 54);
        stocks.put("Google", 87);
        stocks.put("Microsoft", 232);
        stocks.put("Oracle", 76);
        System.out.println(stocks);
        stocks.get("Google");
        stocks.get("BMW");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(stocks);

        //Arrays.binarySearch returns the index if the key is found.
        // If not found it returns -(insertionPoint)-1 where insertionPoint
        // is the index where the key would be inserted to keep the array sorted.
        //1, 5, 4, 6, 7, 8, 9, 10, 11
        //1, 3, 5, 6, 7, 8, 9, 10, 11
        int[] numbers = {1, 5, 4, 6, 7, 8, 9, 10, 11};
        System.out.println(Arrays.binarySearch(numbers, 4));
        System.out.println(Arrays.binarySearch(numbers, 5));

        HashSet<String> set = new HashSet<>();
        set.add(null);
        set.add("One");
        for (String s : set)
            System.out.println(s);
    }

    private static void modify(Integer i, ArrayList<Integer> l) {
        i = i + 1;
        l.add(2); // Modifies the original list
        // l = new ArrayList<>(); // This would not affect the original reference
    }
}
