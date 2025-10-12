package com.dsaproblems.DSAProblems.advancedJava.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyCustomAnnotation {

    int priority() default 1;

    String[] tags() default {};
}
