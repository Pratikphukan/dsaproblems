package com.dsaproblems.DSAProblems.advancedJava.annotations;

@MyCustomAnnotation(priority = 2, tags = {"important", "feature"})
public class Information {

    @MyCustomAnnotation(tags = {"details"})
    public void getDetailedInfo() {
        System.out.println("This is a detailed information method.");
    }
}
